package cat.tecnocampus.messageSink;

import cat.tecnocampus.domainController.NoteUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

import static org.springframework.cloud.stream.messaging.Sink.INPUT;

@EnableBinding(Sink.class)
public class MessageSink {
    private NoteUseCases noteUseCases;

    @Autowired
    public MessageSink(NoteUseCases noteUseCases) {
        this.noteUseCases = noteUseCases;
    }


    @ServiceActivator(inputChannel= Sink.INPUT)
    public void deleteNotesSink(Object payload) {
        noteUseCases.deleteUserNotes((String) payload);
    }
}
