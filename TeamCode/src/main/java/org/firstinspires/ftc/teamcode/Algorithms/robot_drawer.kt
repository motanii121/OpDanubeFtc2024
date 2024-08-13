package org.firstinspires.ftc.teamcode.Algorithms

import org.firstinspires.ftc.teamcode.Variables.draw_vars.drawscale
import org.firstinspires.ftc.teamcode.PurePursuit.purepursuit_vars.radius
import org.firstinspires.ftc.teamcode.Variables.system_funcs.localizer
import org.firstinspires.ftc.teamcode.Variables.system_funcs.telemetryPacket
import java.lang.Math.cos
import java.lang.Math.sin

class robot_drawer {

    fun drawRobot() {
        val canvas = telemetryPacket.fieldOverlay()
        canvas.setStrokeWidth(1)
        canvas.setStroke("#FF00C3")
        val lp = localizer.robotpose
        canvas.strokeCircle(lp.x * drawscale, lp.y * drawscale, radius)
        canvas.setStroke("#00FFC3")
        canvas.strokeLine(lp.x * drawscale, lp.y * drawscale, (lp.x * drawscale + radius * cos(lp.heading)), (lp.y * drawscale + radius * sin(lp.heading)))
    }
}