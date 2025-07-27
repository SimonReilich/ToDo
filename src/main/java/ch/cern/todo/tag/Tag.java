package ch.cern.todo.tag;

import ch.cern.todo.errors.BadRequestException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(unique=true)
    private String name;

    public Tag(@Valid TagContent tag) {
        this.name = tag.name().trim();
        if (name.isEmpty()) throw new BadRequestException();
    }
}
