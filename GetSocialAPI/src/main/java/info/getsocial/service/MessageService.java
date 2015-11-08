package info.getsocial.service;

import org.springframework.stereotype.Service;

import info.getsocial.domain.Message;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
@Service
public class MessageService {
    List<Message> messages = Collections.synchronizedList(new ArrayList<Message>());

    @PostConstruct
    public void init() {
        messages.add(new Message("Joe", "Hello"));
        messages.add(new Message("Jane", "Spring boot is cool !"));
    }

    public List<Message> getMessages() {
        return messages;
    }
}
