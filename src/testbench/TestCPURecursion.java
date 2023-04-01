package testbench;

import bench.IBenchmark;
import bench.cpu.CPURecursionLoopUnrolling;
import logging.ConsoleLogger;
import logging.ILog;
import logging.TimeUnit;
import timing.ITimer;
import timing.Timer;;

public class TestCPURecursion {
    public static void main(String[] args) {
        IBenchmark bench = new CPURecursionLoopUnrolling();
        ITimer timer = new Timer();
        ILog log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.Milli;

        long size = 1000000000;
        bench.initialize(size);
        try {
            timer.start();
            bench.run(false);
            long totalTime = timer.stop();
            log.write("Total time is: " + timeUnit.toTimeUnit(totalTime, timeUnit) + timeUnit.toString() + "\n" + "Total sum is: " + bench.getResult());
        } catch (StackOverflowError soe) {
            long totalTime = timer.stop();
            log.write("Run stopped at number:" + "/" + size + " with the total time of " );
        }
    }
}
