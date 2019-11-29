package name.shokred;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import name.shokred.model.Album;
import name.shokred.model.Artist;
import name.shokred.model.Track;

public class Chapter3 {

    @Test
    public void task1a() {
        Assert.assertEquals(10, addUp(Stream.of(1, 2, 3, 4)));
    }

    @Test
    public void task1b() {
        Assert.assertEquals(Arrays.asList("artist1", "artist2", "russia"), names(new Artist("band1", artists(), "russia")));
    }

    @Test
    public void task1c() {
        Assert.assertEquals(
                Collections.singletonList(shortAlbum()),
                findShortsAlbums(Arrays.asList(shortAlbum(), longAlbum()))
        );
    }

    @Test
    public void task2() {
        List<Artist> artists = artists();

        Assert.assertEquals(2, artists.stream().map(Artist::getMembers).count());
        /*int totalMembers = 0;
        for (Artist artist : artists) {
            Stream<Artist> members = artist.getMembers().stream();
            totalMembers += members.count();
        }*/
    }

    @Test
    public void task6() {
        Assert.assertEquals(11L, lowerLettersCount("assertEquals"));
    }

    @Test
    public void task7() {
        Assert.assertEquals("assertEquals", maxLowerCaseString(Arrays.asList("assertEquals", "hello")).get());
    }

    @Test
    public void task7ifListEmpty() {
        Assert.assertFalse(maxLowerCaseString(Collections.emptyList()).isPresent());
    }

    @Test
    public void hardTask1() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Assert.assertEquals(Arrays.asList(2, 4, 6), map(integerStream, x -> x * 2));
    }

    private <I, O> List<O> map(Stream<I> input, Function<I, O> mapper) {
        return input.reduce(new ArrayList<>(), (ts, i) -> {
            List<O> os = new ArrayList<>(ts);
            os.add(mapper.apply(i));
            return os;
        }, (List<O> ts, List<O> ts2) -> {
            List<O> os = new ArrayList<>(ts);
            os.addAll(ts2);
            return os;
        });
    }

    private Optional<String> maxLowerCaseString(List<String> list) {
        return list.stream().max(Comparator.comparingLong(this::lowerLettersCount));
    }

    private long lowerLettersCount(String string) {
        return string.chars().filter(Character::isLowerCase).count();
    }

    private int addUp(Stream<Integer> numbers) {
        return numbers.mapToInt(x -> x).sum();
    }

    private List<String> names(Artist artist) {
        return Stream.concat(
                artist.getMembers().stream().map(Artist::getName),
                Stream.of(artist.getOrigin())
        ).collect(Collectors.toList());
    }

    private List<Album> findShortsAlbums(List<Album> albums) {
        return albums.stream().filter(x -> x.getTracks().size() <= 3).collect(Collectors.toList());
    }

    private Album shortAlbum() {
        return new Album(
                "shortAlbum",
                Arrays.asList(new Track("track1"), new Track("track2")),
                Collections.singletonList(new Artist("artist1"))
        );
    }

    private Album longAlbum() {
        return new Album(
                "longAlbum",
                Arrays.asList(
                        new Track("track1"),
                        new Track("track2"),
                        new Track("track3"),
                        new Track("track4"),
                        new Track("track5"),
                        new Track("track6")
                ),
                Collections.singletonList(new Artist("artist2"))
        );
    }

    private List<Artist> artists() {
        return Arrays.asList(new Artist("artist1"), new Artist("artist2"));
    }
}
