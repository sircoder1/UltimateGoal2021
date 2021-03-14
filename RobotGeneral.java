/*
    Robot Class for Destriers 2020-2021
    Created by Josh Faber with Eshaan Mahajan, Joel Samuel, Benjamin Gantman, Erica Wang, and Jamie Heskett
 */


package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class RobotGeneral {
    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;

    private Telemetry telemetry;

    private HardwareMap hardwareMap;
    private LinearOpMode linearOpMode;



    public RobotGeneral(DcMotor frontRightMotor, DcMotor frontLeftMotor, DcMotor backRightMotor, DcMotor backLeftMotor, Telemetry telemetry, LinearOpMode linearOpMode){
        this.frontRightMotor = frontRightMotor;
        this.frontLeftMotor = frontLeftMotor;
        this.backRightMotor = backRightMotor;
        this.backLeftMotor = backLeftMotor;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;
    }


    //TeleOp movement

    public void teleMove (double x, double y)
    {
        if(linearOpMode.opModeIsActive()) {
            double firstBasis = x + y;
            double secondBasis = y - x;
            firstBasis = firstBasis / Math.sqrt(2);
            secondBasis = secondBasis / Math.sqrt(2);
            teleSetMove(firstBasis, secondBasis);
        }
    }

    public void teleSetMove (double firstBasis, double secondBasis)
    {
        if(linearOpMode.opModeIsActive()) {
            frontLeftMotor.setPower(firstBasis);
            backRightMotor.setPower(firstBasis);
            frontRightMotor.setPower(secondBasis);
            backLeftMotor.setPower(secondBasis);
        }
    }



    public void teleTurn (int CW, double power){
        if(linearOpMode.opModeIsActive()){
            double leftPower = CW * power;
            double rightPower = -CW * power;
            teleSetTurn(leftPower, rightPower);
        }
    }

    public void teleSetTurn(double left, double right) {
        if(linearOpMode.opModeIsActive()){
            frontLeftMotor.setPower(left);
            backLeftMotor.setPower(left);
            frontRightMotor.setPower(right);
            backRightMotor.setPower(right);
        }
    }

    private double circumference = 2*Math.PI*48;
    private int ticks = 1680;


    public void autonMove(double x, double y)
    {
        if(linearOpMode.opModeIsActive()){
            double firstBasis = x + y;
            double secondBasis = y - x;
            firstBasis = firstBasis * 0.25 / Math.sqrt(2);
            secondBasis = secondBasis * 0.25 / Math.sqrt(2);
            int firstTicks = (int) Math.round(firstBasis * ticks / (circumference));
            int secondTicks = (int) Math.round(secondBasis * ticks / (circumference));
            frontLeftMotor.setTargetPosition(firstTicks);
            backRightMotor.setTargetPosition(firstTicks);
            frontRightMotor.setTargetPosition(secondTicks);
            backLeftMotor.setTargetPosition(secondTicks);
            frontLeftMotor.setPower(firstBasis);
            backRightMotor.setPower(firstBasis);
            frontRightMotor.setPower(secondBasis);
            backLeftMotor.setPower(secondBasis);
            setMotorModeToPosition();
            while((backLeftMotor.isBusy() || backRightMotor.isBusy() || frontRightMotor.isBusy() || frontLeftMotor.isBusy()) && linearOpMode.opModeIsActive())
            {
                //wait
            }
            stopAndResetEncoders();

        }
    }
























    public void setMotorModeWithout() {
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setMotorModeToPosition() {
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void stopAndResetEncoders() {
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void setMotorModeusing() {
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void init(HardwareMap hardwareMap) {
        //Initialize Motors
        frontLeftMotor = hardwareMap.get(DcMotor.class, "Front Left Motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "Front Right Motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "Back Left Motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "Back Right Motor");


        //Set Directions
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors


        //Set Motor Power to Zero
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);


        //Run with Encoders. If we don't use Encoders, change "RUN_USING_ENCODERS" to "RUN_WITHOUT_ENCODERS"
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //Brakes the Motors when the power is at Zero
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
