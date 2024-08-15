package org.firstinspires.ftc.teamcode.CommandBase

import com.qualcomm.hardware.ams.AMSColorSensor.Wait
import org.firstinspires.ftc.teamcode.Autonomous.Pose
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.isRed
import org.firstinspires.ftc.teamcode.CommandBase.auto_sequences_red.preload0
import org.firstinspires.ftc.teamcode.CommandBase.auto_sequences_red.preload1
import org.firstinspires.ftc.teamcode.CommandBase.auto_sequences_red.preload2
import org.firstinspires.ftc.teamcode.CommandBase.commands.follow
import org.firstinspires.ftc.teamcode.CommandBase.commands.goinit
import org.firstinspires.ftc.teamcode.CommandBase.commands.lowerslides
import org.firstinspires.ftc.teamcode.CommandBase.commands.raiseslides
import org.firstinspires.ftc.teamcode.CommandBase.commands.yello
import org.firstinspires.ftc.teamcode.CommandBase.commands.yellowsequence
import org.firstinspires.ftc.teamcode.Pathing.Trajectory
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Park0Blue
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Park0Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Park1Blue
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Park1Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Park2Blue
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Park2Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Preload0Blue
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Preload0Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Preload1Blue
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Preload1Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Preload2Blue
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.Preload2Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.PreloadtoBack0Blue
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.PreloadtoBack0Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.PreloadtoBack1Blue
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.PreloadtoBack1Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.PreloadtoBack2Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.anotherintermediarytraj2Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.anotherintermediarytraj2blue
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.bluebackdrop
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.bluepreload
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.droptostack
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.failsafe
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.intermediarytraj0Blue
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.intermediarytraj0Red
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.intermediarytraj2Red
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
            InstantCommand { claws.rotator.position = 0.05},
            InstantCommand { claws.drop()},
            InstantCommand { arm.goInit() }, //sets the pos of the 2 arm servos
            InstantCommand { arm.fourbar.position = 0.4 },
            InstantCommand { claws.rotator.position = 0.05},
            InstantCommand { intake.intakeServo.position = 0.8},
            WaitUntilCommand { intake.intakeServo.position == 0.8 },
            InstantCommand { intake.intakeMotor.power = intakeMotorPower}, //little intake action
            SleepCommand(0.2),
            InstantCommand { intake.stop() },
            InstantCommand { intake.lidServo.position = lidOpenPos },
            SleepCommand(0.3), //insane amount of sleeps bcs testing reasons
            InstantCommand { arm.fourbar.position = 0.4 }, //dont care that its the same position lol
            InstantCommand { arm.goDown() }, //same as goinit
            SleepCommand(0.25),
            InstantCommand { claws.grab() },
            SleepCommand(0.25),
            InstantCommand { arm.goInit() },
            SleepCommand(0.3),
            InstantCommand { intake.lidServo.position = lidClosePos }
        )
    }

    fun failsafe(): Command{
        return SequentialCommand(
            InstantCommand {arm.goInit()},
            InstantCommand { arm.fourbar.position = 0.4}
        )
    }

    fun goup(): Command{
        return SequentialCommand(
            InstantCommand { arm.goUp() },
            SleepCommand(0.1),
            InstantCommand { arm.fourbar.position =  0.87}
        )
    }

    fun goinit(): Command{
        return SequentialCommand(
            SleepCommand(0.2),
            InstantCommand { claws.drop() },
            SleepCommand(0.1),
            InstantCommand { claws.rotator.position = 0.05},
            lowerslidesteleop(slides.lslide.currentPosition > 1),
            //InstantCommand { arm.fourbar.position = 0.4},
            InstantCommand { arm.goInit() },
            InstantCommand { arm.fourbar.position = 0.4},
            )
    }

    fun transferfromup(): Command{
        return SequentialCommand(
            goinit(),
            SleepCommand(0.1),
            transfer()
        )
    }


    //AUTO COMMANDS
    fun raiseslides(i: Int): Command{
        return SequentialCommand(
            InstantCommand { slides.up() },
            SleepCommand(0.2 ),
            InstantCommand { slides.stop() },
        )
    }

    fun lowerslidesteleop(areslidesup: Boolean): Command{

        return if(areslidesup)
            SequentialCommand(
                RunUntilCommand(InstantCommand{ slides.down() }, InstantCommand{ slides.lslide.currentPosition < 10 })
            )
        else
            InstantCommand{}
    }

    fun lowerslides(i: Int): Command{
        return SequentialCommand(
            InstantCommand { slides.down() },
            SleepCommand(0.2 ),
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

    fun yellowsequence(): Command{
        return SequentialCommand(
            raiseslides(3),
            yello(),
            SleepCommand(1.0),
            lowerslides(3),
            goinit(),
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

    fun auto(case: Int): Command{
        var preload =  if(case == 0) preload0() else if(case == 1) preload1() else preload2()
        return SequentialCommand(
            preload,
            cycle(3),
            cycle(2),
            cycle(1),
            InstantCommand { pp.followtraj(park) }
        )
    }
}

object auto_sequences_red{
    //BACKDROP RED
    fun preload0(): Command{
        return SequentialCommand(
            //start
            follow(Trajectory(Pose(), Pose())),

            //setup arm
            InstantCommand{arm.fourbar.position = 0.7},
            SleepCommand(0.5),
            InstantCommand { arm.goPreloadDown()},

            //go to preload 0 (towards backdrop)
            follow(Preload0Red),
            SleepCommand(0.2),

            //straighten wrist
            InstantCommand { arm.goPreloadDown()},
            InstantCommand { arm.fourbar.position = 1.0 },
            SleepCommand(0.5),
            InstantCommand{claws.rotator.position = 0.05},

            //drop purple
            InstantCommand { claws.droppurple() },
            SleepCommand(0.3),

            //prepare for yellow
            InstantCommand { arm.goUp()},
            InstantCommand { arm.fourbar.position = 0.95},
            SleepCommand(0.4),

            //move out of the way to not de-score purple
            follow(intermediarytraj0Red),
            SleepCommand(0.2),

            //go to backdrop
            follow(PreloadtoBack0Red),

            //drop yellow
            yellowsequence(),

            //park
            follow(Park0Red)
        )
    }

    //CENTER RED
    fun preload1(): Command{
        return SequentialCommand(
            //start
            follow(Trajectory(Pose(), Pose())),

            //setup arm
            InstantCommand{arm.fourbar.position = 0.7},
            SleepCommand(0.5),
            InstantCommand { arm.goPreloadDown()},

            //go to preload 1 (center)
            follow(Preload1Red),
            SleepCommand(0.2),

            //put arm down and straighten wrist
            InstantCommand { arm.goPreloadDown()},
            InstantCommand { arm.fourbar.position = 1.0 },
            SleepCommand(0.5),
            InstantCommand{claws.rotator.position = 0.05},

            //drop
            InstantCommand { claws.droppurple() },
            SleepCommand(0.3),

            //prepare for yellow
            InstantCommand { arm.goUp()},
            InstantCommand { arm.fourbar.position = 0.95},
            SleepCommand(0.4),

            //go to backboard
            follow(PreloadtoBack1Red),

            //put yellow
            yellowsequence(),

            //reset and park
            follow(Park1Red)
        )
    }

    //TRUSS RED
    fun preload2(): Command{
        return SequentialCommand(
            //start
            follow(Trajectory(Pose(), Pose())),

            //setup arm
            InstantCommand{arm.fourbar.position = 0.7},
            SleepCommand(0.5),
            InstantCommand { arm.goPreloadDown()},

            //go to preload 2 (towards trusses)
            follow(Preload2Red),

            //allign to the spike mark, cannot do it in 1 path because otherwise i'd hit the truss
            follow(anotherintermediarytraj2Red),
            SleepCommand(0.2),

            //straighten wrist
            InstantCommand { arm.goPreloadDown()},
            InstantCommand { arm.fourbar.position = 1.0 },
            SleepCommand(0.5),
            InstantCommand{claws.rotator.position = 0.05},

            //drop purple
            InstantCommand { claws.droppurple() },
            SleepCommand(0.3),

            //setup for yellow
            InstantCommand { arm.goUp()},
            InstantCommand { arm.fourbar.position = 0.95},
            SleepCommand(0.4),

            //due to the fact that i've got an imperfect pid paired with a pid to point, going straight up to the backdrop would
            //make me hit it, so i make the robot go to an intermediary point between the two to soften the pathing
            follow(intermediarytraj2Red),
            SleepCommand(0.2),

            //go to backdrop
            follow(Preload2Red),

            //drop yellow
            yellowsequence(),

            //reset and park
            follow(Park2Red)
        )
    }
}

object auto_sequences_blue{
    //BACKDROP BLUE
    fun preload0blue(): Command{
        return SequentialCommand(
            //start
            follow(Trajectory(Pose(), Pose())),

            //setup arm
            InstantCommand{arm.fourbar.position = 0.7},
            SleepCommand(0.5),
            InstantCommand { arm.goPreloadDown()},

            //go to preload 0 (towards backdrop)
            follow(Preload0Blue),
            SleepCommand(0.2),

            //straighten wrist
            InstantCommand { arm.goPreloadDown()},
            InstantCommand { arm.fourbar.position = 1.0 },
            SleepCommand(0.5),
            InstantCommand{claws.rotator.position = 0.05},

            //drop purple
            InstantCommand { claws.droppurple() },
            SleepCommand(0.3),

            //prepare for yellow
            InstantCommand { arm.goUp()},
            InstantCommand { arm.fourbar.position = 0.95},
            SleepCommand(0.4),

            //move out of the way to not de-score purple
            follow(intermediarytraj0Blue),
            SleepCommand(0.2),

            //go to backdrop
            follow(PreloadtoBack0Blue),

            //drop yellow
            yellowsequence(),

            //park
            follow(Park0Blue)
        )
    }

    //CENTER BLUE
    fun preload1blue(): Command{
        return SequentialCommand(
            //start
            follow(Trajectory(Pose(), Pose())),

            //setup arm
            InstantCommand{arm.fourbar.position = 0.7},
            SleepCommand(0.5),
            InstantCommand { arm.goPreloadDown()},

            //go to preload 1 (center)
            follow(Preload1Blue),
            SleepCommand(0.2),

            //put arm down and straighten wrist
            InstantCommand { arm.goPreloadDown()},
            InstantCommand { arm.fourbar.position = 1.0 },
            SleepCommand(0.5),
            InstantCommand{claws.rotator.position = 0.05},

            //drop
            InstantCommand { claws.droppurple() },
            SleepCommand(0.3),

            //prepare for yellow
            InstantCommand { arm.goUp()},
            InstantCommand { arm.fourbar.position = 0.95},
            SleepCommand(0.4),

            //go to backboard
            follow(PreloadtoBack1Blue),

            //put yellow
            yellowsequence(),

            //reset and park
            follow(Park1Blue)
        )
    }

    //TRUSS BLUE
    fun preload2blue(): Command{
        return SequentialCommand(
            //start
            follow(Trajectory(Pose(), Pose())),

            //setup arm
            InstantCommand{arm.fourbar.position = 0.7},
            SleepCommand(0.5),
            InstantCommand { arm.goPreloadDown()},

            //go to preload 2 (towards trusses)
            follow(Preload2Blue),

            //allign to the spike mark, cannot do it in 1 path because otherwise i'd hit the truss
            follow(anotherintermediarytraj2blue),
            SleepCommand(0.2),

            //straighten wrist
            InstantCommand { arm.goPreloadDown()},
            InstantCommand { arm.fourbar.position = 1.0 },
            SleepCommand(0.5),
            InstantCommand{claws.rotator.position = 0.05},

            //drop purple
            InstantCommand { claws.droppurple() },
            SleepCommand(0.3),

            //setup for yellow
            InstantCommand { arm.goUp()},
            InstantCommand { arm.fourbar.position = 0.95},
            SleepCommand(0.4),

            //due to the fact that i've got an imperfect pid paired with a pid to point, going straight up to the backdrop would
            //make me hit it, so i make the robot go to an intermediary point between the two to soften the pathing
            follow(anotherintermediarytraj2blue),
            SleepCommand(0.2),

            //go to backdrop
            follow(Preload2Blue),

            //drop yellow
            yellowsequence(),

            //reset and park
            follow(Park2Blue)
        )
    }

}