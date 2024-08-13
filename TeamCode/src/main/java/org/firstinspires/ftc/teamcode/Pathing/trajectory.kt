package org.firstinspires.ftc.teamcode.Pathing

import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.angDiff
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.angNorm
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.liniuta
import org.firstinspires.ftc.teamcode.Autonomous.Pose

class Trajectory(@JvmField val startpos: Pose, @JvmField val endpos: Pose) {

    //gets a point in a line defined between startpos and endpos
    operator fun get(a: Double): Pose {
        val x = liniuta(startpos.x, endpos.x, a)
        val y = liniuta(startpos.y, endpos.y, a)
        val h = liniuta(startpos.heading, startpos.heading + angDiff(startpos.heading, endpos.heading), a)

        return Pose(x, y, angNorm(h))
    }
}


