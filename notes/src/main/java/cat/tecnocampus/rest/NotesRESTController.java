package cat.tecnocampus.rest;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.domainController.NoteUseCases;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/")
public class NotesRESTController {
    private NoteUseCases noteUseCases;
    private RestTemplate restTemplate;

    private final String FALSE = "false";

    public NotesRESTController(NoteUseCases noteUseCases, RestTemplate restTemplate) {
        this.noteUseCases = noteUseCases;
        this.restTemplate = restTemplate;
    }


    @GetMapping(value = "/notes/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteLab> getUserNotes(@PathVariable String username) {
        List<NoteLab> noteLabs = noteUseCases.getUserNotes(username);
        return noteLabs;
    }

    @GetMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteLab> getAllNotes() {
        List<NoteLab> noteLabs = noteUseCases.getAllNotes();
        return noteLabs;
    }

    @PostMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
    @HystrixCommand(fallbackMethod = "saveUncheckedNote")
    public NoteLab createNote(@RequestBody @Valid NoteLab note) {
        String userExists = restTemplate.getForObject("http://localhost:8090/api/users/exists/" + note.getUserName(), String.class);

        if (userExists.equals(FALSE))
            throw new UserDoesNotExistException();

        note.setChecked(true);
        noteUseCases.createNote(note);
        return note;
    }

    public NoteLab saveUncheckedNote(NoteLab note) {
        note.setChecked(false);
        noteUseCases.createNote(note);

        return note;
    }

    @DeleteMapping(value = "/notes/{username}")
    public int deleteUserNotes(@PathVariable String username) {
        return noteUseCases.deleteUserNotes(username);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity handleUserDoesNotExist() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
