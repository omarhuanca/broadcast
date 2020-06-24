package io.umss.app.br.broadcast.dao.message.classmessage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.umss.app.br.broadcast.core.message.ClassMessage;
import io.umss.app.br.broadcast.dao.RepositoryUtil;
import io.umss.app.br.broadcast.service.Pagination;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * RClassMessageRepository
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Repository
public class RClassMessageRepository {

    @Autowired
    MClassMessageMapper mapper;

    public ClassMessage getObjectById(Optional<Long> id) throws RepositoryException {
        return mapper.getRecordById(id.orElse(null));
    }

    public List<ClassMessage> getAllObjects(Optional<Integer> status, Optional<String> name, Integer pageSize,
            Integer page) throws RepositoryException {
        int limit = RepositoryUtil.calcLimit(Pagination.MAX_PAGE_SIZE.getCode(), pageSize);
        int offset = RepositoryUtil.calcOffset(limit, page);

        return mapper.getAllRecords(status.orElse(null), name.orElse(null), limit, offset);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> name) throws RepositoryException {
        return mapper.getCountAllRecords(status.orElse(null), name.orElse(null));
    }

    public ClassMessage save(ClassMessage object) throws RepositoryException {
        Long uid = nextVal();
        object.setUid(uid);
        mapper.saveRecord(object);
        return object;
    }

    public ClassMessage update(ClassMessage object) throws RepositoryException {
        mapper.updateRecord(object);
        return object;
    }

    public void delete(ClassMessage object) throws RepositoryException {
        mapper.deleteRecord(object);
    }

    public Long nextVal() throws RepositoryException {
        return mapper.getNextVal();
    }
}