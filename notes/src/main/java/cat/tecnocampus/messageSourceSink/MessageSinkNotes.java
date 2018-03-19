package cat.tecnocampus.messageSourceSink;

import cat.tecnocampus.domainController.NoteUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(MessageSinkNotes.InputChannels.class)
public class MessageSinkNotes {
    private NoteUseCases noteUseCases;

    @Autowired
    public MessageSinkNotes(NoteUseCases noteUseCases) {
        this.noteUseCases = noteUseCases;
    }


    @StreamListener(InputChannels.INPUT)
    public void deleteNotesSink(String message) {
        noteUseCases.deleteUserNotes(message);
    }

    @StreamListener(InputChannels.ANSWER)
    public void deleteNotesIfUserDoesNotExist(String message) {
        String[] answer = message.split(":");
        
        if (answer[0].equalsIgnoreCase("no")) {
            noteUseCases.deleteUserNotes(answer[1]);
        }
        else {
            noteUseCases.updateNoteExists(answer[1]);
        }
    }


    public interface InputChannels {
        public String INPUT = "input";
        public String ANSWER = "inputAnswer";

        @Input
        SubscribableChannel input();

        @Input
        SubscribableChannel inputAnswer();
    }

}
