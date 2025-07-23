package ch.cern.todo.reminder;

import ch.cern.todo.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reminder {

    Reminder(ReminderContent content) {
        title = content.title();
        date = content.date();
        category = content.category();
        done = false;
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
}
