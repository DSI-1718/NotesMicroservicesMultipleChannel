package cat.tecnocampus.persistence;

import cat.tecnocampus.domain.NoteLab;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by josep on 8/9/17.
 */
@Repository
public class NoteLabDAO {
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_NOTE = "insert into note_lab (title, content, date_creation, date_edit, owner, checked) values(?, ?, ?, ?, ?, ?)";
    private final String FIND_ALL = "select * from note_lab";
    private final String FIND_BY_USERNAME = "select * from note_lab where owner = ? order by date_edit desc";
    private final String FIND_BY_ID = "select * from note_lab where id = ?";
    private final String UPDATE_NOTE = "update note_lab set title = ?, content = ?, date_edit = ? where date_creation = ? and title = ? and owner = ?";
    private final String DELETE_NOTE = "delete note_lab where title = ? and date_creation = ?";
    private final String EXISTS_NOTE = "select count(*) from note_lab where title = ? and date_creation = ?";
    private final String DELETE_USER_NOTES = "delete note_lab where owner = ?";
    private final String UPDATE_NOTE_EXISTS = "update note_lab set checked = true where owner = ?";

    private RowMapper<NoteLab> mapper = (resultSet, i) -> {
        NoteLab noteLab = new NoteLab.NoteLabBuilder(resultSet.getString("title"), resultSet.getString("content"),
                                                     resultSet.getNString("owner"))
                .dateCreation(resultSet.getTimestamp("date_creation").toLocalDateTime())
                .dateEdit(resultSet.getTimestamp("date_edit").toLocalDateTime())
                .checked(resultSet.getBoolean("checked"))
                .build();
        return noteLab;
    };

    public NoteLabDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<NoteLab> findAll() {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    public List<NoteLab> findByUsername(String username) {
        return jdbcTemplate.query(FIND_BY_USERNAME, new Object[]{username}, mapper);
    }

    public NoteLab findById(int id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id},mapper);
    }


    public int insert(NoteLab noteLab) {
        return jdbcTemplate.update(INSERT_NOTE, noteLab.getTitle(), noteLab.getContent(), Timestamp.valueOf(noteLab.getDateCreation()),
                Timestamp.valueOf(noteLab.getDateEdit()), noteLab.getUserName(), noteLab.isChecked());
    }

    public int updateNote(String oldTitle, NoteLab note) {
        return jdbcTemplate.update(UPDATE_NOTE,
                note.getTitle(), note.getContent(), note.getDateEdit(), note.getDateCreation(), oldTitle, note.getUserName());
    }

    public int updateNoteExists(String userName) {
        return jdbcTemplate.update(UPDATE_NOTE_EXISTS, userName);
    }

    public int deleteNote(NoteLab note) {
        return jdbcTemplate.update(DELETE_NOTE, note.getTitle(), note.getDateCreation());
    }

    public int deleteUserNotes(String userName) {
        return jdbcTemplate.update(DELETE_USER_NOTES, userName);
    }

    public boolean existsNote(NoteLab note) {
        int countOfNotes = jdbcTemplate.queryForObject(
                EXISTS_NOTE, Integer.class, note.getTitle(), Timestamp.valueOf(note.getDateCreation()));
        return countOfNotes > 0;
    }

}
