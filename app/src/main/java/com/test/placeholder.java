package com.test;


import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.drivetrains.Mecanum;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.ftc.localization.localizers.PinpointLocalizer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManagerImpl;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This folder is where you would normally put all java/kt classes
 * you would want to test.
 */
public class placeholder {
    //placeholder for git

    // Accessing FTC and Pedro Pathing SDKs
    // If this throws an error, try rebuilding the project and syncing gradle files
    // If that doesn't work, try invalidating caches and restarting

    // In order: RobotCore, Hardware, Pedro-FTC, Pedro-Core
    OpMode opMode = new OpModeManagerImpl.DefaultOpMode();
    Servo servo = opMode.hardwareMap.get(Servo.class, "servoName");
    PinpointLocalizer localizer = new PinpointLocalizer(opMode.hardwareMap, new PinpointConstants());
    Follower follower = new Follower(new FollowerConstants(), localizer, new Mecanum(opMode.hardwareMap, new MecanumConstants()));
}
