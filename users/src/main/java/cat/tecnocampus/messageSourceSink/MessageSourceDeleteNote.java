package cat.tecnocampus.messageSourceSink;

import cat.tecnocampus.domainController.UserUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(MessageSourceDeleteNote.OutputChannels.class)
public class MessageSourceDeleteNote {
    private MessageChannel source;

    @Autowired @Qualifier(OutputChannels.OUTPUT)
    public void setNotesOutputChannel(MessageChannel source) {
        this.source = source;
    }

    public void deleteUserNotes(String username) {
        source.send(MessageBuilder.withPayload(username).build());
    }

    public interface OutputChannels {
        public String OUTPUT = "output";

        @Output
        MessageChannel output();

    }
}
