package name.shokred.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    private String name;
    private List<Artist> members;
    private String origin;

    public Artist(String name) {
        this.name = name;
    }
}
