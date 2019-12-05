package name.shokred;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;
import org.openjdk.jmh.runner.options.*;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Just run this class's main method and it will time benchmarks using the harness
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class OptimisationExample {

    public static void main(String[] ignore) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(OptimisationExample.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    private List<Integer> linkedListOfNumbers;

    @Setup
    public void init() {
        linkedListOfNumbers = new LinkedList<>();
        addNumbers(linkedListOfNumbers);

        // TODO: put any additional setup code here
    }

    private void addNumbers(List<Integer> container) {
        IntStream.range(0, 1_000_000)
                .forEach(container::add);
    }

    @Benchmark
    // BEGIN slowSumOfSquares
    public int slowSumOfSquares() {
        return linkedListOfNumbers.parallelStream()
                .map(x -> x * x)
                .reduce(0, Integer::sum);
    }
    // END slowSumOfSquares

    @Benchmark
    public int fastSumOfSquares() {
        return linkedListOfNumbers.parallelStream()
                .mapToInt(x -> x)
                .sum();
    }

}