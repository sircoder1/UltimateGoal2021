/*
    First Attempt at Driver-Controlled Code for the Destriers 2020-2021
    Created by Josh Faber with Eshaan Mahajan, Joel Samuel, Benjaman Gantman, Erica Wang, and Jamie Heskett
 */


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "MechTele1", group = "TeleOp")


public class MechTele1 extends LinearOpMode{


    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;

    private ElapsedTime runtime = new ElapsedTime();

    RobotGeneral Robot = new RobotGeneral(frontRightMotor, frontLeftMotor, backRightMotor, backLeftMotor, telemetry, this);

    @Override

    public void runOpMode() {
        Robot.init(hardwareMap);
        telemetry.addData("Status","TeleOp has been Initialized");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()){

            //The Joystick:
            double G1rightStickX = gamepad1.right_stick_x;
            double G1rightStickY = -gamepad1.right_stick_y;
            double G1leftStickX = gamepad1.left_stick_x;
            double G1leftStickY = -gamepad1.left_stick_y;



            if (Math.abs(G1rightStickX) > 0.1 || Math.abs(G1rightStickY) > 0.1){
                Robot.teleMove(G1rightStickX * 0.25, G1rightStickY * 0.25);
            } else if (Math.abs(G1leftStickX) > 0.2) {
                int CW = (int) (G1leftStickX / Math.abs(G1leftStickX));
                Robot.teleTurn(CW, Math.abs(G1leftStickX));
            } else {
                Robot.teleMove(0,0);

            }
        }
    }
}
