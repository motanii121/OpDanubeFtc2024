package org.firstinspires.ftc.teamcode.Systems.arm

import org.firstinspires.ftc.teamcode.Algorithms.motionprofiler
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarPreload
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarfinalpos
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarinit
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmDown
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmPreload
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmUp
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.rarmDown
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.rarmPreload
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.rarmUp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_funcs.tp
import org.firstinspires.ftc.teamcode.Variables.system_vars.MPdownAcceleration
import org.firstinspires.ftc.teamcode.Variables.system_vars.MPdownDecceleration
import org.firstinspires.ftc.teamcode.Variables.system_vars.MPdownVelocity
import org.firstinspires.ftc.teamcode.Variables.system_vars.MPinitAcceleration
import org.firstinspires.ftc.teamcode.Variables.system_vars.MPinitDecceleration
import org.firstinspires.ftc.teamcode.Variables.system_vars.MPinitVelocity
import org.firstinspires.ftc.teamcode.Variables.system_vars.MPupAcceleration
import org.firstinspires.ftc.teamcode.Variables.system_vars.MPupDecceleration
import org.firstinspires.ftc.teamcode.Variables.system_vars.MPupVelocity
import org.firstinspires.ftc.teamcode.Variables.system_vars.larmInit
import org.firstinspires.ftc.teamcode.Variables.system_vars.rarmInit

class Arm {

    val rarm = hardwareMap.servo.get("RARM")
    val larm = hardwareMap.servo.get("LARM")
    val fourbar = hardwareMap.servo.get("V4B")

    private val MPup = motionprofiler(MPupVelocity, MPupAcceleration, MPupDecceleration)
    private val MPinit = motionprofiler(MPinitVelocity, MPinitAcceleration, MPinitDecceleration)
    private val MPdown = motionprofiler(MPdownVelocity, MPdownAcceleration, MPdownDecceleration)

    var isUp = false
    var isInit = true
    var isDown = false

    fun init(){
        fourbar.position = 0.4
        rarm.position = rarmInit
        larm.position = larmInit
    }

    fun goDown(){
        //fourbar.position = fourbarinit
        rarm.position = rarmDown
        larm.position = larmDown
        isUp = false
        isInit = false
        isDown = true
        //autoupdate_tp(tp, "brat jos", "yurrrr")
    }

    fun goUp(){
        rarm.position = rarmUp
        larm.position = larmUp
        isUp = true
        isInit = false
        isDown = false
        //autoupdate_tp(tp, "brat sus", "yurrrr")
    }

    fun goInit(){
        rarm.position = rarmInit
        larm.position = larmInit
        isUp = false
        isInit = true
        isDown = false
    }

    fun goPreloadDown(){
        rarm.position = rarmPreload
        larm.position = larmPreload
        //fourbar.position = fourbarPreload
    }

    fun goYellowDown(){
        rarm.position = 0.78
        larm.position = 0.16
        fourbar.position = 0.75
    }

}