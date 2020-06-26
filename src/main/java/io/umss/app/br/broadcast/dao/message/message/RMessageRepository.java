package io.umss.app.br.broadcast.dao.message.message;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.umss.app.br.broadcast.core.message.Message;
import io.umss.app.br.broadcast.dao.RepositoryUtil;
import io.umss.app.br.broadcast.service.Pagination;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * RMessageRepository
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Repository
public class RMessageRepository {

    @Autowired
    MMessageMapper mapper;

    public Message getObjectById(Optional<Long> id) throws RepositoryException {
        return mapper.getRecordById(id.orElse(null));
    }

    public List<Message> getAllObjects(Optional<Integer> status, Optional<String> title, Optional<String> body,
            Optional<Long> classMessageId, Integer pageSize, Integer page) throws RepositoryException {
        int limit = RepositoryUtil.calcLimit(Pagination.MAX_PAGE_SIZE.getCode(), pageSize);
        int offset = RepositoryUtil.calcOffset(limit, page);

        return mapper.getAllRecords(status.orElse(null), title.orElse(null), body.orElse(null),
                classMessageId.orElse(null), limit, offset);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> title, Optional<String> body,
            Optional<Long> classMessageId) throws RepositoryException {
        return mapper.getCountAllRecords(status.orElse(null), title.orElse(null), body.orElse(null),
                classMessageId.orElse(null));
    }

    public Message save(Message object) throws RepositoryException {
        Long uid = nextVal();
        object.setUid(uid);
        mapper.saveRecord(object);
        return object;
    }

    public Message update(Message object) throws RepositoryException {
        mapper.updateRecord(object);
        return object;
    }

    public void delete(Message object) throws RepositoryException {
        mapper.deleteRecord(object);
    }

    public Long nextVal() throws RepositoryException {
        return mapper.getNextVal();
    }
}