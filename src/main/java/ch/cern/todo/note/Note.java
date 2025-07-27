package ch.cern.todo.note;

import ch.cern.todo.errors.BadRequestException;
import ch.cern.todo.reminder.Reminder;
import ch.cern.todo.tag.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @OneToMany
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Reminder> reminders;

    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tag tag;

    Note(NoteContent content) {
        name = content.name().trim();
        description = content.description().trim();
        reminders = content.reminders();
        tag = content.tag();
        if (name.isEmpty()) throw new BadRequestException();
    }
}
