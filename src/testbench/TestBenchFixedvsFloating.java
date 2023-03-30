package testbench;


import bench.IBenchmark;
import bench.cpu.CPUFixedVsFloatingPoint;
import bench.cpu.CPUFixedvsFloating;
import bench.cpu.NumberRepresentation;
import logging.ConsoleLogger;
import logging.ILog;
import timing.ITimer;
import timing.Timer;


import static logging.TimeUnit.Sec;
import static logging.TimeUnit.toTimeUnit;

public class TestBenchFixedvsFloating {



    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILog log = /* new FileLogger("bench.log"); */new ConsoleLogger();


        IBenchmark bench = new CPUFixedvsFloating();
        bench.initialize(10000000);
        bench.warmUp();

        timer.start();
        //bench.run(NumberRepresentation.FIXED);
		bench.run(NumberRepresentation.FLOATING);
        long time = timer.stop();
        log.write("Finished in",toTimeUnit(time,Sec));
        System.out.println();
        log.write("Result is", bench.getResult());

        bench.clean();
        log.close();
    }
}
