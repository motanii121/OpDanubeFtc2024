package org.firstinspires.ftc.teamcode.Math

import kotlin.math.PI
import kotlin.math.floor

object formulas {

    fun max(a: Double, b: Double): Double {
        return if (a > b) {
            a
        } else {
            b
        }
    }

    fun min(a: Double, b: Double): Double {
        return if (a < b) {
            a
        } else {
            b
        }
    }

    fun abs(a: Double): Double {
        return if (a < 0) {
            -a
        } else {
            a
        }
    }

    fun floatmod(ang1: Double, ang2: Double) = ang1 - floor(ang1 / ang2) * ang2
    fun angnorm(ang: Double) = floatmod(ang, 2 * PI)
    fun angdiff(ang1: Double, ang2: Double) = floatmod(ang2 - ang1 + PI, 2 * PI) - PI

    fun Double.format(digits: Int) = "%.${digits}f".format(this)
}