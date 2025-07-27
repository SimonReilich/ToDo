package ch.cern.todo.reminder;

import ch.cern.todo.errors.BadRequestException;
import ch.cern.todo.tag.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String title;
    @Column
    private String date;
    @Column
    private boolean done = false;
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tag tag;

    Reminder(ReminderContent content) {
        title = content.title().trim();
        date = content.date().trim();
        done = false;
        tag = content.tag();
        if (title.isEmpty()) throw new BadRequestException();
    }
}
