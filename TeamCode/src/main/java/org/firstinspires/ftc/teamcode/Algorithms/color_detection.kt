package org.firstinspires.ftc.teamcode.Algorithms

import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.isRed
import org.firstinspires.ftc.teamcode.Math.formulas
import org.firstinspires.ftc.teamcode.Math.formulas.abs
import org.firstinspires.ftc.teamcode.Math.formulas.angdiff
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.blueHue
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.blueMaxHueVariation
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.blueMinSaturation
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.blueMinValue
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.redHue
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.redMaxHueVariation
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.redMinSaturation
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.redMinValue
import kotlin.math.PI

object color_detection {

    fun RGBtoHSV(colorArray: DoubleArray): DoubleArray {
        val HSV: DoubleArray = DoubleArray(3)
        HSV[0] = (colorArray[0] / 255.0) * PI * 2 // hue
        HSV[1] = colorArray[1] // saturation
        HSV[2] = colorArray[2] // value

        return HSV
    }

    fun iscolorRed(colorArray: DoubleArray): Boolean {
        val HSV = RGBtoHSV(colorArray)
        return abs(angdiff(HSV[0], redHue)) < redMaxHueVariation && HSV[1] > redMinSaturation && HSV[2] > redMinValue
    }

    fun iscolorBlue(colorArray: DoubleArray): Boolean {
        val HSV = RGBtoHSV(colorArray)
        return abs(angdiff(HSV[0], blueHue)) < blueMaxHueVariation && HSV[1] > blueMinSaturation && HSV[2] > blueMinValue
    }

    fun isRightColor(colorArray: DoubleArray): Boolean {
        return if (isRed) {
            iscolorRed(colorArray)
        } else {
            iscolorBlue(colorArray)
        }
    }
}