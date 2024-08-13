package org.firstinspires.ftc.teamcode.Pathing

import com.acmerobotics.dashboard.config.Config
import org.firstinspires.ftc.teamcode.Algorithms.chain_actioner
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.posdiff
import org.firstinspires.ftc.teamcode.CommandBase.Command
import org.firstinspires.ftc.teamcode.CommandBase.InstantCommand
import org.firstinspires.ftc.teamcode.CommandBase.RunUntilCommand
import org.firstinspires.ftc.teamcode.CommandBase.SequentialCommand
import org.firstinspires.ftc.teamcode.CommandBase.WaitUntilCommand
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmPreload
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmUp
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.lclawOpenPos
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.rclawOpenPos
import org.firstinspires.ftc.teamcode.Variables.system_funcs.arm
import org.firstinspires.ftc.teamcode.Variables.system_funcs.claws
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp

@Config
object path_planner {
    fun test_linie(vals: test_linie): Command {
        return SequentialCommand(
            InstantCommand { pp.followtraj(vals.Liniuta)},
            WaitUntilCommand{ !pp.busy },
            InstantCommand { arm.goPreloadDown() }
        )
    }
}