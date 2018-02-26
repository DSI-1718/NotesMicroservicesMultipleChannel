package cat.tecnocampus.messageSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class MessageSourceUser {
    private Source source;

    @Autowired
    public MessageSourceUser(Source source) {
        this.source = source;
    }

    public void deleteUserNotes(String username) {
        source.output().send(MessageBuilder.withPayload(username).build());
    }
}
