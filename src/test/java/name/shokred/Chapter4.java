package name.shokred;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import name.shokred.model.Artist;

public class Chapter4 {

    @Test
    public void task1() {
        Assert.assertEquals(
                Stream.of(new Artist("1"), new Artist("2"), new Artist("3")).map(Artist::getName).collect(Collectors.toList()),
                new Performance() {
                    @Override
                    public String getName() {
                        return "name";
                    }

                    @Override
                    public Stream<Artist> getMusicians() {
                        return Stream.of(new Artist("1", Arrays.asList(new Artist("2"), new Artist("3")), "orgigin"));
                    }
                }.getAllMusicians().map(Artist::getName).collect(Collectors.toList()));
    }

    @Test
    public void task3() {
        Artists artists = new Artists(Collections.singletonList(new Artist("name")));

        Assert.assertEquals("name", artists.getArtistName(0));
        Assert.assertEquals("unknown", artists.getArtistName(1));
    }

    public interface Performance {
        String getName();

        Stream<Artist> getMusicians();

        default Stream<Artist> getAllMusicians() {
            return Stream.concat(getMusicians(), getMusicians().flatMap(x -> x.getMembers().stream()));
        }
    }

    public static class Artists {
        private List<Artist> artists;

        public Artists(List<Artist> artists) {
            this.artists = artists;
        }

        public Optional<Artist> getArtist(int index) {
            if (index < 0 || index >= artists.size()) {
                return Optional.empty();
            }

            return Optional.of(artists.get(index));
        }

        public String getArtistName(int index) {
            return getArtist(index).map(Artist::getName).orElse("unknown");
        }
    }
}
