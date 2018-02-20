package cat.tecnocampus.rest;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.domainController.NoteUseCases;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/")
public class NotesRESTController {
    private NoteUseCases noteUseCases;

    public NotesRESTController(NoteUseCases noteUseCases) {
        this.noteUseCases = noteUseCases;
    }


    @GetMapping(value = "/notes/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteLab> getUserNotes(@PathVariable String username) {
        List<NoteLab> noteLabs = noteUseCases.getUserNotes(username);
        return noteLabs;
    }

    @PostMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteLab createNote(@RequestBody @Valid NoteLab note) {
        noteUseCases.createNote(note);

        return note;
    }

    @DeleteMapping(value = "notes/{username}")
    public int deleteUserNotes(@PathVariable String username) {
        return noteUseCases.deleteUserNotes(username);
    }

}
