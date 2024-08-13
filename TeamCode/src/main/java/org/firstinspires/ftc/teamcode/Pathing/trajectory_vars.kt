package org.firstinspires.ftc.teamcode.Pathing

import com.acmerobotics.dashboard.config.Config
import org.firstinspires.ftc.teamcode.Autonomous.Pose
import org.firstinspires.ftc.teamcode.Autonomous.PreloadTrajectorySet
import org.firstinspires.ftc.teamcode.Autonomous.Vector3Trajectories
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.autocase
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.isRed
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pipeline
import java.util.Vector
import kotlin.math.PI

@Config
object red_long_vals{

    @JvmField
    val Preload0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    @JvmField
    val PreloadtoBack0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    private val preloadSet0 = PreloadTrajectorySet(Preload0, PreloadtoBack0)
    private val preloadSet1 = PreloadTrajectorySet(Preload1, PreloadtoBack1)
    private  val preloadSet2 = PreloadTrajectorySet(Preload2, PreloadtoBack2)

    private val preloadSequences = Vector3Trajectories(preloadSet0, preloadSet1, preloadSet2)
    @JvmField
    val StartPreload = preloadSequences[autocase][0]
    @JvmField
    val PreloadBackDrop = preloadSequences[autocase][1]

    @JvmField
    val Park = Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0, 0.0, 0.0))
}

@Config
object red_short_vals{
    @JvmField
    val Preload0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    @JvmField
    val PreloadtoBack0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    private val preloadSet0 = PreloadTrajectorySet(Preload0, PreloadtoBack0)
    private val preloadSet1 = PreloadTrajectorySet(Preload1, PreloadtoBack1)
    private  val preloadSet2 = PreloadTrajectorySet(Preload2, PreloadtoBack2)

    private val preloadSequences = Vector3Trajectories(preloadSet0, preloadSet1, preloadSet2)
    @JvmField
    val StartPreload = preloadSequences[autocase][0]
    @JvmField
    val PreloadBackDrop = preloadSequences[autocase][1]

    @JvmField
    val Park = Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0, 0.0, 0.0))
}

@Config
object blue_long_vals {
    @JvmField
    val Preload0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    @JvmField
    val PreloadtoBack0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    private val preloadSet0 = PreloadTrajectorySet(Preload0, PreloadtoBack0)
    private val preloadSet1 = PreloadTrajectorySet(Preload1, PreloadtoBack1)
    private  val preloadSet2 = PreloadTrajectorySet(Preload2, PreloadtoBack2)

    private val preloadSequences = Vector3Trajectories(preloadSet0, preloadSet1, preloadSet2)
    @JvmField
    val StartPreload = preloadSequences[autocase][0]
    @JvmField
    val PreloadBackDrop = preloadSequences[autocase][1]

    @JvmField
    val Park = Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0, 0.0, 0.0))
}

@Config
object blue_short_vals {
    @JvmField
    val Preload0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    @JvmField
    val PreloadtoBack0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    private val preloadSet0 = PreloadTrajectorySet(Preload0, PreloadtoBack0)
    private val preloadSet1 = PreloadTrajectorySet(Preload1, PreloadtoBack1)
    private  val preloadSet2 = PreloadTrajectorySet(Preload2, PreloadtoBack2)

    private val preloadSequences = Vector3Trajectories(preloadSet0, preloadSet1, preloadSet2)
    @JvmField
    val StartPreload = preloadSequences[autocase][0]
    @JvmField
    val PreloadBackDrop = preloadSequences[autocase][1]

    @JvmField
    val Park = Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0, 0.0, 0.0))
}

@Config
object test_linie {
    @JvmField
    var Liniuta = Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0, 0.0, 0.0))
}

@Config
object auto_trajectories{

    //REDS
    // 1 = center
    // 0 = back
    // 2 = truss
    @JvmField
    val Preload0Red = Trajectory(Pose(0.0,0.0,0.0), Pose(65.0, -30.0, PI /2))
    @JvmField
    val Preload1Red = Trajectory(Pose(0.0,0.0,0.0), Pose(65.0, -10.0, 0.0))
    @JvmField
    val Preload2Red = Trajectory(Pose(0.0,0.0,0.0), Pose(65.0, 0.0, 3 * PI / 2))

    @JvmField
    val PreloadtoBack0Red = Trajectory(Pose(65.0, -10.0, 3 * PI / 2), Pose(30.0, 114.0, 3 * PI / 2))
    @JvmField
    val PreloadtoBack1Red = Trajectory(Pose(65.0, -10.0, 0.0), Pose(50.0, 110.0, 3 * PI / 2))
    @JvmField
    val PreloadtoBack2Red = Trajectory(Pose(65.0, -10.0, PI /2), Pose(40.0, 114.0,  3 * PI /2))

    private val preloadSet0Red = PreloadTrajectorySet(Preload0Red, PreloadtoBack0Red)
    private val preloadSet1Red = PreloadTrajectorySet(Preload1Red, PreloadtoBack1Red)
    private  val preloadSet2Red = PreloadTrajectorySet(Preload2Red, PreloadtoBack2Red)

    private val redpreloads = Vector3Trajectories(preloadSet0Red, preloadSet1Red, preloadSet2Red)

    //BLUES
    @JvmField
    val Preload0Blue = Trajectory(Pose(), Pose())
    @JvmField
    val Preload1Blue = Trajectory(Pose(), Pose())
    @JvmField
    val Preload2Blue = Trajectory(Pose(), Pose())

    @JvmField
    val PreloadtoBack0Blue = Trajectory(Pose(), Pose())
    @JvmField
    val PreloadtoBack1Blue = Trajectory(Pose(), Pose())
    @JvmField
    val PreloadtoBack2Blue = Trajectory(Pose(), Pose())

    private val preloadSet0Blue = PreloadTrajectorySet(Preload0Blue, PreloadtoBack0Blue)
    private val preloadSet1Blue = PreloadTrajectorySet(Preload1Blue, PreloadtoBack1Blue)
    private  val preloadSet2Blue = PreloadTrajectorySet(Preload2Blue, PreloadtoBack2Blue)

    private val bluepreloads = Vector3Trajectories(preloadSet0Blue, preloadSet1Blue, preloadSet2Blue)

    @JvmField
    var stacktodrop = Trajectory(Pose(), Pose())

    @JvmField
    var redpreload = redpreloads[pipeline.case][0]

    @JvmField
    var redbackdrop = redpreloads[pipeline.case][1]

    @JvmField
    var bluepreload = bluepreloads[pipeline.case][0]

    @JvmField
    var bluebackdrop = bluepreloads[pipeline.case][1]

    @JvmField
    var droptostack = Trajectory(if(isRed) redpreload.endpos else bluepreload.endpos, Pose())


    @JvmField
    var park = Trajectory(Pose(), Pose())

    @JvmField
    var failsafe = Trajectory(Pose(), Pose())

    @JvmField
    var redpark = Trajectory(Pose(50.0, 110.0, 3 * PI / 2), Pose(-50.0, 110.0, 3 * PI / 2))
}