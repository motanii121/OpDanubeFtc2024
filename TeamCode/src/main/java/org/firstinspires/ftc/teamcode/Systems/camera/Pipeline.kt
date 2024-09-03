package org.firstinspires.ftc.teamcode.Systems.camera


import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import org.firstinspires.ftc.teamcode.Algorithms.color_detection.RGBtoHSV
import org.firstinspires.ftc.teamcode.Algorithms.color_detection.isRightColor
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Autonomous.Vec4i
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.autocase
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.isRed
import org.firstinspires.ftc.teamcode.Math.formulas.abs
import org.firstinspires.ftc.teamcode.Math.formulas.angdiff
import org.firstinspires.ftc.teamcode.Math.formulas.max
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.COL_INDEX
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.CUR_DONE_CORRECTION
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.CameraMidPos
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.DO_I_EVEN_PROCESS_FRAME
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.DRAW_BOXES
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.DRAW_MEDIAN
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.autominblocks
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.framelength
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.framewidth
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.linepos
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.lineposred
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.offx
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.offy
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.patratepelatime
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.patratepelungime
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.redHue
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.redMaxHueVariation
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.redMinSaturation
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.redMinValue
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.squaresize
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.step
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.testx
import org.firstinspires.ftc.teamcode.Systems.camera.camera_vars.testy
import org.firstinspires.ftc.teamcode.Variables.system_funcs.lom
import org.firstinspires.ftc.teamcode.Variables.system_funcs.telemetryPacket
import org.firstinspires.ftc.teamcode.Variables.system_funcs.tp
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Rect
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.COLOR_RGB2HSV_FULL
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvPipeline
import java.lang.Math.PI
import java.time.Clock

class pipeline0 : OpenCvPipeline() {
    private val frame = Mat()
    private val ff = Mat()
    var midBlocks = 0
    var leftBlocks = 0
    var maxblocks = 600
    var case = 0
    override fun processFrame(input: Mat): Mat {
        if (input.empty()) {
            return input
        }
        input.copyTo(frame)
        Imgproc.cvtColor(frame, frame, COLOR_RGB2HSV_FULL)
        frame.copyTo(ff)
        for (i in 0..framelength - squaresize step squaresize) {
            for (j in 0..framewidth - squaresize step squaresize) {
                val patratul = frame[j, i] ?: continue
                if(isRed){
                    linepos = lineposred
                }
                if (isRightColor(patratul)) {
                    if (i < linepos) {
                        ++leftBlocks
                    } else {
                        ++midBlocks
                    }

                    Imgproc.rectangle(ff, Rect(i, j, squaresize, squaresize), Scalar(255.0, 255.0, 255.0), 2)
                } else {
                    Imgproc.rectangle(ff, Rect(i, j, squaresize, squaresize), Scalar(0.0, 0.0, 0.0), 1)
                }
            }
        }
//        autoupdate_tp(tp, "H", "${frame[testy, testx][0] / 255.0 * PI * 2}")
//        autoupdate_tp(tp, "S", "${frame[testy, testx][1]}")
        //autoupdate_tp(tp, "V", "${frame[testy, testx][2]}")
        //autoupdate_tp(tp, "The one ", abs(angdiff(frame[testy, testx][0] / 255.0 * PI * 2, redHue)))
        if (isRightColor(frame[testy, testx])) {
            Imgproc.rectangle(ff, Rect(testx, testy, squaresize, squaresize), Scalar(255.0, 0.0, 0.0), 2)
        } else {
            Imgproc.rectangle(ff, Rect(testx, testy, squaresize, squaresize), Scalar(0.0, 0.0, 0.0), 1)
        }

        Imgproc.line(ff, Point(linepos, 0.0), Point(linepos, 480.0), Scalar(255.0, 0.0, 0.0, 255.0), 5)
        autoupdate_tp(tp, "LEFTBLOCKS", "${leftBlocks}")
        autoupdate_tp(tp, "MIDBLOCKS", "${midBlocks}")
        autoupdate_tp(tp, "LEFTBLOCKS>MAXBLOCKS", leftBlocks > maxblocks)
        autoupdate_tp(tp, "MIDBLOCKS>MAXBLOCKS", midBlocks > maxblocks)
        if(!isRed){
            if(midBlocks > maxblocks){
                case = 1
            }
            else if(leftBlocks > maxblocks){
                case = 0
            }
            else{
                case = 2
            }
        }
        else{
            if(midBlocks > maxblocks){
                case = 0
            }
            else if(leftBlocks > maxblocks){
                case = 1
            }
            else{
                case = 2
            }
        }
        autoupdate_tp("CASEEEEEE", case)
        return ff
    }

    fun resetblocks(){
        midBlocks = 0
        leftBlocks = 0
        case = 2
    }
}
