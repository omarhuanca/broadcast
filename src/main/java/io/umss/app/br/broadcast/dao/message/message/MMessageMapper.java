package io.umss.app.br.broadcast.dao.message.message;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.umss.app.br.broadcast.core.message.Message;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * MMessageMapper
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Mapper
public interface MMessageMapper {

    public Message getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<Message> getAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordTitle") String recordTitle, @Param("recordBody") String recordBody,
            @Param("classMessageId") Long classMessageId, @Param("limit") Integer limit,
            @Param("offset") Integer offset) throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordTitle") String recordTitle, @Param("recordBody") String recordBody,
            @Param("classMessageId") Long classMessageId) throws RepositoryException;

    public Integer saveRecord(@Param("message") Message message) throws RepositoryException;

    public void updateRecord(@Param("message") Message message) throws RepositoryException;

    public void deleteRecord(@Param("message") Message message) throws RepositoryException;

    public Long getNextVal() throws RepositoryException;
}