package ch.cern.todo.note;

import ch.cern.todo.errors.BadRequestException;
import ch.cern.todo.errors.NoteNotFoundException;
import ch.cern.todo.errors.ReminderNotFoundException;
import ch.cern.todo.reminder.Reminder;
import ch.cern.todo.reminder.ReminderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository noteRepository;
    private final ReminderRepository reminderRepository;


    @GetMapping
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("{id}")
    public Note getNote(@PathVariable Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @PostMapping
    public Note createNote(@Valid @RequestBody NoteContent note) {
        //if (note.reminders() == null) throw new BadRequestException();
        reminderRepository.saveAll(note.reminders());
        return noteRepository.save(new Note(note));
    }

    @PutMapping("{id}")
    public Note updateNote(@PathVariable long id, @RequestBody NoteContent note) {
        if (note == null) throw new BadRequestException();
        Note data = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        data.setName(note.name());
        data.setDescription(note.description());
        return noteRepository.save(data);
    }

    @PutMapping("/{id}/reminders/{rId}")
    public Note assignReminder(@PathVariable Long id, @PathVariable Long rId) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        Reminder reminder = reminderRepository.findById(rId).orElseThrow(() -> new ReminderNotFoundException(rId));
        note.getReminders().add(reminder);
        if (reminder.getCategory() == null) {
            reminder.setCategory(note.getCategory());
        } else if (note.getCategory() == null) {
            note.setCategory(reminder.getCategory());
        }
        return noteRepository.save(note);
    }

    @DeleteMapping("/{id}/reminders/{rId}")
    public void removeReminder(@PathVariable Long id, @PathVariable Long rId) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        Reminder reminder = reminderRepository.findById(rId).orElse(null);
        note.getReminders().remove(reminder);
        noteRepository.save(note);
    }

    @DeleteMapping("{id}")
    public void deleteNote(@PathVariable long id) {
        noteRepository.deleteById(id);
    }
}
