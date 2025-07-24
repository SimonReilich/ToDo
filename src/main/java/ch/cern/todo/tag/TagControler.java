package ch.cern.todo.tag;

import ch.cern.todo.errors.NoteNotFoundException;
import ch.cern.todo.errors.ReminderNotFoundException;
import ch.cern.todo.reminder.Reminder;
import ch.cern.todo.reminder.ReminderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagControler {

    private final TagRepository tagRepository;


}
