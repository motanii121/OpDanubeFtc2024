package org.firstinspires.ftc.teamcode.Algorithms

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcore.external.Telemetry


class motionprofiler(private val maxVelocity: Double, private val acceleration: Double, private val deceleration: Double) {

    private var initialVelocity = 0.0
    var initialPosition = 0.0
    var finalPosition = 0.0
    private var deltaPose = 0.0
    private var sign = 0.0
    private var maxReachedVelocity = 0.0
    private var t0 = 0.0
    private var t1 = 0.0
    private var t2 = 0.0
    private var t3 = 0.0
    private var t = 0.0
    private var v0 = 0.0
    var position = 0.0
        private set
    var velocity = 0.0
        private set
    var signedVelocity = 0.0
        private set
    val timer = ElapsedTime()

    init {
        setMotion(0.0, 0.0, 0.0)
    }

    fun setMotion(initialPosition: Double, finalPosition: Double, initialVelocity: Double) {
        this.initialPosition = initialPosition
        this.finalPosition = finalPosition
        this.initialVelocity = Math.signum(initialVelocity) * Math.min(Math.abs(initialVelocity), maxVelocity)
        sign = Math.signum(finalPosition - initialPosition)
        v0 = sign * initialVelocity
        t0 = v0 / acceleration
        deltaPose = t0 * v0 / 2.0 + Math.abs(finalPosition - initialPosition)
        maxReachedVelocity = Math.max(if (calculateDeltaIfMaxReachedVelocityIs(maxVelocity) - deltaPose <= 0) maxVelocity else Math.sqrt(deltaPose * 2.0 * acceleration * deceleration / (acceleration + deceleration)), v0)
        t1 = maxReachedVelocity / acceleration - t0
        t3 = maxReachedVelocity / deceleration
        t2 = Math.abs(Math.min(0.0, calculateDeltaIfMaxReachedVelocityIs(maxVelocity) - deltaPose)) / maxVelocity
        t = t1 + t2 + t3
        timer.reset()
    }

    private fun calculateDeltaIfMaxReachedVelocityIs(v: Double): Double {
        return v * v / 2.0 * (acceleration + deceleration) / (acceleration * deceleration)
    }

    val phase: Int
        get() {
            if (timer.seconds() <= t1) return 1
            if (timer.seconds() <= t1 + t2) return 2
            return if (timer.seconds() <= t1 + t2 + t3) 3 else 0
        }

    private fun getVelocity(phase: Int, time: Double): Double {
        when (phase) {
            1 -> return v0 + time * acceleration
            2 -> return getVelocity(1, t1)
            3 -> return maxReachedVelocity - deceleration * (time - t1 - t2)
        }
        return 0.0
    }

    fun getPosition(time: Double): Double {
        if (time <= t1) return initialPosition + sign * v0 * time / 2.0 + sign * getVelocity(1, time) * time / 2.0
        if (time <= t1 + t2) return initialPosition + sign * v0 * t1 / 2.0 + sign * getVelocity(1, t1) * t1 / 2.0 + sign * getVelocity(2, time) * (time - t1)
        return if (time <= t1 + t2 + t3) getPosition(t1 + t2) + sign * maxReachedVelocity * t3 / 2.0 - sign * getVelocity(3, time) * (t1 + t2 + t3 - time) / 2.0 else finalPosition
    }

    private fun updateVelocity() {
        velocity = getVelocity(phase, timer.seconds())
    }

    private fun updateSignedVelocity() {
        signedVelocity = sign * getVelocity(phase, timer.seconds())
    }

    private fun updatePosition() {
        position = getPosition(timer.seconds())
    }

    val timeToMotionEnd: Double
        get() = Math.max(0.0, t - timer.seconds())

    private fun getTime1(position: Double): Double {
        return (-sign * v0 - Math.signum(v0) * Math.sqrt(v0 * v0 - 2 * sign * acceleration * (initialPosition - position))) / (sign * acceleration)
    }

    private fun getTime2(position: Double): Double {
        return (position - getPosition(t1)) / (sign * (v0 + t1 * acceleration)) + t1
    }

    private fun getTime3(position: Double): Double {
        val qa = -deceleration
        val qb = deceleration * (t + t1 + t2) + maxReachedVelocity
        val qc = 2.0 / sign * (getPosition(t1 + t2) - position) + maxReachedVelocity * t3 - t * (maxReachedVelocity + deceleration * (t1 + t2))
        return (-qb + Math.sqrt(qb * qb - 4.0 * qa * qc)) / (2.0 * qa)
    }

    fun getTime(position: Double): Double {
        if (position >= Math.min(getPosition(t1), getPosition(t1 + t2)) && position <= Math.max(getPosition(t1), getPosition(t1 + t2))) return getTime2(position)
        var s = getTime1(position)
        if (s < 0) s = 0.0
        if (s > t1) {
            s = getTime3(position)
            if (s > t1 + t2 + t3) s = t1 + t2 + t3
        }
        return s
    }

    fun getTimeTo(position: Double): Double {
        return Math.max(0.0, getTime(position) - timer.seconds())
    }

    fun telemetry(telemetry: Telemetry) {
        telemetry.addData("Profile initial position", initialPosition)
        telemetry.addData("Profile final position", finalPosition)
        telemetry.addData("Profile initial velocity", initialVelocity)
        telemetry.addData("Profile current velocity", velocity)
        telemetry.addData("Plateau distance", calculateDeltaIfMaxReachedVelocityIs(maxVelocity))
        telemetry.addData("Max Velocity", maxVelocity)
        telemetry.addData("Max reached Velocity", maxReachedVelocity)
        telemetry.addData("Acceleration", acceleration)
        telemetry.addData("Deceleration", deceleration)
        telemetry.addData("Delta pose", deltaPose)
        telemetry.addData("t1", t1)
        telemetry.addData("t2", t2)
        telemetry.addData("t3", t3)
        telemetry.addData("Phase", phase)
        telemetry.addData("Time to motion end", timeToMotionEnd)
    }

    fun update() {
        updateVelocity()
        updateSignedVelocity()
        updatePosition()
    }
}