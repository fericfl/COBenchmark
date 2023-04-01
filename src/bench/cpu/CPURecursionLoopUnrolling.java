package bench.cpu;

import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.ILog;

public class CPURecursionLoopUnrolling implements IBenchmark {
    private Long sum=Long.valueOf(0);
    private long size;
    private int counter=0;
    private long start=0;

    private boolean isPrime(long x) {
        if(x <= 2)
            return true;
        for(int i = 2; i <= Math.sqrt(x); i++) {
            if(x % i == 0)
                return false;
        }
        return true;
    }
    private long recursive(long start, long size, int counter) {
        if(start >= size)
            return 0;
        if(isPrime(start)) {
            return start + recursive(start+1, size, ++counter);
        }
        return 0;
    }
    private long recursiveUnrolled(long start, int unrollLevel, long size, int counter) {
        if(start >= size)
            return 0;
        long sum=0;
        for(long i = start; i <= start+unrollLevel; i++) {
            if(isPrime(i))
                sum += i;
        }
        return sum + recursiveUnrolled(start+unrollLevel, unrollLevel, size, ++counter);
    }
    @Override
    public void initialize(Object... params) {
        size = (Long) params[0];
    }

    @Override
    public void warmUp() {

    }

    @Override
    public void run() {

    }

    @Override
    public void run(Object... options) {
        boolean isUnrolled = (Boolean) options[0];
        if(isUnrolled) {
            int unrollLevel = (Integer) options[1];
            try {
                sum = recursiveUnrolled(start, unrollLevel, size, ++counter);
            } catch (StackOverflowError soe) {
                throw new StackOverflowError();
            }
        }
        else {
            try {
                sum = recursive(start, size, ++counter);
            } catch (StackOverflowError soe) {
                throw new StackOverflowError();
            }
        }
    }

    @Override
    public void warmup(int scale) {

    }

    @Override
    public void warmup() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void clean() {

    }
    @Override
    public String getResult() {
        return sum.toString();
    }
}
