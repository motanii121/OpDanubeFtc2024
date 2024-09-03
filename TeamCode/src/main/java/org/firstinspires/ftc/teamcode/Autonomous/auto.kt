package org.firstinspires.ftc.teamcode.Autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Autonomous.auto_funcs.setupAuto
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.autocase
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.isRed
import org.firstinspires.ftc.teamcode.CommandBase.Command
import org.firstinspires.ftc.teamcode.CommandBase.auto_sequences_blue
import org.firstinspires.ftc.teamcode.CommandBase.auto_sequences_red
import org.firstinspires.ftc.teamcode.CommandBase.commands
import org.firstinspires.ftc.teamcode.CommandBase.parkblue
import org.firstinspires.ftc.teamcode.Systems.slides.Slides
import org.firstinspires.ftc.teamcode.Variables.system_funcs.arm
import org.firstinspires.ftc.teamcode.Variables.system_funcs.camera
import org.firstinspires.ftc.teamcode.Variables.system_funcs.claws
import org.firstinspires.ftc.teamcode.Variables.system_funcs.init_teleop
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pipeline
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.slides
import org.firstinspires.ftc.teamcode.Variables.system_funcs.telemetryPacket
import org.firstinspires.ftc.teamcode.Variables.system_funcs.update
var runningcommand: Command? = null
var case: Int = 2
@Autonomous
class linetest: LinearOpMode(){
    //override fun runOpMode() = setupAuto(this, path_planner.test_linie(test_linie) )
    override fun runOpMode(){
        init_teleop(this, true)
        runningcommand = auto_sequences_blue.preload2blue()
        waitForStart()
        camera.stop()
        while (!isStopRequested) {
            if(runningcommand != null){
                if(runningcommand!!.run(telemetryPacket)){
                    runningcommand = null
                }
            }
            pp.update()
            update()
        }
    }
}

@Autonomous
class red: LinearOpMode(){
    var ep = ElapsedTime()
    override fun runOpMode() {
        init_teleop(this, true)
        while(opModeInInit()) {
            if(ep.seconds() == 3.0){
                pipeline.midBlocks = 0
                pipeline.leftBlocks = 0
                pipeline.case = 2
                ep.reset()
            }
            runningcommand = if (pipeline.case == 0) auto_sequences_red.preload0() else if (pipeline.case == 1) auto_sequences_red.preload1() else auto_sequences_red.preload2()
            if(runningcommand == auto_sequences_red.preload0()){
                telemetry.addData("Trajectory:", "is preload0")
            }
            if(runningcommand == auto_sequences_red.preload1()){
                telemetry.addData("Trajectory:", "is preload1")
            }
            if(runningcommand == auto_sequences_red.preload2()){
                telemetry.addData("Trajectory:", "is preload2")
            }
            telemetry.addData("Case: ", pipeline.case);
            telemetry.update()
        }
        waitForStart()
        camera.stop()
        while (!isStopRequested) {
            if(runningcommand != null){
                if(runningcommand!!.run(telemetryPacket)){
                    runningcommand = null
                }
            }
            pp.update()
            update()
        }
    }
}

@Autonomous
class blue: LinearOpMode(){
    override fun runOpMode() {
        isRed = false
        init_teleop(this, true)
        while(opModeInInit()){

            telemetry.addData("Case: ", pipeline.case);
            telemetry.update()
        }
        runningcommand = parkblue.parkblue()
        waitForStart()
        camera.stop()
        while (!isStopRequested) {
            if(runningcommand != null){
                if(runningcommand!!.run(telemetryPacket)){
                    runningcommand = null
                }
            }
            pp.update()
            update()
        }
    }
}

