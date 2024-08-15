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
    val Preload0Red = Trajectory(Pose(0.0,0.0,0.0), Pose(65.0, 10.0, 3* PI /2))
    @JvmField
    val Preload1Red = Trajectory(Pose(0.0,0.0,0.0), Pose(65.0, 5.0, 0.0))
    @JvmField
    val Preload2Red = Trajectory(Pose(0.0,0.0,0.0), Pose(60.0, -30.0,  PI / 2))

    @JvmField
    val PreloadtoBack0Red = Trajectory(Pose(20.0, 10.0, 3*PI/2), Pose(25.0, 110.0, 3 * PI / 2))
    @JvmField
    val PreloadtoBack1Red = Trajectory(Pose(65.0, 5.0, 0.0), Pose(40.0, 110.0, 3*PI/2))

    val anotherintermediarytraj2Red = Trajectory(Pose(60.0, -30.0,  PI / 2), Pose(60.0, -40.0,  PI / 2))
    val intermediarytraj2Red = Trajectory(Pose(60.0, -44.0, PI/2), Pose(50.0, 50.0, 3*PI/2))
    @JvmField
    val PreloadtoBack2Red = Trajectory(Pose(50.0, 50.0, 3*PI /2), Pose(50.0, 105.0,  3 * PI /2))

    val intermediarytraj0Red = Trajectory(Pose(65.0, 10.0, 3*PI/2), Pose(20.0, 10.0, 3*PI/2))

    val Park1Red = Trajectory(Pose(40.0, 110.0, 3 * PI / 2), Pose(-20.0, 110.0, 3 * PI / 2))
    val Park0Red = Trajectory(Pose(25.0, 110.0, 3 * PI / 2), Pose(-20.0, 110.0, 3 * PI / 2))
    val Park2Red = Trajectory(Pose(50.0, 110.0, 3 * PI / 2), Pose(-20.0, 110.0, 3 * PI / 2))

    private val preloadSet0Red = PreloadTrajectorySet(Preload0Red, PreloadtoBack0Red)
    private val preloadSet1Red = PreloadTrajectorySet(Preload1Red, PreloadtoBack1Red)
    private  val preloadSet2Red = PreloadTrajectorySet(Preload2Red, PreloadtoBack2Red)

    val redpreloads = Vector3Trajectories(preloadSet0Red, preloadSet1Red, preloadSet2Red)

    //BLUES
    // 1 = center
    // 0 = back
    // 2 = truss
    @JvmField
    val Preload0Blue = Trajectory(Pose(0.0,0.0,0.0), Pose(65.0, 10.0, PI /2))
    @JvmField
    val Preload1Blue = Trajectory(Pose(0.0,0.0,0.0), Pose(65.0, -5.0, 0.0))
    @JvmField
    val Preload2Blue = Trajectory(Pose(0.0,0.0,0.0), Pose(60.0, 20.0,   3 * PI / 2))

    @JvmField
    val anotherintermediarytraj2blue = Trajectory(Pose(60.0, 20.0,  PI / 2), Pose(60.0, 30.0,  3*PI / 2))
    val intermediarytraj0Blue = Trajectory(Pose(60.0, -44.0, PI/2), Pose(50.0, 50.0, 3*PI/2))
    val PreloadtoBack0Blue = Trajectory(Pose(50.0, 50.0, 3*PI /2), Pose(50.0, 105.0,  3 * PI /2))
    @JvmField
    val PreloadtoBack1Blue = Trajectory(Pose(65.0, 5.0, 0.0), Pose(40.0, 110.0, 3*PI/2))
    @JvmField
    val PreloadtoBack2Blue = Trajectory(Pose(20.0, 10.0, 3*PI/2), Pose(25.0, 110.0, 3 * PI / 2))

    private val preloadSet0Blue = PreloadTrajectorySet(Preload0Blue, PreloadtoBack0Blue)
    private val preloadSet1Blue = PreloadTrajectorySet(Preload1Blue, PreloadtoBack1Blue)
    private  val preloadSet2Blue = PreloadTrajectorySet(Preload2Blue, PreloadtoBack2Blue)

    private val bluepreloads = Vector3Trajectories(preloadSet0Blue, preloadSet1Blue, preloadSet2Blue)
    val Park1Blue = Trajectory(Pose(40.0, 110.0, 3 * PI / 2), Pose(-20.0, 110.0, 3 * PI / 2))
    val Park0Blue = Trajectory(Pose(25.0, 110.0, 3 * PI / 2), Pose(-20.0, 110.0, 3 * PI / 2))
    val Park2Blue = Trajectory(Pose(50.0, 110.0, 3 * PI / 2), Pose(-20.0, 110.0, 3 * PI / 2))
    @JvmField
    var stacktodrop = Trajectory(Pose(), Pose())

    @JvmField
    var redpreload = redpreloads[autocase][0]

    @JvmField
    var redbackdrop = redpreloads[autocase][1]

    @JvmField
    var bluepreload = bluepreloads[autocase][0]

    @JvmField
    var bluebackdrop = bluepreloads[autocase][1]

    @JvmField
    var droptostack = Trajectory(if(isRed) redpreload.endpos else bluepreload.endpos, Pose())


    @JvmField
    var park = Trajectory(Pose(), Pose())

    @JvmField
    var failsafe = Trajectory(Pose(), Pose())

    @JvmField
    var redpark = Trajectory(Pose(50.0, 110.0, 3 * PI / 2), Pose(-50.0, 110.0, 3 * PI / 2))
}