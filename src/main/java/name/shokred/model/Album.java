package name.shokred.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private String name;
    private List<Track> tracks;
    private List<Artist> musicians;
}
