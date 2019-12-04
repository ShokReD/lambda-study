package name.shokred;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
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

    @Test
    public void task2c() {
        Stream<String> stream = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        Map<String, List<String>> map = new HashMap<>();
        map.put("John", Arrays.asList("John", "John", "John"));
        map.put("Paul", Arrays.asList("Paul", "Paul"));
        map.put("George", Collections.singletonList("George"));
        Assert.assertEquals(map, stream.collect(new GroupingBy<>()));
    }

    public static class GroupingBy<T> implements Collector<T, Map<T, List<T>>, Map<T, List<T>>> {

        @Override
        public Supplier<Map<T, List<T>>> supplier() {
            return HashMap::new;
        }

        @Override
        public BiConsumer<Map<T, List<T>>, T> accumulator() {
            return (kListMap, t) -> {
                kListMap.computeIfAbsent(t, k -> new ArrayList<>()).add(t);
            };
        }

        @Override
        public BinaryOperator<Map<T, List<T>>> combiner() {
            return (kListMap, kListMap2) -> {
                kListMap.forEach((k, ts) -> ts.addAll(kListMap2.get(k)));
                return kListMap;
            };
        }

        @Override
        public Function<Map<T, List<T>>, Map<T, List<T>>> finisher() {
            return kListMap -> kListMap;
        }

        @Override
        public Set<Characteristics> characteristics() {
            Set<Characteristics> characteristics = new HashSet<>();
            return characteristics;
        }
    }

    @Test
    public void task3() {
        Assert.assertEquals(1L, fib(1));
        Assert.assertEquals(1L, fib(2));
        Assert.assertEquals(8L, fib(6));
        Assert.assertEquals(5L, fib(5));
    }

    private long fib(int index, Map<Integer, Long> map) {
        return map.computeIfAbsent(index, x -> fib(x - 1, map) + fib(x - 2, map));
    }

    private long fib(int index) {
        if (index <= 0) {
            throw new IndexOutOfBoundsException();
        }
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(1, 1L);
        map.put(2, 1L);
        return fib(index, map);
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
