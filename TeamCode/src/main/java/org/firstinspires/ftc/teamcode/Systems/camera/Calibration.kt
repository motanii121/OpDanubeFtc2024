package org.firstinspires.ftc.teamcode.Systems.camera

import android.util.Size
import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.vision.VisionPortal


@Config
@Autonomous(name = "Vision Calibration Blue Close")
class VisionCalibrationBlueClose : LinearOpMode() {
    private var portal: VisionPortal? = null
    private var processor: PropDetectionBlueClose? = null

    @Throws(InterruptedException::class)
    override fun runOpMode() {
        processor = PropDetectionBlueClose()
        portal = VisionPortal.Builder()
            .setCamera(hardwareMap.get<WebcamName>(WebcamName::class.java, "Webcam 1"))
            .setCameraResolution(Size(640, 480))
            .setCamera(BuiltinCameraDirection.BACK)
            .addProcessor(processor)
            .build()
        while (opModeInInit() && !isStopRequested) {
            telemetry.addData("Detection", processor!!.detection)
            telemetry.addData("Left value", processor!!.leftSum)
            telemetry.addData("Middle value", processor!!.middleSum)
            telemetry.update()
        }
        waitForStart()
    }
}