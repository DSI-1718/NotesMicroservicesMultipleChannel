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

@EnableBinding(value = {MessageInQuestionOutAnswer.OutputChannel.class, MessageInQuestionOutAnswer.InputChannel.class})
public class MessageInQuestionOutAnswer {
    private UserUseCases userUseCases;
    private MessageChannel outputAnswer;

    public MessageInQuestionOutAnswer(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @Autowired @Qualifier(MessageInQuestionOutAnswer.OutputChannel.OUTPUT_ANSWER)
    public void setOutAnswerChannel(MessageChannel outputAnswer) {
        this.outputAnswer = outputAnswer;
    }

    @StreamListener(MessageInQuestionOutAnswer.InputChannel.QUESTION)
    public void existsUser(String inputMessage) {
        String outputMessage;

        if (userUseCases.userExists(inputMessage)) {
            outputMessage = "yes:"+inputMessage;
        } else {
            outputMessage = "no:"+inputMessage;
        }
        outputAnswer.send(MessageBuilder.withPayload(outputMessage).build());
    }

    public interface OutputChannel {
        public String OUTPUT_ANSWER = "outputAnswer";

        @Output
        MessageChannel outputAnswer();
    }

    public interface InputChannel {
        public String QUESTION = "intputQuestion";

        @Input
        SubscribableChannel intputQuestion();
    }
}
