package org.firstinspires.ftc.teamcode.Algorithms

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.Pathing.Trajectory
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp
import java.util.Vector

class action(val theAction: () -> Unit, val isDone: () -> Boolean) {

}

class chain_actioner(){
    val actions =  Vector<action>()
    var actionindex: Int = 0
    var elapsedTime =  ElapsedTime()

    init{
     actions.add(action({}, {true}))
    }

    fun addAction(theAction: () -> Unit, isDone: () -> Boolean): chain_actioner{
        actions.add(action(theAction, isDone))
        return this
    }

    fun addTrajectory(t: Trajectory): chain_actioner{
        actions.add(action({pp.followtraj(t)}, {pp.busy}))
        return this
    }

    fun addSleep(s: Double): chain_actioner {
        actions.add(action( { elapsedTime.reset() }) { elapsedTime.seconds() > s }
        )
        return this
    }

    fun update(): Int {
        if (actionindex >= actions.size) {
            return 1
        }
        while (actionindex < actions.size && actions[actionindex].isDone()) {
            actionindex++

            if (actionindex >= actions.size) {
                return 1
            }
            actions[actionindex].theAction()
        }

        return 0
    }

}

