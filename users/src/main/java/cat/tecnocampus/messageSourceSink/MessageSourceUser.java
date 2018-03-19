package cat.tecnocampus.messageSourceSink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(MessageSourceUser.OutputChannels.class)
public class MessageSourceUser {
    private MessageChannel source;
    private MessageChannel outAnswer;

    @Autowired @Qualifier(OutputChannels.OUTPUT)
    public void setNotesOutputChannel(MessageChannel source) {
        this.source = source;
    }

    @Autowired @Qualifier(OutputChannels.OUTPUT_ANSWER)
    public void setOutAnswerChannel(MessageChannel outputAnswer) {
        this.outAnswer = outputAnswer;
    }

    public void deleteUserNotes(String username) {
        source.send(MessageBuilder.withPayload(username).build());
    }

    public interface OutputChannels {
        public String OUTPUT_ANSWER = "outputAnswer";

        public String OUTPUT = "output";

        @Output
        MessageChannel output();

        @Output
        MessageChannel outputAnswer();
    }

}
