package bench.cpu;

import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.ILog;

public class CPURecursionLoopUnrolling implements IBenchmark {
    private long sum=0;
    private long size=0;
    private int counter=0;
    private long start=2;

    public long getNumber() {
    	return start;
    }
    private boolean isPrime(long x) {
        if(x <= 2)
            return true;
        for(int i = 2; i <= Math.sqrt(x); i++) {
            if(x % i == 0)
                return false;
        }
        return true;
    }
    private long recursive(long size, int counter) {
        if(start >= size)
            return 0;
        if(isPrime(start)) {
        	//System.out.println(start);
        	start++;
            return recursive(size, ++counter) + start;
        }
        start++;
        return recursive(size, counter);
    }
    private long recursiveUnrolled(int unrollLevel, long size, int counter) {
        if(start >= size)
            return 0;
        long sum=0;
        for(long i = start; i <= start+unrollLevel; i++) {
            if(isPrime(i))
            {
                sum += i;
                //System.out.println(i);
            }
        }
        start += unrollLevel;
        return sum + recursiveUnrolled(unrollLevel, size, ++counter);
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
                sum = recursiveUnrolled(unrollLevel, size, ++counter);
            } catch (StackOverflowError soe) {
                throw new StackOverflowError();
            }
        }
        else {
            try {
                sum = recursive(size, ++counter);
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
        return Long.toString(sum);
    }
}
