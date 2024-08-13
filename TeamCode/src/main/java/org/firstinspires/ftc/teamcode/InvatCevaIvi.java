package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "motor1")
public class InvatCevaIvi extends OpMode {

    DcMotor motor;
    @Override
    public void init(){
        motor = hardwareMap.get(DcMotor.class, "motor1");


    }
    @Override
    public void loop(){
        motor.setPower(1);
    }
}
