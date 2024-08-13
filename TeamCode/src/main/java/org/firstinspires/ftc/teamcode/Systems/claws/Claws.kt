package org.firstinspires.ftc.teamcode.Systems.claws

import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.clawRotateMaxPos
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.clawRotateStep
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.lclawClosePos
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.lclawOpenPos
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.rclawClosePos
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.rclawOpenPos
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_vars.clawRotateInit

class Claws {

    val lclaw = hardwareMap.servo.get("LCLAW")
    val rclaw = hardwareMap.servo.get("RCLAW")
    val rotator = hardwareMap.servo.get("ROTATION")

    fun initpos(){
        lclaw.position = lclawOpenPos
        rclaw.position = rclawOpenPos
        rotator.position = clawRotateInit
    }
    fun grab(){
        lclaw.position = lclawClosePos
        rclaw.position = rclawClosePos
    }

    fun droppurple(){
        lclaw.position = lclawOpenPos
    }

    fun dropyellow(){
        rclaw.position = rclawOpenPos
    }

    fun rotate(){
        rotator.position += clawRotateStep
        if(rotator.position > clawRotateMaxPos){
            rotator.position = clawRotateInit
        }
    }

    fun drop(){
        lclaw.position = lclawOpenPos
        rclaw.position = rclawOpenPos
    }
}