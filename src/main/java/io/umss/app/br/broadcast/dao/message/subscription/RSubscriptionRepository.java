package io.umss.app.br.broadcast.dao.message.subscription;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.umss.app.br.broadcast.core.message.Subscription;
import io.umss.app.br.broadcast.dao.RepositoryUtil;
import io.umss.app.br.broadcast.service.Pagination;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * RSubscriptionRepository
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Repository
public class RSubscriptionRepository {

    @Autowired
    MSubscriptionMapper mapper;

    public Subscription getObjectById(Optional<Long> id) throws RepositoryException {
        return mapper.getRecordById(id.orElse(null));
    }

    public List<Subscription> getAllObjects(Optional<Integer> status, Optional<Long> classChannelId,
            Optional<Long> subscriberId, Optional<Long> categoryId, Integer pageSize, Integer page)
            throws RepositoryException {
        int limit = RepositoryUtil.calcLimit(Pagination.MAX_PAGE_SIZE.getCode(), pageSize);
        int offset = RepositoryUtil.calcOffset(limit, page);

        return mapper.getAllRecords(status.orElse(null), classChannelId.orElse(null), subscriberId.orElse(null),
                categoryId.orElse(null), limit, offset);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<Long> classChannelId,
            Optional<Long> subscriberId, Optional<Long> categoryId) throws RepositoryException {
        return mapper.getCountAllRecords(status.orElse(null), classChannelId.orElse(null), subscriberId.orElse(null),
                categoryId.orElse(null));
    }

    public Subscription save(Subscription object) throws RepositoryException {
        Long uid = nextVal();
        object.setUid(uid);
        mapper.saveRecord(object);
        return object;
    }

    public Subscription update(Subscription object) throws RepositoryException {
        mapper.updateRecord(object);
        return object;
    }

    public void delete(Subscription object) throws RepositoryException {
        mapper.deleteRecord(object);
    }

    public Long nextVal() throws RepositoryException {
        return mapper.getNextVal();
    }
}