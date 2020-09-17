package io.umss.app.br.broadcast.service.message;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.umss.app.br.broadcast.core.message.Category;
import io.umss.app.br.broadcast.dao.message.category.RCategoryRepository;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * CategoryService
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Service
public class CategoryService {

    @Autowired
    RCategoryRepository repository;

    public Category getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<Category> getAllObjects(Optional<Integer> status, Optional<String> name, Integer pageSize,
            Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, name, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> name) throws RepositoryException {
        return repository.getCountAllObjects(status, name);
    }

    public Category save(Category object) throws RepositoryException {
        return repository.save(object);
    }

    public Category update(Category object) throws RepositoryException {
        return repository.update(object);
    }

    public void delete(Category object) throws RepositoryException {
        repository.delete(object);
    }
}