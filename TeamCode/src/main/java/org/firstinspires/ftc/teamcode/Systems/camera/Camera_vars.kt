package org.firstinspires.ftc.teamcode.Systems.camera

import com.acmerobotics.dashboard.config.Config
import org.firstinspires.ftc.robotcore.internal.ftdi.eeprom.FT_EEPROM_232H.DRIVE_STRENGTH
import org.firstinspires.ftc.teamcode.Autonomous.DDoubleV4i
import org.firstinspires.ftc.teamcode.Autonomous.Vec4i
import org.firstinspires.ftc.teamcode.Autonomous.Vec4vi

@Config
object camera_vars {

    @JvmField
    var blueMinSaturation: Double = 200.0
    @JvmField
    var redMinSaturation: Double = 125.0
    @JvmField
    var redMinValue : Double = 40.0
    @JvmField
    var blueMinValue: Double = 100.0
    @JvmField
    var redMaxHueVariation: Double = 0.5
    @JvmField
    var blueMaxHueVariation: Double = 0.5
    @JvmField
    var redHue: Double = 0.0
    @JvmField
    var blueHue: Double = 3.6

    @JvmField
    var cameraGain: Int = 0
    @JvmField
    var cameraExposure: Int = 0

    @JvmField
    var DRAW_BOXES: Boolean = true
    @JvmField
    var DO_I_EVEN_PROCESS_FRAME = true
    @JvmField
    var DRAW_MEDIAN = true
    @JvmField
    var CUR_DONE_CORRECTION: Double = 0.0
    @JvmField
    var CameraMidPos: Double = 0.0
    @JvmField
    var COL_INDEX: Int = 0
    @JvmField
    var patratepelungime: Int = 300

    @JvmField
    var patratepelatime: Int = 100

    //offseturi de centru
    @JvmField
    var offx: Int = 300

    @JvmField
    var offy: Int = 300

    @JvmField
    var step: Int = 35

    @JvmField
    var autominblocks: Int = 30

    @JvmField
    var linepos: Double = 200.0

    @JvmField
    var lineposred: Double = 400.0

    @JvmField
    var squaresize: Int = 10

    @JvmField
    var framelength: Int = 650

    @JvmField
    var framewidth: Int = 480

    @JvmField
    var testx: Int = 100

    @JvmField
    var testy: Int = 350
}