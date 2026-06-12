# TestingQuickstart

Java-first quickstart for testing FTC and Pedro Pathing robot code in an Android Studio project without needing a connected robot.

## Scope of this README

This README intentionally focuses on the Java source files under `app/src`.

Excluded from this write-up:
- XML resources
- `AndroidManifest.xml`
- themes, icons, backup rules, and other generated/template Android resource files

## What this repository is for

This project is a small testing scaffold for writing and running robot-related Java code locally. It is set up as an Android app module, but the most important pieces in the repo right now are the Java classes used for:

- referencing FTC SDK types
- referencing Pedro Pathing types
- organizing local JVM unit tests
- providing a sample Android instrumented test

## Java source layout

### Production source

#### `app/src/main/java/com/test/placeholder.java`

This file is a placeholder/example class showing that the project can resolve and use classes from both the FTC SDK and Pedro Pathing libraries.

It demonstrates references to:
- FTC SDK classes such as `OpMode`, `OpModeManagerImpl`, and `Servo`
- Pedro Pathing classes such as `Follower`, `FollowerConstants`, `PinpointLocalizer`, and `Mecanum`

The file is not a full robot implementation. Instead, it acts as a proof-of-setup file that shows how code in this repository can access the libraries needed for robot logic and pathing experiments.

### Local unit tests

#### `app/src/test/java/com/test/UnitTest.java`

This is the base class for local unit test classes used by the custom test harness. It exposes a shared static list:

- `testClasses`

That list is used by `TestAll` to determine which unit test classes should be executed together.

#### `app/src/test/java/com/test/TestAll.java`

This is the custom local test runner for the repository.

What it does:
- defines `configuredClasses()`
- registers test classes by assigning `UnitTest.testClasses`
- reflects over each configured class
- finds methods annotated with `@Test`
- constructs an instance of each class and invokes its test methods

Right now it is configured to run:
- `ExampleUnitTest.class`

This file is useful when you want one top-level test entry point that runs a hand-picked set of unit test classes.

#### `app/src/test/java/com/test/ExampleUnitTest.java`

This is the sample local JVM test.

Current behavior:
- contains `addition_isCorrect()`
- asserts that `2 + 2 == 4`

This class serves as the simplest example of how core logic tests can be added under `app/src/test/java`.

### Instrumented Android test

#### `app/src/androidTest/java/com/test/ExampleInstrumentedTest.java`

This is the sample Android instrumented test that runs on a device or emulator.

Current behavior:
- gets the target app `Context`
- verifies the package name is `com.test`

This is the place for tests that require the Android framework, device APIs, or behavior that cannot run as a plain JVM unit test.

## Dependency bundle used by the project

The app module declares:

- `implementation(libs.bundles.ftc)`

That bundle is defined in `gradle/libs.versions.toml` and groups the main robotics/pathing dependencies used by this project.

The `ftc` bundle includes:
- `org.firstinspires.ftc:RobotCore`
- `org.firstinspires.ftc:Hardware`
- `com.pedropathing:core`
- `com.pedropathing:ftc`

In other words, this repository uses:
- the **FTC SDK**
- **Pedro Pathing**

through the shared `libs.bundles.ftc` dependency bundle.

## How the Java code fits together

At a high level:

1. Java robot-related code lives in `app/src/main/java`
2. Plain logic tests live in `app/src/test/java`
3. Android/device-dependent tests live in `app/src/androidTest/java`
4. `TestAll` can be used as a manual aggregation point for selected local test classes

## Running tests

Typical local unit test target:

```bash
./gradlew testDebugUnitTest
```

To focus on the custom aggregated test runner:

```bash
./gradlew testDebugUnitTest --tests com.test.TestAll
```

To focus on the sample local test class directly:

```bash
./gradlew testDebugUnitTest --tests com.test.ExampleUnitTest
```

Instrumented tests are typically run on an emulator or device.

## Current state of the repo

This repository is currently more of a testing starter/scaffold than a complete app. The Java files show:

- how to reference FTC SDK code
- how to reference Pedro Pathing code
- how to organize local tests
- how to add an instrumented Android test

The next logical step would be to replace `placeholder.java` with real robot subsystems, utilities, and logic classes, then add corresponding tests under `app/src/test/java`.
