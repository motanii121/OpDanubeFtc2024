package org.firstinspires.ftc.teamcode.Systems.camera

import android.graphics.Canvas
import com.acmerobotics.dashboard.config.Config
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration
import org.firstinspires.ftc.vision.VisionProcessor
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Rect
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc

//thank u buru & pachy & the whole cyliis u are all awesome
@Config
class PropDetectionBlueClose : VisionProcessor {
    var detection = 2
    var leftSum = 0.0
    var middleSum = 0.0
    var workingMat = Mat()
    override fun init(width: Int, height: Int, calibration: CameraCalibration?) {}

    override fun processFrame(frame: Mat, captureTimeNanos: Long): Any? {
        Imgproc.cvtColor(frame, workingMat, Imgproc.COLOR_BGR2HSV)
        val leftRect = Rect(Point(leftRectX1.toDouble(), leftRectY1.toDouble()), Point(leftRectX2.toDouble(),
            leftRectY2.toDouble()
        ))
        val middleRect = Rect(Point(middleRectX1.toDouble(), middleRectY1.toDouble()), Point(
            middleRectX2.toDouble(), middleRectY2.toDouble()
        ))
        val lowThresh = Scalar(blueLowH.toDouble(), blueLowS.toDouble(), blueLowV.toDouble())
        val highThresh = Scalar(blueHighH.toDouble(), blueHighS.toDouble(), blueHighV.toDouble())
        Core.inRange(workingMat, lowThresh, highThresh, workingMat)
        leftSum = Core.sumElems(workingMat.submat(leftRect)).`val`[0]
        middleSum = Core.sumElems(workingMat.submat(middleRect)).`val`[0]
        Imgproc.rectangle(frame, leftRect, Scalar(0.0, 255.0, 0.0), 5)
        Imgproc.rectangle(frame, middleRect, Scalar(0.0, 255.0, 0.0), 5)
        detection =
            if (leftSum > leftThresh) 1 else if (middleSum > middleThresh) 2 else 3

//        workingMat.copyTo(frame);
        workingMat.release()
        return null
    }

    override fun onDrawFrame(
        canvas: Canvas?,
        onscreenWidth: Int,
        onscreenHeight: Int,
        scaleBmpPxToCanvasPx: Float,
        scaleCanvasDensity: Float,
        userContext: Any?
    ) {
    }

    companion object {
        var leftRectX1 = 470
        var leftRectY1 = 220
        var leftRectX2 = 570
        var leftRectY2 = 330
        var leftThresh = 800000.0
        var middleRectX1 = 130
        var middleRectY1 = 240
        var middleRectX2 = 220
        var middleRectY2 = 330
        var middleThresh = 600000.0
        var blueLowH = 110
        var blueLowS = 160
        var blueLowV = 0
        var blueHighH = 125
        var blueHighS = 255
        var blueHighV = 255
    }
}