package io.umss.app.br.broadcast.service.message;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.umss.app.br.broadcast.core.message.Attach;
import io.umss.app.br.broadcast.core.message.Message;
import io.umss.app.br.broadcast.dao.message.attach.RAttachRepository;
import io.umss.app.br.broadcast.dao.message.message.RMessageRepository;
import io.umss.app.br.broadcast.service.ClassStatus;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * AttachService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
public class AttachService {

    @Autowired
    RAttachRepository repository;

    @Autowired
    RMessageRepository messageRepository;

    public Attach getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<Attach> getAllObjects(Optional<Integer> status, Optional<String> urlFile, Optional<String> nameFile,
            Optional<Long> messageId, Integer pageSize, Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, urlFile, nameFile, messageId, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> urlFile, Optional<String> nameFile,
            Optional<Long> messageId) throws RepositoryException {
        return repository.getCountAllObjects(status, urlFile, nameFile, messageId);
    }

    public Attach save(Attach object) throws RepositoryException {
        this.verifyForeingKey(object);

        return repository.save(object);
    }

    public Attach update(Attach object) throws RepositoryException {
        this.verifyForeingKey(object);

        return repository.update(object);
    }

    private void verifyForeingKey(Attach object) {
        Message message = messageRepository.getObjectById(Optional.ofNullable(object.getMessage().getUid()));
        if (null == message || ClassStatus.DISABLE.getCode().equals(message.getStatus())) {
            throw new InvalidParameterException("The message id does not exist or status is disable.");
        }
    }

    public void delete(Attach object) throws RepositoryException {
        repository.delete(object);
    }
}