package name.shokred;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chapter6 {

    @Test
    public void task1() {
        Assert.assertEquals(30, sequentialSumOfSquares(IntStream.range(1, 5)));
        Assert.assertEquals(30, parallelSumOfSquares(IntStream.range(1, 5)));
    }

    @Test
    public void task2() {
        Assert.assertEquals(600, multiplyThrough(IntStream.range(1, 6).boxed().collect(Collectors.toList())));
    }

    private static int multiplyThrough(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.parallelStream()
                .reduce(1, (acc, x) -> x * acc) * 5;
    }

    private static int sequentialSumOfSquares(IntStream range) {
        return range
                .map(x -> x * x)
                .sum();
    }

    private static int parallelSumOfSquares(IntStream range) {
        return range
                .parallel()
                .map(x -> x * x)
                .sum();
    }
}
