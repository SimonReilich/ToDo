package ch.cern.todo.reminder;

import ch.cern.todo.note.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reminders")
public class ReminderController {

    public final NoteRepository noteRepository;
    public final ReminderRepository reminderRepository;

    @GetMapping
    public List<Reminder> getReminders() {
        return reminderRepository.findAll();
    }

    @GetMapping("{id}")
    public Reminder getReminder(@PathVariable Long id) {
        return reminderRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Reminder createReminder(@RequestBody ReminderContent reminder) {
        return reminderRepository.save(new Reminder(reminder));
    }

    @PutMapping("{id}")
    public Reminder updateReminder(@PathVariable Long id, @RequestBody ReminderContent reminder) {
        Reminder data = reminderRepository.findById(id).orElse(null);
        if (data != null) {
            data.setTitle(reminder.title());
            data.setDate(reminder.date());
            return reminderRepository.save(data);
        }
        return null;
    }

    @PutMapping("complete/{id}")
    public Reminder completeReminder(@PathVariable Long id) {
        Reminder reminder = reminderRepository.findById(id).orElse(null);
        if (reminder != null) {
            reminder.setDone(true);
            return reminderRepository.save(reminder);
        }
        return null;
    }

    @DeleteMapping("{id}")
    public void deleteReminder(@PathVariable Long id) {
        reminderRepository.deleteById(id);
    }
}
