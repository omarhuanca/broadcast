package io.umss.app.br.broadcast.service.message;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.umss.app.br.broadcast.core.message.Subscriber;
import io.umss.app.br.broadcast.dao.message.subscriber.RSubscriberRepository;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * SubscriberService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
public class SubscriberService {

    @Autowired
    RSubscriberRepository repository;

    public Subscriber getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<Subscriber> getAllObjects(Optional<Integer> status, Optional<String> firstName,
            Optional<String> lastName, Optional<String> email, Optional<String> cellphone, Integer pageSize,
            Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, firstName, lastName, email, cellphone, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> firstName, Optional<String> lastName,
            Optional<String> email, Optional<String> cellphone) throws RepositoryException {
        return repository.getCountAllObjects(status, firstName, lastName, email, cellphone);
    }

    public Subscriber save(Subscriber object) throws RepositoryException {
        return repository.save(object);
    }

    public Subscriber update(Subscriber object) throws RepositoryException {
        return repository.update(object);
    }

    public void delete(Subscriber object) throws RepositoryException {
        repository.delete(object);
    }
}
