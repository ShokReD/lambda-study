package name.shokred;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        Assert.assertEquals(Arrays.asList("artist1", "artist2", "russia"), names(new Artist("band1", Arrays.asList(new Artist("artist1"), new Artist("artist2")), "russia")));
    }

    @Test
    public void task1c() {
        Assert.assertEquals(
                Collections.singletonList(shortAlbum()),
                findShortsAlbums(Arrays.asList(shortAlbum(), longAlbum()))
        );
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
}
