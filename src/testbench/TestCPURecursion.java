package testbench;

import bench.cpu.CPURecursionLoopUnrolling;
import logging.ConsoleLogger;
import logging.ILog;
import logging.TimeUnit;
import timing.ITimer;
import timing.Timer;



public class TestCPURecursion {
    public static void main(String[] args) {
        CPURecursionLoopUnrolling bench = new CPURecursionLoopUnrolling();
        ITimer timer = new Timer();
        ILog log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.Milli;
        double score=0;

        bench.initialize(Long.parseLong("4000"));
        bench.warmup();

        long size = 500000;
        bench.initialize(size);
        try {
            timer.start();
            bench.run(false);
            long totalTime = timer.stop();
            score = ((bench.getNumber()) / TimeUnit.toTimeUnit(totalTime, timeUnit));
            //log.write("Total time is: " + TimeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit + "\n" + "Total sum is: " + bench.getResult() + "\nThe score is: " + score);
        } catch (StackOverflowError soe) {
            long totalTime = timer.stop();
            score = ((bench.getNumber()) / TimeUnit.toTimeUnit(totalTime, timeUnit)/10);
            //log.write("Run stopped at number:" + bench.getNumber() + "/" + size + "\nTotal time: " + TimeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit + "\nTotal score is: " + score);
        }

        long size2=1000;
        double score2=0;
        bench.initialize(size2);
        try {
            timer.start();
            bench.run(false);
            long totalTime = timer.stop();
            score2 = ((bench.getNumber()) / TimeUnit.toTimeUnit(totalTime, timeUnit));
            //log.write("Total time is: " + TimeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit + "\n" + "Total sum is: " + bench.getResult() + "\nThe score is: " + score2);
        } catch (StackOverflowError soe) {
            long totalTime = timer.stop();
            score2 = ((bench.getNumber()) / TimeUnit.toTimeUnit(totalTime, timeUnit)/10);
            //log.write("Run stopped at number:" + bench.getNumber() + "/" + size + "\nTotal time: " + TimeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit + "\nTotal score is: " + score2);
        }

        long size3=1000;
        double score3=0;
        bench.initialize(size3);
        try {
            timer.start();
            bench.run(true, 8);
            long totalTime = timer.stop();
            score3 = ((bench.getNumber()) / TimeUnit.toTimeUnit(totalTime, timeUnit));
            //log.write("Total time is: " + TimeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit + "\n" + "Total sum is: " + bench.getResult() + "\nThe score is: " + score3);
        } catch (StackOverflowError soe) {
            long totalTime = timer.stop();
            score3 = ((bench.getNumber()) / TimeUnit.toTimeUnit(totalTime, timeUnit)/10);
            //log.write("Run stopped at number:" + bench.getNumber() + "/" + size + "\nTotal time: " + TimeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit + "\nTotal score is: " + score3);
        }

        long size4=100000;
        double score4=0;
        bench.initialize(size4);
        try {
            timer.start();
            bench.run(true, 8);
            long totalTime = timer.stop();
            score4 = ((bench.getNumber()) / TimeUnit.toTimeUnit(totalTime, timeUnit));
            //log.write("Total time is: " + TimeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit + "\n" + "Total sum is: " + bench.getResult() + "\nThe score is: " + score4);
        } catch (StackOverflowError soe) {
            long totalTime = timer.stop();
            score4 = ((bench.getNumber()) / TimeUnit.toTimeUnit(totalTime, timeUnit)/10);
            //log.write("Run stopped at number:" + bench.getNumber() + "/" + size + "\nTotal time: " + TimeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit + "\nTotal score is: " + score4);
        }

        double totalScore = (score+score2+score3+score4) / 10;
        log.write("Your total score is: " + totalScore);
    }
}
