package org.firstinspires.ftc.teamcode.Autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Autonomous.auto_funcs.setupAuto
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.autocase
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.isRed
import org.firstinspires.ftc.teamcode.CommandBase.Command
import org.firstinspires.ftc.teamcode.CommandBase.commands
import org.firstinspires.ftc.teamcode.Variables.system_funcs.arm
import org.firstinspires.ftc.teamcode.Variables.system_funcs.camera
import org.firstinspires.ftc.teamcode.Variables.system_funcs.claws
import org.firstinspires.ftc.teamcode.Variables.system_funcs.init_teleop
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pipeline
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.telemetryPacket
import org.firstinspires.ftc.teamcode.Variables.system_funcs.update
var runningcommand: Command? = null
var case: Int = 2
@Autonomous
class linetest: LinearOpMode(){
    //override fun runOpMode() = setupAuto(this, path_planner.test_linie(test_linie) )
    override fun runOpMode() {
        init_teleop(this)
        claws.grab()
        runningcommand = commands.preload()
        autoupdate_tp("CASE", autocase)
        var case = autocase
        autoupdate_tp("CASE2", pipeline.case)

        waitForStart()
        camera.stop()
        while(!isStopRequested && opModeIsActive()){
            if(runningcommand != null){
                if(runningcommand!!.run(telemetryPacket)){
                    runningcommand = null
                    autoupdate_tp("COMMAND", "NULLIFIED")
                }
            }
            pp.update()
            update()
        }
    }
}

@Autonomous
class red: LinearOpMode(){

    override fun runOpMode() {
        isRed = true
        setupAuto(this, commands.auto())
    }
}

@Autonomous
class blue: LinearOpMode(){
    override fun runOpMode() {
        isRed = false
        setupAuto(this, commands.auto())
    }
}