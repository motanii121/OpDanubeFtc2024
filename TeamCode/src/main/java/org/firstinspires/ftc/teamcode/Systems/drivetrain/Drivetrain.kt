package org.firstinspires.ftc.teamcode.Systems.drivetrain

//import org.firstinspires.ftc.teamcode.Variables.system_funcs.batteryVoltageSensor
import android.annotation.SuppressLint
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_funcs.imew
import org.firstinspires.ftc.teamcode.Variables.system_funcs.localizer
import org.firstinspires.ftc.teamcode.Variables.system_funcs.lom
import org.firstinspires.ftc.teamcode.Variables.system_vars.equalizercoef
import java.lang.Math.PI
import java.lang.Math.max
import kotlin.math.cos
import kotlin.math.sin


class Drivetrain {

    private val lbmotor = hardwareMap.dcMotor.get("LB")
    private val lfmotor = hardwareMap.dcMotor.get("LF")
    private val rbmotor = hardwareMap.dcMotor.get("RB")
    private val rfmotor = hardwareMap.dcMotor.get("RF")

    fun init() {
        rbmotor.direction = DcMotorSimple.Direction.REVERSE
        rfmotor.direction = DcMotorSimple.Direction.REVERSE

        lbmotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        lbmotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        lfmotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        lfmotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rbmotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        rbmotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rfmotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        rfmotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    fun dummydriverobotcentric() {
        val y = -lom.gamepad1.left_stick_y // Remember, Y stick value is reversed

        val x = -lom.gamepad1.left_stick_x // Counteract imperfect strafing

        val rx = lom.gamepad1.right_stick_x

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]

        val frontLeftPower = (y + x + rx) / 1.0
        val backLeftPower = (y - x + rx) / 1.0
        val frontRightPower = (-y + x - rx) / 1.0
        val backRightPower = (-y - x - rx) / 1.0

        lfmotor.power = frontLeftPower
        lbmotor.power = backLeftPower
        rfmotor.power = frontRightPower
        rbmotor.power = backRightPower


        //autoupdate_tp(tp, "merge trenu", "daaaaaaaa")
    }

    @SuppressLint("DefaultLocale")
    fun autodrive(speed: Double, turn: Double, heading: Double, slow: Double) {
        autoupdate_tp("Autodrive", String.format("%.2f %.2f %.2f %.2f", speed, turn, heading, slow))
        val slowdown = 1.0 - slow * 0.75
        val ms = speed * sin(heading + imew.yaw)
        val mc = speed * cos(heading + imew.yaw)

        val lfPower = ms + turn
        val rfPower = mc - turn
        val lbPower = mc + turn
        val rbPower = ms - turn

        lfmotor.power = lfPower * equalizercoef * slowdown
        rfmotor.power = rfPower * slowdown
        lbmotor.power = lbPower * equalizercoef * slowdown
        rbmotor.power = rbPower * equalizercoef * slowdown
    }

    fun gm0drive(slow: Double) {

        val slowdown = 1.0 - slow * 0.75
        rbmotor.direction = DcMotorSimple.Direction.REVERSE
        rfmotor.direction = DcMotorSimple.Direction.REVERSE

        var heading = imew.yaw

        val y = lom.gamepad1.left_stick_y.toDouble() // Remember, Y stick value is reversed

        val x = -lom.gamepad1.left_stick_x * 1.1 // Counteract imperfect strafing

        val rx = -lom.gamepad1.right_stick_x.toDouble()

        var fieldcentricspeed = y * Math.cos(heading) - x * Math.sin(heading)
        var fieldcentricstrafe = y * Math.sin(heading) + x * Math.cos(heading)

        val denominator = max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0)
        val frontLeftPower = (fieldcentricspeed + fieldcentricstrafe + rx) / denominator
        val backLeftPower = (fieldcentricspeed - fieldcentricstrafe + rx) / denominator
        val frontRightPower = (fieldcentricspeed - fieldcentricstrafe - rx) / denominator
        val backRightPower = (fieldcentricspeed + fieldcentricstrafe - rx) / denominator

        lfmotor.power = frontLeftPower * slowdown
        lbmotor.power = backLeftPower * slowdown
        rbmotor.power = backRightPower * slowdown
        rfmotor.power = frontRightPower * slowdown

    }

    fun perurobotcentricdrive(forward: Double, strafe: Double, rotation: Double){
        val denominator = max(1.0, forward + strafe + rotation).toDouble()
        val heading = localizer.robotpose.heading
        //var fwdpwr = forward*cos(heading) -strafe*sin(heading)
       // var strafepwr = forward*sin(heading) +strafe*cos(heading)
       // var rotationpwr = rotation

        lfmotor.power = (forward + strafe - rotation) / denominator
        lbmotor.power = (forward - strafe - rotation) / denominator
        rbmotor.power = (forward + strafe + rotation) / denominator
        rfmotor.power = (forward - strafe + rotation) / denominator
    }
}