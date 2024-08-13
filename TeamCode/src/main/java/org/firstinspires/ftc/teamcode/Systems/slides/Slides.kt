package org.firstinspires.ftc.teamcode.Systems.slides

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.Algorithms.PIDF
import org.firstinspires.ftc.teamcode.Systems.slides.slides_vars.ltargetposition
import org.firstinspires.ftc.teamcode.Systems.slides.slides_vars.rtargetposition
import org.firstinspires.ftc.teamcode.Systems.slides.slides_vars.slideupForce
import org.firstinspires.ftc.teamcode.Systems.slides.slides_vars.tolerance

import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_funcs.lom
import org.firstinspires.ftc.teamcode.Variables.system_funcs.tp


class Slides {
    val lslide = hardwareMap.dcMotor.get("LSLIDE")
    val rslide = hardwareMap.dcMotor.get("RSLIDE")
   // val encoder = Encoder("LSLIDE", -1)

    //val pos: Int
    //    get() = encoder.pos


    fun init(){
        lslide.mode = DcMotor.RunMode.RUN_USING_ENCODER
        rslide.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }

    fun run(){
        rslide.power = lom.gamepad2.right_stick_y.toDouble()
        lslide.power = -lom.gamepad2.right_stick_y.toDouble()
    }

    fun up(){
        rslide.power = -1.0
        lslide.power = 1.0
    }

    fun down(){
        rslide.power = 1.0
        lslide.power = -1.0
    }

    fun stop(){
        rslide.power = 0.0
        lslide.power = 0.0
    }

    fun runwithpid(lpid: PIDF, rpid: PIDF){
        if(rtargetposition - rslide.currentPosition < tolerance && ltargetposition - lslide.currentPosition < tolerance){
            rslide.power = slideupForce
            lslide.power = slideupForce
        }
        if(rtargetposition - rslide.currentPosition > tolerance && ltargetposition - lslide.currentPosition > tolerance){
            rslide.power = rpid.update(rtargetposition - rslide.currentPosition)
            lslide.power = lpid.update(ltargetposition - lslide.currentPosition)
        }

    }
}