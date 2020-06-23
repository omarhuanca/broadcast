package io.umss.app.br.broadcast.dao.message.subscriber;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.umss.app.br.broadcast.core.message.Subscriber;
import io.umss.app.br.broadcast.dao.RepositoryUtil;
import io.umss.app.br.broadcast.service.Pagination;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * RSubscriberRepository
 * 
 * @author Omar Huanca
 * @since 1.0 
 */
@Repository
public class RSubscriberRepository {

    @Autowired
    MSubscriberMapper mapper;

    public Subscriber getObjectById(Optional<Long> id) throws RepositoryException {
        return mapper.getRecordById(id.orElse(null));
    }

    public List<Subscriber> getAllObjects(Optional<Integer> status, Optional<String> firstName,
            Optional<String> lastName, Optional<String> email, Optional<String> cellphone, Integer pageSize,
            Integer page) throws RepositoryException {
        int limit = RepositoryUtil.calcLimit(Pagination.MAX_PAGE_SIZE.getCode(), pageSize);
        int offset = RepositoryUtil.calcOffset(limit, page);

        return mapper.getAllRecords(status.orElse(null), firstName.orElse(null), lastName.orElse(null),
                email.orElse(null), cellphone.orElse(null), limit, offset);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> firstName, Optional<String> lastName,
            Optional<String> email, Optional<String> cellphone) throws RepositoryException {
        return mapper.getCountAllRecords(status.orElse(null), firstName.orElse(null), lastName.orElse(null),
                email.orElse(null), cellphone.orElse(null));
    }

    public Subscriber save(Subscriber object) throws RepositoryException {
        Long uid = nextVal();
        object.setUid(uid);
        mapper.saveRecord(object);
        return object;
    }

    public Subscriber update(Subscriber object) throws RepositoryException {
        mapper.updateRecord(object);
        return object;
    }

    public void delete(Subscriber object) throws RepositoryException {
        mapper.deleteRecord(object);
    }

    public Long nextVal() throws RepositoryException {
        return mapper.getNextVal();
    }
}
