package testbench;

import bench.IBenchmark;
import bench.cpu.CPURecursionLoopUnrolling;
import logging.ConsoleLogger;
import logging.ILog;;

public class TestCPURecursion {
    IBenchmark bench = new CPURecursionLoopUnrolling();
    ILog log = new ConsoleLogger();


}
