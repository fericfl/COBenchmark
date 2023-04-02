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

        long size = 5000;
        bench.initialize(size);
        try {
            timer.start();
            bench.run(false);
            long totalTime = timer.stop();
            score = ((bench.getNumber()) / TimeUnit.toTimeUnit(totalTime, timeUnit));
            log.write("Total time is: " + TimeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit + "\n" + "Total sum is: " + bench.getResult()
             + "\nThe score is: " + score);
        } catch (StackOverflowError soe) {
            long totalTime = timer.stop();
            score = ((bench.getNumber()) / TimeUnit.toTimeUnit(totalTime, timeUnit)/10);
            log.write("Run stopped at number:" + bench.getNumber() + "/" + size + "\nTotal time: " + TimeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit + "\nTotal score is: " + score);
        }
    }
}
