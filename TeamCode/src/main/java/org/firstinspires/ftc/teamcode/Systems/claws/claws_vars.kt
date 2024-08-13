package org.firstinspires.ftc.teamcode.Systems.claws

import com.acmerobotics.dashboard.config.Config

@Config
object claws_vars {
    @JvmField
    var lclawOpenPos: Double = 0.3
    @JvmField
    var lclawClosePos: Double = 0.81 //0.8
    @JvmField
    var rclawOpenPos: Double = 0.6
    @JvmField
    var rclawClosePos: Double = 0.09 //0.1
    @JvmField
    var clawRotateStep: Double = 0.06
    @JvmField
    var clawRotateMaxPos: Double = 0.6
}