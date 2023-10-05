package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "liniar test")
public class TeleOpLiniar extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor LeftFront = null;
    private DcMotor LeftBack = null;
    private DcMotor RightFront = null;
    private DcMotor RightBack = null;
    private DcMotor Brat = null;
    double TICKS_PER_REVOLUTION = 1120;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        LeftFront = hardwareMap.get(DcMotor.class, "LeftFront");
        LeftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        RightFront = hardwareMap.get(DcMotor.class, "RightFront");
        RightBack = hardwareMap.get(DcMotor.class, "RightBack");
        Brat = hardwareMap.get(DcMotor.class, "Brat");

        LeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        LeftFront.setDirection(DcMotor.Direction.REVERSE);
        RightFront.setDirection(DcMotor.Direction.FORWARD);
        LeftBack.setDirection(DcMotor.Direction.REVERSE);
        RightBack.setDirection(DcMotor.Direction.FORWARD);
        RightBack.setDirection(DcMotor.Direction.REVERSE);

        LeftFront.setPower(0);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        RightBack.setPower(0);
        Brat.setPower(0);

        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Brat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {

            double y = -gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 2);
            if(gamepad1.left_bumper == true){ denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 4);}
            double LeftFrontPower = (y - x + rx)/denominator;
            double LeftBackPower = (y + x + rx)/denominator;
            double RightFrontPower = (y + x - rx)/denominator;
            double RightBackPower = (y - x - rx)/denominator;

            LeftFront.setPower(LeftFrontPower);   // +
            LeftBack.setPower(LeftBackPower);     // -
            RightFront.setPower(RightFrontPower); // -
            RightBack.setPower(RightBackPower);   // +

            double sfert_dr = TICKS_PER_REVOLUTION/4;
            Brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            double PozNoua = Brat.getTargetPosition() + sfert_dr;
            Brat.setTargetPosition((int) PozNoua);
            while(gamepad1.a){
                Brat.setPower(1);
            }

            while(Brat.isBusy()){ }

            Brat.setPower(0);
        }}
}
