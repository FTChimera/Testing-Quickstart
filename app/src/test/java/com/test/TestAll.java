package com.test;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class TestAll {

    /** Configure your classes here*/
    public static void configuredClasses() {
        UnitTest.testClasses = List.of(
                ExampleUnitTest.class
        );

    }


    @Test public void testAll() {
        configuredClasses();
        for (Class<? extends UnitTest> testClass : UnitTest.testClasses) {
            try {
                Arrays.stream(testClass.getDeclaredMethods())
                        .filter(method -> method.isAnnotationPresent(Test.class))
                        .forEach(method -> {
                            try {
                                method.invoke(testClass.getConstructor().newInstance());
                            } catch (Throwable e) {
                                throw new RuntimeException(e);
                            }
                        });
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
}
