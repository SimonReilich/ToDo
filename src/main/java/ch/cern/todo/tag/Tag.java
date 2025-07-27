package ch.cern.todo.tag;

import ch.cern.todo.errors.BadRequestException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tag {

    private final static String REGEX = "^[a-zA-Z0-9]*$";
    private final static Pattern PATTERN = Pattern.compile(REGEX);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(unique=true)
    private String name;

    public Tag(@Valid TagContent tag) {
        this.name = tag.name().trim();
        if (name.isEmpty()) throw new BadRequestException();
        if (!PATTERN.matcher(name).matches()) throw new BadRequestException();
    }
}
