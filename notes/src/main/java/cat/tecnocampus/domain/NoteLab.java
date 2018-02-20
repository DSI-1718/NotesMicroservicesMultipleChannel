package cat.tecnocampus.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    //@JsonIgnore
    private boolean checked;

    public NoteLab() {
    }

    private NoteLab(NoteLabBuilder builder) {
        title = builder.title;
        content = builder.content;
        userName = builder.userName;
        dateCreation = builder.dateCreation;
        dateEdit = builder.dateEdit;
        checked = builder.checked;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public static class NoteLabBuilder {
        private final String title;
        private final String content;
        private final String userName;
        private boolean checked;

        private LocalDateTime dateCreation;
        private LocalDateTime dateEdit;

        public NoteLabBuilder(String title, String content, String userName) {
            this.title = title;
            this.content = content;
            this.userName = userName;
            this.checked = false;
        }

        public NoteLabBuilder dateCreation(LocalDateTime dateCreation) {
            this.dateCreation = dateCreation;
            return this;
        }

        public NoteLabBuilder dateEdit(LocalDateTime dateEdit) {
            this.dateEdit = dateEdit;
            return this;
        }

        public NoteLabBuilder checked(boolean checked) {
            this.checked = checked;
            return  this;
        }

        public NoteLab build() {
            return new NoteLab(this);
        }
    }
}
