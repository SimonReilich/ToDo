package ch.cern.todo.reminder;

import ch.cern.todo.errors.ReminderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reminders")
public class ReminderController {

    public final ReminderRepository reminderRepository;

    @GetMapping
    public List<Reminder> getReminders() {
        return reminderRepository.findAll();
    }

    @GetMapping("{id}")
    public Reminder getReminder(@PathVariable Long id) {
        return reminderRepository.findById(id).orElseThrow(() -> new ReminderNotFoundException(id));
    }

    @PostMapping
    public Reminder createReminder(@RequestBody ReminderContent reminder) {
        return reminderRepository.save(new Reminder(reminder));
    }

    @PutMapping("{id}")
    public Reminder updateReminder(@PathVariable Long id, @RequestBody ReminderContent reminder) {
        Reminder data = reminderRepository.findById(id).orElseThrow(() -> new ReminderNotFoundException(id));
        data.setTitle(reminder.title());
        data.setDate(reminder.date());
        data.setCategory(reminder.category());
        return reminderRepository.save(data);
    }

    @PutMapping("complete/{id}")
    public Reminder completeReminder(@PathVariable Long id) {
        Reminder reminder = reminderRepository.findById(id).orElseThrow(() -> new ReminderNotFoundException(id));
        reminder.setDone(true);
        return reminderRepository.save(reminder);
    }

    @DeleteMapping("{id}")
    public void deleteReminder(@PathVariable Long id) {
        reminderRepository.deleteById(id);
    }
}
