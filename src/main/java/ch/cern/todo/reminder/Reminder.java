package ch.cern.todo.reminder;

import ch.cern.todo.Category;
import ch.cern.todo.tag.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reminder {

    Reminder(ReminderContent content) {
        title = content.title().trim();
        date = content.date().trim();
        category = content.category();
        done = false;
        tag = content.tag();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String title;

    @Column
    private String date;

    @Column
    private Category category;

    @Column
    private boolean done = false;

    @Column
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tag tag;
}
