package io.umss.app.br.broadcast.service.message;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.umss.app.br.broadcast.core.message.BroadcastMessage;
import io.umss.app.br.broadcast.core.message.Category;
import io.umss.app.br.broadcast.core.message.Message;
import io.umss.app.br.broadcast.dao.message.broadcastmessage.RBroadcastMessageRepository;
import io.umss.app.br.broadcast.dao.message.category.RCategoryRepository;
import io.umss.app.br.broadcast.dao.message.message.RMessageRepository;
import io.umss.app.br.broadcast.service.ClassStatus;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * BroadcastMessageService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
public class BroadcastMessageService {

    @Autowired
    RBroadcastMessageRepository repository;

    @Autowired
    RCategoryRepository categoryRepository;

    @Autowired
    RMessageRepository messageRepository;

    public BroadcastMessage getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<BroadcastMessage> getAllObjects(Optional<Integer> status, Optional<Long> categoryId,
            Optional<Long> messageId, Integer pageSize, Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, categoryId, messageId, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<Long> categoryId, Optional<Long> messageId)
            throws RepositoryException {
        return repository.getCountAllObjects(status, categoryId, messageId);
    }

    public BroadcastMessage save(BroadcastMessage object) throws RepositoryException {
        this.verifyForeingKey(object);

        return repository.save(object);
    }

    public BroadcastMessage update(BroadcastMessage object) throws RepositoryException {
        this.verifyForeingKey(object);

        return repository.update(object);
    }

    private void verifyForeingKey(BroadcastMessage object) {
        Category category = categoryRepository.getObjectById(Optional.ofNullable(object.getCategory().getUid()));
        if (null == category || ClassStatus.DISABLE.getCode().equals(category.getStatus())) {
            throw new InvalidParameterException("The category id does not exist or status is disable.");
        }

        Message message = messageRepository.getObjectById(Optional.ofNullable(object.getMessage().getUid()));
        if (null == message || ClassStatus.DISABLE.getCode().equals(message.getStatus())) {
            throw new InvalidParameterException("The message id does not exist or status is disable.");
        }
    }

    public void delete(BroadcastMessage object) throws RepositoryException {
        repository.delete(object);
    }
}