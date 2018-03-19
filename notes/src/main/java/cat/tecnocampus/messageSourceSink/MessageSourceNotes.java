package cat.tecnocampus.messageSourceSink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(MessageSourceNotes.OutputChannel.class)
public class MessageSourceNotes {

    private MessageChannel notesOutputChannel;

    @Autowired @Qualifier(OutputChannel.OUTQUESTION)
    public void setNotesOutputChannel(MessageChannel notesOutputChannel) {
        this.notesOutputChannel = notesOutputChannel;
    }

    public void sendQuestionExists(String message) {
        notesOutputChannel.send(MessageBuilder.withPayload(message).build());
    }


    public interface OutputChannel {
        public String OUTQUESTION = "outputQuestion";

        @Output
        MessageChannel outputQuestion();
    }

}
