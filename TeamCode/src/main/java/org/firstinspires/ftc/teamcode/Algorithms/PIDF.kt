package org.firstinspires.ftc.teamcode.Algorithms

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.Math.formulas.abs
import org.firstinspires.ftc.teamcode.TeleOp.koef.angletolerance
import org.firstinspires.ftc.teamcode.Variables.PIDCOEF
import org.firstinspires.ftc.teamcode.Variables.PIDCoefs.pidcoefAngle
import org.firstinspires.ftc.teamcode.Variables.PIDCoefs.pidcoefSpeed
import kotlin.math.sign

class PIDF(private val pidcoef: PIDCOEF) {
    private var proportional = 0.0
    var elapsedTime = ElapsedTime()
    var integral = 0.0

    init { elapsedTime.reset() }

    fun reset() {
        elapsedTime.reset()
        integral = 0.0
    }

    fun update(err: Double): Double {
        val derivative = (err - proportional) / elapsedTime.seconds()
        integral += err * elapsedTime.seconds()
        elapsedTime.reset()
        proportional = err
        return err * pidcoef.p + derivative * pidcoef.d + integral * pidcoef.i + sign(err) * if((pidcoef == pidcoefAngle && abs(err) < angletolerance) || (abs(err) < 15.0)) 0.0 else pidcoef.f
    }
}