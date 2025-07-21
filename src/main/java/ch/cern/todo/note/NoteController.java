package ch.cern.todo.note;

import ch.cern.todo.reminder.Reminder;
import ch.cern.todo.reminder.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
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
        return noteRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Note createNote(@RequestBody NoteContent note) {
        reminderRepository.saveAll(note.reminders());
        return noteRepository.save(new Note(note));
    }

    @PutMapping("{id}")
    public Note updateNote(@PathVariable long id, @RequestBody NoteContent note) {
        Note data = noteRepository.findById(id).orElse(null);
        if (data != null) {
            data.setName(note.name());
            data.setDescription(note.description());
            return noteRepository.save(data);
        }
        return null;
    }

    @PutMapping("/{id}/reminders/{rId}")
    public Note assignReminder(@PathVariable Long id, @PathVariable Long rId) {
        Note note = noteRepository.findById(id).orElse(null);
        Reminder reminder = reminderRepository.findById(rId).orElse(null);
        if (note != null) {
            note.getReminders().add(reminder);
            if (reminder.getCategory() == null) {
                reminder.setCategory(note.getCategory());
            } else if (note.getCategory() == null) {
                note.setCategory(reminder.getCategory());
            }
            return noteRepository.save(note);
        }
        return null;
    }

    @DeleteMapping("/{id}/reminders/{rId}")
    public void removeReminder(@PathVariable Long id, @PathVariable Long rId) {
        Note note = noteRepository.findById(id).orElse(null);
        Reminder reminder = reminderRepository.findById(rId).orElse(null);
        if (note != null) {
            note.getReminders().remove(reminder);
            noteRepository.save(note);
        }
    }

    @DeleteMapping("{id}")
    public void deleteNote(@PathVariable long id) {
        noteRepository.deleteById(id);
    }
}
