package ch.cern.todo.note;

import ch.cern.todo.Category;
import ch.cern.todo.reminder.Reminder;
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

    @Column
    private Category category;

    Note(NoteContent content) {
        name = content.name();
        description = content.description();
        reminders = content.reminders();
        category = content.category();
    }
}
