package org.firstinspires.ftc.teamcode.Autonomous

import android.graphics.Color
import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.configuration.LynxConstants
import org.firstinspires.ftc.teamcode.Algorithms.chain_actioner
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.autocase
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.isRed
import org.firstinspires.ftc.teamcode.CommandBase.Command
import org.firstinspires.ftc.teamcode.CommandBase.commands
import org.firstinspires.ftc.teamcode.Localizer.ThreeWheelLocalizer
import org.firstinspires.ftc.teamcode.PurePursuit.purepursuit
import org.firstinspires.ftc.teamcode.Systems.ThreadedIMU
import org.firstinspires.ftc.teamcode.Systems.camera.Camera
import org.firstinspires.ftc.teamcode.Systems.camera.Pipeline
import org.firstinspires.ftc.teamcode.Variables.system_funcs
import org.firstinspires.ftc.teamcode.Variables.system_funcs.arm
import org.firstinspires.ftc.teamcode.Variables.system_funcs.camera
import org.firstinspires.ftc.teamcode.Variables.system_funcs.controlHub
import org.firstinspires.ftc.teamcode.Variables.system_funcs.currentcommand
import org.firstinspires.ftc.teamcode.Variables.system_funcs.dash
import org.firstinspires.ftc.teamcode.Variables.system_funcs.expansionHub
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_funcs.imew
import org.firstinspires.ftc.teamcode.Variables.system_funcs.init_systems
import org.firstinspires.ftc.teamcode.Variables.system_funcs.init_teleop
import org.firstinspires.ftc.teamcode.Variables.system_funcs.localizer
import org.firstinspires.ftc.teamcode.Variables.system_funcs.lom
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pipeline
//import org.firstinspires.ftc.teamcode.Variables.system_funcs.pipeline
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.telemetryPacket
import org.firstinspires.ftc.teamcode.Variables.system_funcs.tp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.update
import org.firstinspires.ftc.teamcode.hardware.Controller
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraRotation
import java.lang.Thread.sleep

object auto_funcs {

    fun setupAuto(lom: LinearOpMode, path: Command?) {
        init_teleop(lom)
        autoupdate_tp("CASE", autocase)
        autoupdate_tp("ISRED", isRed)
        camera.stop()
        arm.fourbar.position = 1.0
        sleep(500)
        arm.fourbar.position = 0.7
        var runningcommand = path
        while (!lom.isStopRequested) {

            if(runningcommand != null){
                if(runningcommand.run(telemetryPacket)){
                    runningcommand = null
                }
            }
            pp.update()
            update()
        }
    }
}