package org.firstinspires.ftc.teamcode.Variables

import android.graphics.Color
import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.configuration.LynxConstants
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Autonomous.Pose
import org.firstinspires.ftc.teamcode.CommandBase.Command
import org.firstinspires.ftc.teamcode.CommandBase.commands
import org.firstinspires.ftc.teamcode.Localizer.Localizer
import org.firstinspires.ftc.teamcode.Localizer.ThreeWheelLocalizer
import org.firstinspires.ftc.teamcode.PurePursuit.purepursuit
import org.firstinspires.ftc.teamcode.Systems.arm.Arm
import org.firstinspires.ftc.teamcode.Systems.camera.Camera
import org.firstinspires.ftc.teamcode.Systems.claws.Claws
import org.firstinspires.ftc.teamcode.Systems.drivetrain.Drivetrain
import org.firstinspires.ftc.teamcode.Systems.drone.Drone_launcher
import org.firstinspires.ftc.teamcode.Systems.intake.Intake
import org.firstinspires.ftc.teamcode.Systems.slides.Slides
import org.firstinspires.ftc.teamcode.Systems.ThreadedIMU
import org.firstinspires.ftc.teamcode.Systems.camera.Pipeline
import org.firstinspires.ftc.teamcode.Systems.camera.pipeline0
import org.firstinspires.ftc.teamcode.Systems.colorsensor.ColorSensor
//import org.firstinspires.ftc.teamcode.Systems.camera.Pipeline
import org.firstinspires.ftc.teamcode.hardware.Controller
import org.openftc.easyopencv.OpenCvCameraRotation

object system_funcs {

    lateinit var hardwareMap: HardwareMap
    lateinit var lom: LinearOpMode
    lateinit var controller: Controller
    lateinit var dash: FtcDashboard
    lateinit var tp: Telemetry  //tp stands for telemetry packet
    lateinit var claws: Claws
    lateinit var drivetrain: Drivetrain
    lateinit var droneLauncher: Drone_launcher
    lateinit var intake: Intake
    lateinit var slides: Slides
    lateinit var arm: Arm
    lateinit var imew: ThreadedIMU
    lateinit var controlHub: LynxModule
    lateinit var expansionHub: LynxModule
    //lateinit var batteryVoltageSensor: PhotonLynxVoltageSensor
    lateinit var camera: Camera
    lateinit var pipeline: pipeline0
    lateinit var localizer: ThreeWheelLocalizer
    lateinit var telemetryPacket: TelemetryPacket
    var firstopen: Boolean = true
    lateinit var pp: purepursuit
    var currentcommand: Command? = null
    lateinit var sensor: ColorSensor

    fun init_teleop(lom_real: LinearOpMode){
        lom = lom_real
        hardwareMap = lom.hardwareMap

        val lynxModules = hardwareMap.getAll(LynxModule::class.java)
        for (module in lynxModules) {
            module.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL
        }
        if (lynxModules[0].isParent && LynxConstants.isEmbeddedSerialNumber(lynxModules[0].serialNumber)) {
            controlHub = lynxModules[0]
            expansionHub = lynxModules[1]
        } else {
            controlHub = lynxModules[1]
            expansionHub = lynxModules[0]
        }
        controlHub.setConstant(Color.rgb(221, 168, 255))
        expansionHub.setConstant(Color.rgb(255, 0,0))
     //   batteryVoltageSensor = hardwareMap.getAll(PhotonLynxVoltageSensor::class.java).iterator().next()

        dash = FtcDashboard.getInstance()
        tp = dash.telemetry
        controller = Controller()
        autoupdate_tp(tp, "IMEW", "A MIMIR")
        imew = ThreadedIMU("imu")
        autoupdate_tp(tp, "IMEW", "AWAKE")
        imew.init()
        imew.initThread()
        telemetryPacket = TelemetryPacket()

        init_systems()
        pipeline = pipeline0()
        camera = Camera("Webcam 1", OpenCvCameraRotation.UPRIGHT, 640, 480, pipeline, streaming = true, waitForOpen = true)
        pp = purepursuit()
    }

    fun init_systems(){
        localizer = ThreeWheelLocalizer()
        drivetrain = Drivetrain()
        drivetrain.init()
        intake = Intake()
        intake.init()
        droneLauncher = Drone_launcher()
        droneLauncher.init()
        claws = Claws()
        claws.initpos()
        arm = Arm()
        arm.init()
        sensor = ColorSensor()
        slides = Slides()
    }

    private val et = ElapsedTime()
    fun update() {
        autoupdate_tp("Framerate", 1 / et.seconds())
        et.reset()
        controlHub.clearBulkCache()
        expansionHub.clearBulkCache()
        //telemetryPacket.addLine(localizer.robotpose.x.toString() + " " + localizer.robotpose.y.toString() + " " + localizer.robotpose.heading.toString())
        localizer.update()
    }
}