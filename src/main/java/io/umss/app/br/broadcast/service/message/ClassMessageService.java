package io.umss.app.br.broadcast.service.message;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.umss.app.br.broadcast.core.message.ClassMessage;
import io.umss.app.br.broadcast.dao.message.classmessage.RClassMessageRepository;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * ClassMessageService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
public class ClassMessageService {

    @Autowired
    RClassMessageRepository repository;

    public ClassMessage getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<ClassMessage> getAllObjects(Optional<Integer> status, Optional<String> name, Integer pageSize,
            Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, name, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> name) throws RepositoryException {
        return repository.getCountAllObjects(status, name);
    }

    public ClassMessage save(ClassMessage object) throws RepositoryException {
        return repository.save(object);
    }

    public ClassMessage update(ClassMessage object) throws RepositoryException {
        return repository.update(object);
    }

    public void delete(ClassMessage object) throws RepositoryException {
        repository.delete(object);
    }
}