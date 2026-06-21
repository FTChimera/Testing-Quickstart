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

## How to organize your code and tests

When adding your own code to this project, use these folders consistently:

- `app/src/main/java` for robot code, utilities, helpers, and any production classes you want to test
- `app/src/test/java` for local unit tests that run on your computer's JVM
- `app/src/androidTest/java` for Android/instrumented tests that need a device, emulator, or Android framework access

Recommended workflow:

1. Put the code you actually want to test in `main`
2. Put fast logic-focused tests in `test`
3. Put hardware/framework/device-dependent tests in `androidTest`

For this repo, that means:

- robot logic classes should go under `app/src/main/java/com/test`
- plain verification code should go under `app/src/test/java/com/test`
- Android-dependent verification should go under `app/src/androidTest/java/com/test`

## Local unit tests vs Android tests

This project supports two different testing styles.

### Local unit tests (`app/src/test/java`)

Use local unit tests when:
- you are testing pure logic
- you want fast feedback
- your code does not need a real Android device or Android framework runtime

Characteristics:
- runs on the local JVM
- usually faster than instrumented tests
- good for math, state transitions, path calculations, helper methods, and business logic

In this repo:
- `ExampleUnitTest.java` is the example local unit test
- `TestAll.java` is a custom aggregator that can run selected local test classes together

### Android/instrumented tests (`app/src/androidTest/java`)

Use Android tests when:
- you need `Context`
- you need Android framework APIs
- you need behavior that depends on an emulator, device, or Android runtime

Characteristics:
- runs on an Android device or emulator
- slower than local JVM tests
- useful for Android integration behavior and framework-dependent code

In this repo:
- `ExampleInstrumentedTest.java` is the example Android test
- it checks the package name using the target app `Context`

### Simple rule of thumb

If the code can be tested without Android, put the test in `test`.

If the code requires Android runtime objects like `Context`, framework services, or device execution, put the test in `androidTest`.

## Java source layout

### Production source

#### `app/src/main/java/com/test/placeholder.java`

This file is a placeholder/example class showing that the project can resolve and use classes from both the FTC SDK and Pedro Pathing libraries.

It demonstrates references to:
- FTC SDK classes such as `OpMode`, `OpModeManagerImpl`, and `Servo`
- Pedro Pathing classes such as `Follower`, `FollowerConstants`, `PinpointLocalizer`, and `Mecanum`

The file is not a full robot implementation. Instead, it acts as a proof-of-setup file that shows how code in this repository can access the libraries needed for robot logic and pathing experiments.

### Local unit tests

#### `app/src/test/java/com/test/TestAll.java`

This is the custom local test runner for the repository.


What it does:
- defines `configuredClasses()`
- registers test classes by assigning `testClasses` (testClasses is the list of local test classes that will be run when this runner is executed)
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


In other words, this repository uses:
- the **FTC SDK**
- **Pedro Pathing**
- **Bylazar FullPanels**

through the shared `libs.bundles.ftc` dependency bundle.

## How the Java code fits together

At a high level:

1. Java robot-related code lives in `app/src/main/java`
2. Plain logic tests live in `app/src/test/java`
3. Android/device-dependent tests live in `app/src/androidTest/java`
4. `TestAll` can be used as a manual aggregation point for selected local test classes

## How to set up unit tests in this repo

Use this pattern whenever you add a new feature or subsystem.

### 1. Add or move the robot code into `main`

Create your actual implementation classes under:

- `app/src/main/java/com/test`

Examples of what belongs there:
- drivetrain helpers
- subsystem logic
- motion/path utilities
- calculation helpers
- wrappers around FTC, Panels or Pedro Pathing behavior

### 2. Add local unit tests in `test`

For logic that can run without Android, create a matching test class under:

- `app/src/test/java/com/test`

Basic pattern:
- create a test class
- add methods annotated with `@Test`
- assert expected behavior with JUnit assertions

If you want the class included in the custom aggregated runner, add it to `TestAll.configuredClasses()`.

### 3. Add Android/instrumented tests in `androidTest`

For code that needs Android runtime behavior, create tests under:

- `app/src/androidTest/java/com/test`

Use this for:
- `Context`-dependent code
- Android framework integration
- behavior that only makes sense on a device or emulator

## How to set everything up and use it

### 1. Open the project

Open the repository in Android Studio.

### 2. Sync Gradle

Let Android Studio sync the project so the dependencies from `libs.bundles.ftc` are available.

That bundle provides:
- FTC SDK dependencies
- Pedro Pathing dependencies

### 3. Add your robot-related classes

Start by replacing `placeholder.java` or by adding new classes beside it in:

- `app/src/main/java/com/test`

Keep the production code in `main`, not in `test`.

### 4. Add fast local tests for logic

For every logic-heavy class, add one or more local tests under:

- `app/src/test/java/com/test`

This is the best place for:
- calculations
- state-machine behavior
- path or control logic that does not require Android runtime

### 5. Optionally register local test classes in `TestAll`

If you want one top-level suite, edit `TestAll.configuredClasses()` and add your local test classes there.

Example idea:
- add `DriveMathTest.class`
- add `ArmControllerTest.class`
- add `PathPlannerTest.class`

Then run the aggregated suite through `TestAll`.

### 6. Add Android tests only when needed

Only place tests in `androidTest` when they need Android-specific behavior.

This keeps most of your tests fast and easy to run locally.

### 7. Run the tests

Run all local unit tests:

```bash
./gradlew testDebugUnitTest
```

Run only the custom local suite:

```bash
./gradlew testDebugUnitTest --tests com.test.TestAll
```

Run one local test class directly:

```bash
./gradlew testDebugUnitTest --tests com.test.ExampleUnitTest
```

Run instrumented tests on a device or emulator:

```bash
./gradlew connectedDebugAndroidTest
```

## Suggested way to use this repo

1. Keep all real robot/application code in `main`
2. Write most tests in `test`
3. Use `androidTest` only for Android-specific behavior
4. Use `TestAll` if you want a manual, curated test suite entry point
5. Grow the repo by replacing `placeholder.java` with real subsystems and utilities


## Current state of the repo

This repository is currently more of a testing starter/scaffold than a complete app. The Java files show:

- how to reference FTC SDK code
- how to reference Pedro Pathing code
- how to organize local tests
- how to add an instrumented Android test

The next logical step would be to replace `placeholder.java` with real robot subsystems, utilities, and logic classes, then add corresponding tests under `app/src/test/java`.
