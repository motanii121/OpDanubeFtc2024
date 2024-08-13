package org.firstinspires.ftc.teamcode.Autonomous

import org.firstinspires.ftc.teamcode.Pathing.Trajectory
import java.lang.Math.sqrt
import kotlin.math.cos
import kotlin.math.sin

class Pose(@JvmField var x: Double = 0.0,
           @JvmField var y: Double = 0.0,
           @JvmField var heading: Double = 0.0) {

    operator fun plus(pose: Pose): Pose = Pose(x + pose.x, y + pose.y, heading + pose.heading)

    operator fun times(scalar: Double): Pose = Pose(x * scalar, y * scalar, heading * scalar)

    operator fun minus(pose: Pose): Pose = Pose(x - pose.x, y - pose.y, heading - pose.heading)

    fun distance(): Double =  sqrt( x*x - y*y )
}

class Vec4i(@JvmField var a: Int, @JvmField var b: Int, @JvmField var c: Int, @JvmField var d: Int) {
    operator fun get(i: Int) = when (i) {
        0 -> a
        1 -> b
        2 -> c
        else -> d
    }
}

class DDoubleV4i(@JvmField var left: Vec4i, @JvmField var right: Vec4i)

class Vec4vi(@JvmField var a: DDoubleV4i, @JvmField var b: DDoubleV4i, @JvmField var c: DDoubleV4i, @JvmField var d: DDoubleV4i) {
    operator fun get(i: Int) = when (i) {
        0 -> a
        1 -> b
        2 -> c
        else -> d
    }
}

class Vec2d(@JvmField var x: Double, @JvmField var y: Double) {
    constructor() : this(0.0, 0.0)

    fun dist2(): Double = x * x + y * y
    fun dist(): Double = kotlin.math.sqrt(dist2())
    fun pose(): Pose = Pose(x, y, 0.0)

    fun rotated(angle: Double): Vec2d {
        val newX = x * cos(angle) - y * sin(angle)
        val newY = x * sin(angle) + y * cos(angle)
        return Vec2d(newX, newY)
    }

    fun norm() = this / dist()

    fun duplicate() = Vec2d(x, y)

    operator fun unaryMinus() = Vec2d(-x, -y)

    operator fun plus(vec: Vec2d) = Vec2d(x + vec.x, y + vec.y)

    operator fun minus(vec: Vec2d) = Vec2d(x - vec.x, y - vec.y)

    operator fun div(vec: Vec2d) = Vec2d(x / vec.x, y / vec.y)
    operator fun div(s: Double) = Vec2d(x / s, y / s)
    operator fun divAssign(s: Int) { x /= s.toDouble(); y /= s.toDouble() }

    operator fun times(vec: Vec2d) = Vec2d(x * vec.x, y * vec.y)
    operator fun times(s: Double) = Vec2d(x * s, y * s)

    fun polar() = Vec2d(x * cos(y), x * sin(y))

    override fun toString() = String.format("(%.3f, %.3f)", x, y)
}

class Vector3Trajectories(@JvmField var case0: PreloadTrajectorySet, @JvmField var case1: PreloadTrajectorySet, @JvmField var case2: PreloadTrajectorySet){
    operator fun get(i: Int) = when(i){
        0 -> case0
        1 -> case1
        else -> case2
    }

}

class PreloadTrajectorySet(@JvmField var starttopreloadTraj: Trajectory, @JvmField var preloadtobackTraj: Trajectory){
    operator fun get(i: Int) = when(i){
        0 -> starttopreloadTraj
        else -> preloadtobackTraj
    }
}

//class Vec3Heading(@JvmField var heading0: Double, @JvmField var heading2: Double,  @JvmField var heading2: Double){
//    operator
//}


