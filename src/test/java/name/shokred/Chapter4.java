package name.shokred;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
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

    public interface Performance {
        String getName();

        Stream<Artist> getMusicians();

        default Stream<Artist> getAllMusicians() {
            return Stream.concat(getMusicians(), getMusicians().flatMap(x -> x.getMembers().stream()));
        }
    }
}
