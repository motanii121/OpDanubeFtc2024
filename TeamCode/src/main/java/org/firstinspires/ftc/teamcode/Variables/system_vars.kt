package org.firstinspires.ftc.teamcode.Variables

import com.acmerobotics.dashboard.config.Config

@Config
object system_vars {

    //init
    @JvmField
    var clawRotateInit: Double = 0.5
    @JvmField
    var intakeInit: Double = 0.81

    @JvmField
    var droneInit: Double = 0.5
    @JvmField
    var larmInit: Double = 0.31 //0.31;firul rosu /portul 5

    @JvmField
    var rarmInit: Double = 0.46 //0.46; firul albastru 0.465 putintel mai jos sau 0.47 putin mai sus /portul 4

    @JvmField
    var larmUp: Double = 0.20
    @JvmField
    var larmDown: Double = 0.48

    @JvmField
    var rarmUp : Double = 0.75
    @JvmField
    var rarmDown: Double = 0.5


    //generals
    @JvmField
    var trigtresh: Double = 0.02

    var imuoffset: Double = 0.0



    @JvmField
    var MPupVelocity: Double = 3.0
    @JvmField
    var MPupAcceleration: Double = 8.0
    @JvmField
    var MPupDecceleration: Double = 1.5

    @JvmField
    var MPdownVelocity: Double = 4.0
    @JvmField
    var MPdownAcceleration: Double  = 6.0
    @JvmField
    var MPdownDecceleration: Double = 1.3

    @JvmField
    var MPinitVelocity: Double = 3.5
    @JvmField
    var MPinitAcceleration: Double = 7.0
    @JvmField
    var MPinitDecceleration: Double = 1.4

    @JvmField
    var equalizercoef: Double = 0.95

}