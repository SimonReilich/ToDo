package ch.cern.todo.tag;

import ch.cern.todo.errors.BadRequestException;
import ch.cern.todo.errors.TagNotFoundException;
import ch.cern.todo.note.Note;
import ch.cern.todo.note.NoteRepository;
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
    private final NoteRepository noteRepository;
    private final ReminderRepository reminderRepository;

    @GetMapping
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    @GetMapping("{id}")
    public Tag getTag(@PathVariable Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id));
    }

    @PostMapping
    public Tag createTag(@Valid @RequestBody TagContent tag) {
        if (tag.name().trim().isEmpty()) throw new BadRequestException();
        return tagRepository.save(new Tag(tag));
    }

    @PutMapping("{id}")
    public Tag updateTag(@PathVariable long id, @RequestBody TagContent tag) {
        Tag data = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id));
        if (tag.name().trim().isEmpty()) throw new BadRequestException();
        data.setName(tag.name());
        return tagRepository.save(data);
    }

    @PutMapping("{id1}/{id2}")
    public void mergeTags(@PathVariable long id1, @PathVariable long id2) {
        if (id1 == id2) throw new BadRequestException();
        Tag tag = tagRepository.findById(id1).orElseThrow(() -> new TagNotFoundException(id1));
        List<Note> notes = noteRepository.findAll();
        for (Note note : notes) {
            if (note.getTag().getId() == id2) {
                note.setTag(tag);
                noteRepository.save(note);
            }
        }
        List<Reminder> reminders = reminderRepository.findAll();
        for (Reminder reminder : reminders) {
            if (reminder.getTag().getId() == id2) {
                reminder.setTag(tag);
                reminderRepository.save(reminder);
            }
        }
    }

    @DeleteMapping("{id}")
    public void deleteTag(@PathVariable long id) {
        tagRepository.deleteById(id);
    }
}
