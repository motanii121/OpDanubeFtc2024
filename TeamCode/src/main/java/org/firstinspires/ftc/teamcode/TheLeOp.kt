package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorSimple

@TeleOp
class TeleopDanube : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val leftfrontMotor = hardwareMap.dcMotor["LF"]
        val rightfrontMotor = hardwareMap.dcMotor["RF"]
        val leftbackMotor = hardwareMap.dcMotor["LB"]
        val rightbackMotor = hardwareMap.dcMotor["RB"]

        leftfrontMotor.direction = DcMotorSimple.Direction.REVERSE
        leftbackMotor.direction = DcMotorSimple.Direction.REVERSE

        waitForStart()

        while (!isStopRequested) {
            val forward = -gamepad1.left_stick_y.toDouble()
            val strafe = gamepad1.left_stick_x.toDouble()
            val rotation = gamepad1.right_stick_x.toDouble()

            val LFpower = (forward + strafe + rotation)
            val LBpower = (forward - strafe + rotation)
            val RFpower = (forward - strafe - rotation)
            val RBpower = (forward + strafe - rotation)

            leftfrontMotor.power = LFpower
            rightbackMotor.power = RBpower
            leftbackMotor.power = LBpower
            rightfrontMotor.power = RFpower
        }
    }
}
