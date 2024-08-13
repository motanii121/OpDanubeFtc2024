package org.firstinspires.ftc.teamcode.CommandBase

import org.firstinspires.ftc.teamcode.Autonomous.Pose
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.isRed
import org.firstinspires.ftc.teamcode.Pathing.Trajectory
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.bluebackdrop
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.bluepreload
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.droptostack
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.failsafe
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.park
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.redbackdrop
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.redpreload
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.stacktodrop
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarfinalpos
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarinit
import org.firstinspires.ftc.teamcode.Systems.camera.pipeline0
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.intakeMotorPower
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.lidClosePos
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.lidOpenPos
import org.firstinspires.ftc.teamcode.Variables.system_funcs.arm
import org.firstinspires.ftc.teamcode.Variables.system_funcs.claws
import org.firstinspires.ftc.teamcode.Variables.system_funcs.intake
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pipeline
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.sensor
import org.firstinspires.ftc.teamcode.Variables.system_funcs.slides
import kotlin.math.PI

object commands{

    //MAIN / TELEOP COMMANDS

    fun transfer(): Command{
        return SequentialCommand(
            InstantCommand { arm.goInit() },
            InstantCommand { arm.fourbar.position = 0.35 },
            InstantCommand { claws.rotator.position = 0.05},
            InstantCommand { intake.intakeServo.position = 0.8},
            WaitUntilCommand { intake.intakeServo.position == 0.8 },
            InstantCommand { intake.intakeMotor.power = intakeMotorPower},
            SleepCommand(0.2),
            InstantCommand { intake.stop() },
            InstantCommand { intake.lidServo.position = lidOpenPos },
            //WaitUntilCommand { intake.lidServo.position == lidOpenPos },
            SleepCommand(0.7),
            InstantCommand { arm.goDown() },
            InstantCommand { arm.fourbar.position = 0.20 },
            SleepCommand(0.5),
            InstantCommand { claws.grab() },
            SleepCommand(0.5),
            InstantCommand { arm.goInit() },
            //InstantCommand { arm.fourbar.position = 0.2},
            //InstantCommand{ arm.fourbar.position = fourbarfinalpos },
            SleepCommand(0.2),
            //InstantCommand { arm.fourbar.position =  0.8},
            SleepCommand(0.5),
            InstantCommand { intake.lidServo.position = lidClosePos }
        )
    }

    fun goup(): Command{
        return SequentialCommand(
            InstantCommand { arm.goUp() },
            SleepCommand(0.1),
            InstantCommand { arm.fourbar.position =  fourbarfinalpos}
        )
    }

    fun goinit(): Command{
        return SequentialCommand(
            InstantCommand { claws.rotator.position = 0.05},
            InstantCommand { claws.drop() },
            SleepCommand(0.1),
            InstantCommand { arm.goInit() },
            SleepCommand(0.2),
            InstantCommand { arm.fourbar.position =  0.4 }
        )
    }


    //AUTO COMMANDS
    fun raiseslides(i: Int): Command{
        return SequentialCommand(
            InstantCommand { slides.up() },
            SleepCommand(0.2 * (3 - i/2)),
            InstantCommand { slides.stop() },
        )
    }

    fun lowerslides(i: Int): Command{
        return SequentialCommand(
            InstantCommand { slides.down() },
            SleepCommand(0.2 * (3-i/2)),
            InstantCommand { slides.stop() },
        )
    }

    fun follow(t: Trajectory): Command{
        return SequentialCommand(
            InstantCommand{pp.followtraj(t)},
            WaitUntilCommand{!pp.busy}
        )
    }

    //PRELOADS
    fun porpl(): Command{
        return SequentialCommand(
            SleepCommand(1.0),
            InstantCommand { arm.goUp() },
            // InstantCommand { arm.fourbar.position = fourbarfinalpos},
            SleepCommand(0.4),
            //InstantCommand { arm.fourbar.position = fourbarPreload},
            InstantCommand { arm.fourbar.position = 0.7 },
            SleepCommand(0.3),
            InstantCommand { arm.goPreloadDown()},
            SleepCommand(0.5),
            InstantCommand { claws.droppurple() },
            //InstantCommand { arm.fourbar.position = 0.65},
            InstantCommand { arm.goUp()},
            SleepCommand(0.5),
        )
    }

    fun yello(): Command{
        return SequentialCommand(
            //InstantCommand { arm.goUp()},
            InstantCommand { claws.dropyellow() }
        )
    }

    fun preload(): Command{
        return SequentialCommand(
            /*InstantCommand{(if(isRed) redpreload else bluepreload)},
            porpl(),
            follow(if(isRed) redbackdrop else bluebackdrop),
            yello()*/
            InstantCommand{pp.followtraj(Trajectory(Pose(0.0,0.0,0.0), Pose(0.0,0.0,0.0)))},
            WaitUntilCommand { !pp.busy},
            SleepCommand(0.5),
            InstantCommand { arm.goPreloadDown()},
            InstantCommand {arm.fourbar.position = 0.5},
            InstantCommand { arm.goPreloadDown()},
            //InstantCommand { arm.goUp() },
            InstantCommand { arm.goPreloadDown()},
            InstantCommand{pp.followtraj(redpreload)},
            InstantCommand { arm.goPreloadDown()},
            WaitUntilCommand{!pp.busy},
            SleepCommand(0.2),
            InstantCommand { arm.goPreloadDown()},
            InstantCommand { arm.fourbar.position = 0.9 },
            SleepCommand(0.5),
            InstantCommand { claws.droppurple() },
            SleepCommand(0.3),
            InstantCommand { arm.goUp()},
            SleepCommand(0.4),
            InstantCommand {pp.followtraj(redbackdrop)},
            WaitUntilCommand{!pp.busy},
            //SleepCommand(3.0),
            raiseslides(3),
            InstantCommand { arm.goYellowDown()},
            yello(),
            SleepCommand(1.0),
            lowerslides(3),
            goinit(),
            InstantCommand {pp.followtraj(Trajectory(Pose(50.0, 110.0, 3 * PI / 2), Pose(-50.0, 110.0, 3 * PI / 2)))},
            WaitUntilCommand{!pp.busy}
            /*InstantCommand {pp.followtraj(Trajectory(Pose(30.0, 110.0, 3 * PI / 2), Pose(10.0, 0.0, PI / 2) ))},
            WaitUntilCommand{!pp.busy},
            InstantCommand {pp.followtraj(Trajectory(Pose(10.0, 0.0, PI / 2), Pose(10.0, -200.0, PI / 2) ))},
            WaitUntilCommand{!pp.busy},
            InstantCommand {pp.followtraj(Trajectory(Pose(10.0, -200.0, PI / 2), Pose(30.0, -250.0, PI / 2)))},
            WaitUntilCommand{!pp.busy},

             */
        )
    }

    //TRANSFER
    fun autotransfer(cycle: Int): Command{
        return SequentialCommand(
            raiseslides(cycle),
            SleepCommand(0.2),
            InstantCommand { arm.goUp() },
            SleepCommand(0.2),
            InstantCommand { claws.drop() },
            SleepCommand(0.2),
            lowerslides(cycle),
            InstantCommand { arm.goInit() }
        )
    }

    //INTAKE
    fun eatpixels(cycle: Int): Command{
        return SequentialCommand(
            InstantCommand { intake.intakeServo.position = 0.9 - (3 -cycle) * 0.1},
            InstantCommand { pp.followtraj(failsafe) },
            RunUntilCommand ( InstantCommand{ intake.take() }, InstantCommand{ sensor.got2pixels() } )
        )
    }

    //CYCLES
    fun cycle(cycle: Int): Command{
        return SequentialCommand(
            InstantCommand { claws.drop() },
            follow(droptostack),
            eatpixels(cycle),
            transfer(),
            follow(stacktodrop),
            autotransfer(cycle),
        )
    }

    fun auto(): Command{
        return SequentialCommand(
            preload(),
            cycle(3),
            cycle(2),
            cycle(1),
            InstantCommand { pp.followtraj(park) }
        )
    }
}