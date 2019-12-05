package name.shokred;

import org.junit.Assert;
import org.junit.Test;
import java.util.stream.IntStream;

public class Chapter6 {

    @Test
    public void task1() {
        Assert.assertEquals(30, sequentialSumOfSquares(IntStream.range(1, 5)));
        Assert.assertEquals(30, parallelSumOfSquares(IntStream.range(1, 5)));
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
