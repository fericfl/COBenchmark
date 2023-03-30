package testbench;

import bench.IBenchmark;
import bench.cpu.CPUDigitsOfPI;
import logging.ConsoleLogger;
import logging.ILog;
import timing.ITimer;
import timing.Timer;

import static logging.TimeUnit.Sec;
import static logging.TimeUnit.toTimeUnit;

public class TestBenchCPU {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILog log = new ConsoleLogger();
        IBenchmark bench = new CPUDigitsOfPI();

        timer.start();
        bench.warmup(10000);
        long time;
        time = timer.stop();
        log.write("Warmup finished in " , toTimeUnit(time,Sec),"Seconds");
        System.out.println("");
        timer.start();
        bench.run(2,1000000);
        time = timer.stop();

        log.write("Finished in",toTimeUnit(time,Sec),"");
        log.close();

    }
}
