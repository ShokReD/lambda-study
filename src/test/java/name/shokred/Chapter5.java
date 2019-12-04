package name.shokred;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter5 {

    @Test
    public void task1a() {
        Assert.assertEquals(Arrays.asList("UPPER", "ALMOST_UPPER", "LOWER"), toUpperCase(Arrays.asList("UPPER", "almost_UPPER", "lower")));
    }

    @Test
    public void task1b() {
        Assert.assertEquals(5L, countWithReduce(Arrays.asList(1, new Object(), 4L, "String", 'c')));
    }

    @Test
    public void task1c() {
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), concatenate(Arrays.asList(1, 2, 3), Collections.singletonList(4)));
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), concatenate(Arrays.asList(1, 2, 3, 4), Collections.emptyList()));
    }

    private List<String> toUpperCase(List<String> input) {
        return input.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    private long countWithReduce(List<Object> input) {
        return (long) input.stream().reduce(0L, (o, o2) -> ((long) o) + 1);
    }

    private List<?> concatenate(List<?> list1, List<?> list2) {
        return Stream.of(list1, list2).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
