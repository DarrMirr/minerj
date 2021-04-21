package org.home.polukeev;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by onodee on 07.02.2016.
 * Запуск тестов
 */
public class TestRunner {
    public static void main(String args[]){
        Result result = JUnitCore.runClasses(TestMiner.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
