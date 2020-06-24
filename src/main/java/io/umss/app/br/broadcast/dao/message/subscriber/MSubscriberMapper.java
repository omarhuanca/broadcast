package io.umss.app.br.broadcast.dao.message.subscriber;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.umss.app.br.broadcast.core.message.Subscriber;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * MSubscriberMapper
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Mapper
public interface MSubscriberMapper {

    public Subscriber getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<Subscriber> getAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordFirstName") String recordFirstName, @Param("recordLastName") String recordLastName,
            @Param("recordEmail") String recordEmail, @Param("recordCellphone") String recordCellphone,
            @Param("limit") Integer limit, @Param("offset") Integer offset) throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordFirstName") String recordFirstName, @Param("recordLastName") String recordLastName,
            @Param("recordEmail") String recordEmail, @Param("recordCellphone") String recordCellphone)
            throws RepositoryException;

    public Integer saveRecord(@Param("subscriber") Subscriber subscriber) throws RepositoryException;

    public void updateRecord(@Param("subscriber") Subscriber subscriber) throws RepositoryException;

    public void deleteRecord(@Param("subscriber") Subscriber subscriber) throws RepositoryException;

    public Long getNextVal() throws RepositoryException;
}
