package testbench;


import bench.IBenchmark;
import bench.cpu.CPUFixedPoint;
import logging.ConsoleLogger;
import logging.ILog;

public class TestCPUFixedPoint {
    public static void main(String[] args){
        ILog log = new ConsoleLogger();

        IBenchmark bench = new CPUFixedPoint();
        bench.initialize(10000);

        bench.run(0);
        log.write(bench.getResult());

        bench.clean();
        log.close();
    }
}
