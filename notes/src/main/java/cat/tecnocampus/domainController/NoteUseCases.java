package cat.tecnocampus.domainController;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.persistence.NoteLabDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by roure on 14/09/2017.
 *
 * All methods update the database
 */
@Service("noteUseCases")
public class NoteUseCases {

    private final NoteLabDAO noteLabDAO;

    public NoteUseCases(NoteLabDAO NoteLabDAO) {
        this.noteLabDAO = NoteLabDAO;
    }


    public NoteLab addNote(NoteLab noteLab) {
        noteLabDAO.insert(noteLab);

        return noteLab;
    }

    public NoteLab createNote(NoteLab noteLab) {
        noteLab.setDateCreation(LocalDateTime.now());
        noteLab.setDateEdit(LocalDateTime.now());

        return addNote(noteLab);
    }

    public NoteLab deleteNote(NoteLab note) {
        noteLabDAO.deleteNote(note);
        return note;
    }

    public int deleteUserNotes(String userName) {
        return noteLabDAO.deleteUserNotes(userName);
    }

    public List<NoteLab> getUserNotes(String userName) {
        return noteLabDAO.findByUsername(userName);
    }


    public List<NoteLab> getAllNotes() {
        return noteLabDAO.findAll();
    }


}
