package io.umss.app.br.broadcast.dao.message.broadcastmessage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.umss.app.br.broadcast.core.message.BroadcastMessage;
import io.umss.app.br.broadcast.dao.RepositoryUtil;
import io.umss.app.br.broadcast.service.Pagination;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * RBroadcastMessageRepository
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Repository
public class RBroadcastMessageRepository {

    @Autowired
    MBroadcastMessageMapper mapper;

    public BroadcastMessage getObjectById(Optional<Long> id) throws RepositoryException {
        return mapper.getRecordById(id.orElse(null));
    }

    public List<BroadcastMessage> getAllObjects(Optional<Integer> status, Optional<Long> categoryId,
            Optional<Long> messageId, Integer pageSize, Integer page) throws RepositoryException {
        int limit = RepositoryUtil.calcLimit(Pagination.MAX_PAGE_SIZE.getCode(), pageSize);
        int offset = RepositoryUtil.calcOffset(limit, page);

        return mapper.getAllRecords(status.orElse(null), categoryId.orElse(null), messageId.orElse(null), limit,
                offset);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<Long> categoryId, Optional<Long> messageId)
            throws RepositoryException {
        return mapper.getCountAllRecords(status.orElse(null), categoryId.orElse(null), messageId.orElse(null));
    }

    public BroadcastMessage save(BroadcastMessage object) throws RepositoryException {
        Long uid = nextVal();
        object.setUid(uid);
        mapper.saveRecord(object);
        return object;
    }

    public BroadcastMessage update(BroadcastMessage object) throws RepositoryException {
        mapper.updateRecord(object);
        return object;
    }

    public void delete(BroadcastMessage object) throws RepositoryException {
        mapper.deleteRecord(object);
    }

    public Long nextVal() throws RepositoryException {
        return mapper.getNextVal();
    }
}