package org.firstinspires.ftc.teamcode.Systems.intake

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.posdiff
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.intakeMotorPower
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.intakePosFour
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.intakePosThree
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.intakePosTop
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.intakePosTwo
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.lidClosePos
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.lidOpenPos
import org.firstinspires.ftc.teamcode.Variables.system_funcs
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_funcs.lom
import org.firstinspires.ftc.teamcode.Variables.system_funcs.tp
import org.firstinspires.ftc.teamcode.Variables.system_vars
import org.firstinspires.ftc.teamcode.Variables.system_vars.intakeInit

class Intake {

    val intakeMotor = hardwareMap.dcMotor.get("INTAKE")
    val intakeServo = hardwareMap.servo.get("INTAKEPOS")
    val lidServo = hardwareMap.servo.get("LID")


    fun init(){
        intakeMotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        intakeServo.position = 0.2
        lidServo.position = lidClosePos
        //
    // autoupdate_tp(tp, "INTAKE", "INITIALIZED")
    }

    fun take(){
       // intakeServo.position = intakeInit
        intakeMotor.power = 1.0
      //  autoupdate_tp("INTAKE", "DA")
    }

    fun spit(){
      //  intakeServo.position = intakeInit
        intakeMotor.power = -1.0
     //   autoupdate_tp("INTAKE", "DA")
    }


    fun stop(){
        intakeMotor.power = 0.0
    }

    var position: Int = 5
    fun changepos(){
        intakeServo.position = when (position) {
            5 -> intakePosTop
            4 -> intakePosFour
            3 -> intakePosThree
            2 -> intakePosTwo
            else -> intakeInit
        }
        --position
        if (position == 0) {
            position = 5
        }
    }

    fun changelidpos(){
        if(posdiff(lidServo.position, lidClosePos))
            lidServo.position = lidOpenPos
        else{
            lidServo.position = lidClosePos
        }
    }
}