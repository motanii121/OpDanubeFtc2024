package org.firstinspires.ftc.teamcode.Systems.colorsensor

import androidx.core.graphics.alpha
import androidx.core.graphics.red
import androidx.core.graphics.toColor
import org.firstinspires.ftc.teamcode.Systems.colorsensor.sensor_vars.alpha
import org.firstinspires.ftc.teamcode.Systems.colorsensor.sensor_vars.blue
import org.firstinspires.ftc.teamcode.Systems.colorsensor.sensor_vars.green
import org.firstinspires.ftc.teamcode.Systems.colorsensor.sensor_vars.red
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap

class ColorSensor {
    val lsensor = hardwareMap.colorSensor.get("LSENSOR")
    val rsensor = hardwareMap.colorSensor.get("RSENSOR")

    fun got2pixels(): Boolean{
        var gotleftpixel = lsensor.red() > 900
        var gotrightpixel = rsensor.red() > 1000

        return gotleftpixel && gotrightpixel
    }
}