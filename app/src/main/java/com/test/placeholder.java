package com.test;


import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.drivetrains.Mecanum;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.ftc.localization.localizers.PinpointLocalizer;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.groups.Groups;
import com.pedropathing.telemetry.Selectable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManagerImpl;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;

/**
 * This folder is where you would normally put all java/kt classes
 * you would want to test.
 */
public class placeholder {
    //placeholder for git

    // Accessing FTC and Pedro Pathing SDKs
    // If this throws an error, try rebuilding the project and syncing gradle files
    // If that doesn't work, try invalidating caches and restarting

    OpMode opMode = new OpModeManagerImpl.DefaultOpMode();
    Servo servo = opMode.hardwareMap.get(Servo.class, "servoName");
    PinpointLocalizer localizer = new PinpointLocalizer(opMode.hardwareMap, new PinpointConstants());
    Follower follower = new Follower(new FollowerConstants(), localizer, new Mecanum(opMode.hardwareMap, new MecanumConstants()));
    Command command = Groups.parallel(Command.NOOP, Command.NOOP);
    Selectable<OpMode> selectableOpMode;
    OpenCvCamera cvCamera;
}
