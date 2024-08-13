package org.firstinspires.ftc.teamcode.Systems.camera

import com.acmerobotics.dashboard.FtcDashboard
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.cameraExposure
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.cameraGain
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_funcs.lom
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvPipeline
import org.openftc.easyopencv.OpenCvWebcam
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

class Camera(
        val name: String,
        val orientation: OpenCvCameraRotation,
        val resX: Int,
        val resY: Int,
        pipeline: OpenCvPipeline?,
        val streaming: Boolean,
        waitForOpen: Boolean,
        val gain: Int,
        val exposure: Int
) {

    constructor(
            name: String,
            orientation: OpenCvCameraRotation,
            resX: Int,
            resY: Int,
            pipeline: OpenCvPipeline?,
            streaming: Boolean,
            waitForOpen: Boolean,
            ) : this(name, orientation, resX, resY, pipeline, streaming, waitForOpen, cameraGain, cameraExposure)

    var camera: OpenCvWebcam
    var ecode: Int = 0
    var opened: Boolean = false
    private var dashboardStreaming = false

    init {
        val cameraMonitorViewId: Int = hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", lom.hardwareMap.appContext.packageName)
        val webcamName: WebcamName = hardwareMap.get(WebcamName::class.java, name)
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId)
        if (pipeline != null) {
            camera.setPipeline(pipeline)
        }

        dashboardStreaming = streaming
        val cameraListener = object : OpenCvCamera.AsyncCameraOpenListener {
            override fun onOpened() {
                if (exposure != 0) {
                    camera.exposureControl.mode = ExposureControl.Mode.Manual
                    sleep(50)
                    camera.exposureControl.setExposure(exposure.toLong(), TimeUnit.MILLISECONDS)
                }
                camera.gainControl.gain = gain

                camera.startStreaming(resX, resY, orientation, OpenCvWebcam.StreamFormat.YUY2)
                if (streaming) {
                    FtcDashboard.getInstance().startCameraStream(camera, 120.0)
                }
                opened = true
            }

            override fun onError(errorCode: Int) {
                ecode = errorCode
            }
        }

        camera.openCameraDeviceAsync(cameraListener)
        while (waitForOpen && !opened && !lom.isStopRequested && !lom.isStarted) {
            lom.telemetry.addLine("Waiting on cam open")
            lom.telemetry.update()
            sleep(5)
        }
        lom.telemetry.addLine("Cam opened")
        lom.telemetry.update()
    }

    fun stop() {
        if (opened) {
            camera.closeCameraDeviceAsync {
                opened = false
            }
        }
    }
}