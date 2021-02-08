package io.umss.app.br.broadcast.service.message;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.umss.app.br.broadcast.core.message.ClassMessage;
import io.umss.app.br.broadcast.core.message.Message;
import io.umss.app.br.broadcast.dao.message.classmessage.RClassMessageRepository;
import io.umss.app.br.broadcast.dao.message.message.RMessageRepository;
import io.umss.app.br.broadcast.service.ClassStatus;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * MessageService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
public class MessageService {

    @Autowired
    RMessageRepository repository;

    @Autowired
    RClassMessageRepository classMessageRepository;

    public Message getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<Message> getAllObjects(Optional<Integer> status, Optional<String> title, Optional<String> body,
            Optional<Long> classMessageId, Integer pageSize, Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, title, body, classMessageId, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> title, Optional<String> body,
            Optional<Long> classMessageId) throws RepositoryException {
        return repository.getCountAllObjects(status, title, body, classMessageId);
    }

    public Message save(Message object) throws RepositoryException {
        this.verifyForeignKey(object);

        return repository.save(object);
    }

    public Message update(Message object) throws RepositoryException {
        this.verifyForeignKey(object);

        return repository.update(object);
    }

    private void verifyForeignKey(Message object) {
        ClassMessage classMessage = classMessageRepository
                .getObjectById(Optional.ofNullable(object.getClassMessage().getUid()));
        if (null == classMessage || ClassStatus.DISABLE.getCode().equals(classMessage.getStatus())) {
            throw new InvalidParameterException("The class message id does not exist or status is disable.");
        }
    }

    public void delete(Message object) throws RepositoryException {
        repository.delete(object);
    }
}