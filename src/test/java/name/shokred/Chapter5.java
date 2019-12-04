package name.shokred;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;
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

    @Test
    public void task2a() {
        List<String> names = Arrays.asList("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        Assert.assertEquals("Stuart Sutcliffe", maxByCollector(names.stream()));
        Assert.assertEquals("Stuart Sutcliffe", maxByReduce(names.stream()));
    }

    @Test
    public void task2b() {
        Stream<String> stream = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        Map<String, Long> map = new HashMap<>();
        map.put("John", 3L);
        map.put("Paul", 2L);
        map.put("George", 1L);
        Assert.assertEquals(map, group(stream));
    }

    private Map<String, Long> group(Stream<String> input) {
        return input.collect(Collectors.groupingBy(x -> x, Collectors.counting()));
    }

    private String maxByReduce(Stream<String> names) {
        return names.reduce((r1, r2) -> r1.length() > r2.length() ? r1 : r2).orElse(null);
    }

    private String maxByCollector(Stream<String> names) {
        return names.collect(Collectors.maxBy((Comparator<Object>) (o1, o2) -> ((String) o1).length() - ((String) o2).length())).orElse(null);
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
