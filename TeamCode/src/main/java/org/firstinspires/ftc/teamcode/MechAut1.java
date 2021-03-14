package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="MechAut1", group="Auton")

public class MechAut1 extends LinearOpMode {

    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;

    private ElapsedTime runtime = new ElapsedTime();

    RobotGeneral Robot = new RobotGeneral(frontRightMotor, frontLeftMotor, backRightMotor, backLeftMotor, telemetry, this);

    @Override

    public void runOpMode() {
        Robot.init(hardwareMap);

        telemetry.addData("Status", "Auton has been Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();

        while (opModeIsActive() && runtime.seconds()<2) {

            Robot.move(0,0.5);
        }

        Robot.move(0,0);

    }
}
