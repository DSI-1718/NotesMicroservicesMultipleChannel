package cat.tecnocampus.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by roure on 8/09/2017.
 */
public class NoteLab {

    @NotNull
    private String title;
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateEdit;

    private String userName;

    public NoteLab() {
    }

    private NoteLab(NoteLabBuilder builder) {
        title = builder.title;
        content = builder.content;
        userName = builder.userName;
        dateCreation = builder.dateCreation;
        dateEdit = builder.dateEdit;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateEdit(LocalDateTime dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString(){
        return "NoteLab: "+this.title+", Content: "+ this.content;
    }

    public static class NoteLabBuilder {
        private final String title;
        private final String content;
        private final String userName;

        private LocalDateTime dateCreation;
        private LocalDateTime dateEdit;

        public NoteLabBuilder(String title, String contennt, String userName) {
            this.title = title;
            this.content = contennt;
            this.userName = userName;
        }

        public NoteLabBuilder dateCreation(LocalDateTime dateCreation) {
            this.dateCreation = dateCreation;
            return this;
        }

        public NoteLabBuilder dateEdit(LocalDateTime dateEdit) {
            this.dateEdit = dateEdit;
            return this;
        }

        public NoteLab build() {
            return new NoteLab(this);
        }
    }
}
