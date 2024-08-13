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

class pipelinedarscrisdeivi(resolutionx: Int, resolutiony: Int) : OpenCvPipeline() {

    /*
    //iti dau eu sa citesti si sa iti explic cate ceva despre documentatia de pipeline, opencv si frameuri
    //functia in care lucrez si verific lucrurile din frame (de acum, mat = frameul cu care lucrez)
    val frametohsv = Mat()
        val finalframe = Mat()
        override fun processFrame(input: Mat): Mat {

            if (input.empty()) {
                return input
            }
            Imgproc.cvtColor(input, frametohsv, Imgproc.COLOR_RGB2HSV)

                //aici retin cate patrate de culoarea pe care o vreau sunt in fiecare parte
            var patrateincentru: Int = 0
            var patrateladreapta: Int = 0

                //aici imi pun patratele in grila, ca sa intelegi, o sa iti arat o foaie [CAND VII LA ROBO]
            for (patratpelungime in -patratepelungime + offx..patratepelungime + offx step step) {
                for (patratpelatime in -patratepelatime + offy..patratepelatime + offy step step) {

                    //patratul efectiv
                    val patrat = frametohsv[patratpelatime, patratpelungime] ?: continue
                    val tp = TelemetryPacket()
                    tp.put("patrat", "${patrat[0]/ 255.0 * PI * 2}, ${patrat[1]}, ${patrat[2]}")
                    FtcDashboard.getInstance().sendTelemetryPacket(tp)

                    //verific culoarea
                    if (operatiicupatrate.verificaculoarea(patrat)) {
                            //verific unde sunt patratele in functie de o linie in care impart frameul
                        if (patratpelungime > cazuldemijloc)
                                patrateladreapta++
                        else
                                patrateincentru++

                            //daca desenez patrate, le pun culorile respective (daca am patrat de culoare dorita, il pun pe alb
                        if (desenezpatrate)
                            Imgproc.rectangle(
                                    finalframe,
                                    Rect(patratpelungime, patratpelatime, step, step),
                                    Scalar(255.0, 255.0, 255.0), -1
                            )

                        } else {
                            //daca nu e ce culoare vreau dar totusi desenez patrate, desenez patratul de culoare neagra
                            if (desenezpatrate) {
                                Imgproc.rectangle(
                                    finalframe,
                                    Rect(patratpelungime, patratpelatime, step, step),
                                    Scalar(
                                        max(patrat[0] - 10.0, 0.0),
                                        max(patrat[0] - 10.0, 0.0),
                                        max(patrat[0] - 10.0, 0.0),
                                    ),
                                    -1
                                )
                            }

                        }

                        lom.telemetry.addData("Patrate in Mijloc", patrateincentru)
                        lom.telemetry.addData("Patrate in centru", patrateladreapta)
                        lom.telemetry.update()
                        Imgproc.line(finalframe, Point(0.0, 100.0), Point(100.0, 100.0),Scalar(255.0, 0.0, 0.0), 100 )

                        //autoresultul, iti explic de ce sunt cum sunt cazurile [CAND VII LA ROBO]
                        autocase = if (autored) {
                            if (patrateladreapta > patrateminrosii)
                                0
                            else if (patrateincentru > patrateminrosii)
                                1
                            else
                                2
                        } else {
                            if (patrateladreapta > patrateminalbastre)
                                2
                            else if (patrateincentru > patrateminalbastre)
                                1
                            else
                                0
                        }

                        lom.telemetry.addData("GOT RESULTS: ", autoresult)
                        lom.telemetry.update()

                        //linia care imi imparte cazurile (dreapta centru stanga)
                        val w = frametohsv.width()
                        Imgproc.line(
                            finalframe,
                            Point(cazuldemijloc, 80.0),
                            Point(cazuldemijloc, 320.0),
                            Scalar(182.0, 23.0, 240.0),
                            4
                        )
                    }
                }

                return if (desenezpatrate && !finalframe.empty()) {
                    finalframe
                } else {
                    frametohsv
                }
            } else {
                return input
            }



        }
    }

    object operatiicupatrate {
        fun verificaculoarea(patrat: DoubleArray): Boolean {
            return if (autored) {
                isred(patrat)
            } else {
                isblue(patrat)
            }
        }

        fun isred(patrat: DoubleArray): Boolean {
            //transform culorile primite din rgb in hsv o sa iti arat cum functioneaza [CAND VII LA ROBO]

            val h = (patrat[0] / 255.0) * PI * 2
            val s = patrat[1]
            val v = patrat[2]

            return abs(angDiff(h, rosuAng)) <= rosuMaxDif &&
                    s > saturatierosieminima &&
                    v > valoarerosieminima
        }

        fun isblue(patrat: DoubleArray): Boolean {

            //transform culorile primite din rgb in hsv o sa iti arat cum functioneaza [CAND VII LA ROBO]
            val diferentadeunghialbastruinstanga: Double = PI / 6
            val diferentadeunghialbastruindreapta: Double = PI / 12


            val h = (patrat[0] / 255.0) * PI * 2
            val s = patrat[1]
            val v = patrat[2]

            return abs(angDiff(h, alabastruAng)) <= alabastruMaxDiff &&
                    s > saturatiealbastraminima &&
                    v > valoarealbastramaxima
        }
        fun floatMod(o1: Double, o2: Double): Double {
            return o1 - floor(o1 / o2) * o2
        }
        fun angDiff(o1: Double, o2: Double): Double {
            return floatMod(o2 - o1 + PI, PI * 2) - PI
        }

        */
    override fun processFrame(input: Mat?): Mat {
        TODO("Not yet implemented")
    }
}

class Pipeline : OpenCvPipeline() {

    private val frame = Mat()
    private val ff = Mat()

    private fun getcase(midBlocks: Int, rightBlocks: Int): Int {
        return if (midBlocks > autominblocks && rightBlocks > autominblocks) {
            if (midBlocks > rightBlocks) 1 else 2
        } else {
            if (midBlocks > autominblocks) {
                1
            } else if (rightBlocks > autominblocks) {
                2
            } else {
                0
            }
        }
    }

    private fun squareify(p: Vec4i): Int {
        var res = 0
        for (x in p.a..p.a + p.c) {
            for (y in p.b..p.b + p.d) {
                val vl = frame[y, x] ?: continue
                if (isRightColor(vl)) {
                    Imgproc.rectangle(ff, Rect(x, y, 1, 1), Scalar(255.0, 255.0, 255.0), -1)
                    ++res
                } else {
                    if (DRAW_BOXES) {
                        Imgproc.rectangle(
                            ff, Rect(x, y, 1, 1), Scalar(
                                kotlin.math.max(vl[COL_INDEX] - 10.0, 0.0),
                                kotlin.math.max(vl[COL_INDEX] - 10.0, 0.0),
                                kotlin.math.max(vl[COL_INDEX] - 10.0, 0.0)
                            ), -1
                        )
                    }
                }
            }
        }
        return res
    }

    override fun processFrame(input: Mat): Mat {
        if (input.empty()) {
            return input
        }
        if (DO_I_EVEN_PROCESS_FRAME) {
            input.copyTo(frame)
            Imgproc.cvtColor(frame, frame, COLOR_RGB2HSV_FULL)

            if (DRAW_BOXES || DRAW_MEDIAN) {
                frame.copyTo(ff)
            }

            var medXS = 0
            var redc = 0

            var midBlocks = 0
            var rightBlocks = 0

            run {
                val f = frame[-patratepelatime + offx, -patratepelungime + offy]
                autoupdate_tp(tp, "test", "${f[0] * PI * 2.0 / 255.0} ${f[1]} ${f[2]}")
            }

            for (cx in -patratepelatime + offx..patratepelatime + offx step step) {
                for (cy in -patratepelungime + offy..patratepelungime + offy step step) {
                    val vl = frame[cy, cx] ?: continue
                    if (isRightColor(vl)) {
                        if (cx < CameraMidPos) {
                            ++midBlocks
                        } else {
                            ++rightBlocks
                        }
                        medXS += cx
                        ++redc
                        if (DRAW_BOXES) {
                            Imgproc.rectangle(
                                ff,
                                Rect(cx, cy, step, step),
                                Scalar(255.0, 255.0, 255.0),
                                -1
                            )
                        }
                    } else {
                        if (DRAW_BOXES) {
                            Imgproc.rectangle(
                                ff, Rect(cx, cy, step, step), Scalar(
                                    max(vl[COL_INDEX] - 10.0, 0.0),
                                    max(vl[COL_INDEX] - 10.0, 0.0),
                                    max(vl[COL_INDEX] - 10.0, 0.0)
                                ), -1
                            )
                        }
                    }
                }
            }

            lom.telemetry.addData("MidBoxes", midBlocks)
            lom.telemetry.addData("RightBoxes", rightBlocks)
            val curr = getcase(midBlocks, rightBlocks)
            autocase = curr

            val w = frame.width()
            Imgproc.line(
                ff,
                Point(CameraMidPos, 80.0),
                Point(CameraMidPos, 380.0),
                Scalar(255.0, 0.0, 0.0, 255.0),
                4
            )

            if (DRAW_MEDIAN && !ff.empty()) {
                val c1 = Point(w / 2.0 + CUR_DONE_CORRECTION * 20, 10.0)
                val c2 = Point(w / 2.0, 10.0)

                Imgproc.line(ff, c1, c2, Scalar(100.0, if (autocase == 0) 0.0 else 100.0, if (autocase == 1) 0.0 else 100.0, 255.0),
                    9
                )
            }

            return if ((DRAW_BOXES || DRAW_MEDIAN) && !ff.empty()) {
                ff
            } else {
                frame
            }
        } else {
            return input
        }
    }

}

class pipeline0 : OpenCvPipeline() {
    private val frame = Mat()
    private val ff = Mat()
    private var midBlocks = 0
    private var leftBlocks = 0
    private var maxblocks = 1000
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
}
