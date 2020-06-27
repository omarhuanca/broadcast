package io.umss.app.br.broadcast.dao.message.broadcastmessage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.umss.app.br.broadcast.core.message.BroadcastMessage;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * MBroadcastMessage
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Mapper
public interface MBroadcastMessageMapper {

    public BroadcastMessage getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<BroadcastMessage> getAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("categoryId") Long categoryId, @Param("messageId") Long messageId, @Param("limit") Integer limit,
            @Param("offset") Integer offset) throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus, @Param("categoryId") Long categoryId,
            @Param("messageId") Long messageId) throws RepositoryException;

    public Integer saveRecord(@Param("broadcastMessage") BroadcastMessage broadcastMessage) throws RepositoryException;

    public void updateRecord(@Param("broadcastMessage") BroadcastMessage broadcastMessage) throws RepositoryException;

    public void deleteRecord(@Param("broadcastMessage") BroadcastMessage broadcastMessage) throws RepositoryException;

    public Long getNextVal() throws RepositoryException;
}