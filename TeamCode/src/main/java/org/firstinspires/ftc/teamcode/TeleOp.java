package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "RobotB3")
public class TeleOp extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor LeftFront = null;
    private DcMotor LeftBack = null;
    private DcMotor RightFront = null;
    private DcMotor RightBack = null;
    Servo ServoExemplu = null;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        LeftFront = hardwareMap.get(DcMotor.class, "LeftFront");
        LeftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        RightFront = hardwareMap.get(DcMotor.class, "RightFront");
        RightBack = hardwareMap.get(DcMotor.class, "RightBack");

        ServoExemplu = hardwareMap.get(Servo.class, "ServoExemplu");

        LeftFront.setDirection(DcMotor.Direction.REVERSE);
        RightFront.setDirection(DcMotor.Direction.FORWARD);
        LeftBack.setDirection(DcMotor.Direction.REVERSE);
        RightBack.setDirection(DcMotor.Direction.FORWARD);

        LeftFront.setPower(0);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        RightBack.setPower(0);

        ServoExemplu.setPosition(0.5);

        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {}

    @Override
    public void start() { runtime.reset(); }

    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = -gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 2);
        if(gamepad1.left_bumper == true){ denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 4);}
        if(gamepad1.right_bumper == true) { denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 0.5);}
        double LeftFrontPower = (y - x + rx)/denominator;
        double LeftBackPower = (y + x + rx)/denominator;
        double RightFrontPower = (y + x - rx)/denominator;
        double RightBackPower = (y - x - rx)/denominator;

        LeftFront.setPower(LeftFrontPower);   // +
        LeftBack.setPower(LeftBackPower);     // -
        RightFront.setPower(RightFrontPower); // -
        RightBack.setPower(RightBackPower);   // +

        if(gamepad1.a) ServoExemplu.setPosition(1);
        else if(gamepad1.y) ServoExemplu.setPosition(0);
        else ServoExemplu.setPosition(0.50);
        telemetry.addData("ServoExemplu", ServoExemplu.getPosition());

    }
    @Override
    public void stop() { }
}
