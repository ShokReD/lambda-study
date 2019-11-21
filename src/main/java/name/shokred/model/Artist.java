package name.shokred.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    private String name;
    private List<Artist> members;
    private String origin;
}
