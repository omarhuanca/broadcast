package io.umss.app.br.broadcast.dao.message.classmessage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.umss.app.br.broadcast.core.message.ClassMessage;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * MClassMessageMapper
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Mapper
public interface MClassMessageMapper {

    public ClassMessage getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<ClassMessage> getAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordName") String recordName, @Param("limit") Integer limit, @Param("offset") Integer offset)
            throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordName") String recordName) throws RepositoryException;

    public Integer saveRecord(@Param("classMessage") ClassMessage classMessage) throws RepositoryException;

    public void updateRecord(@Param("classMessage") ClassMessage classMessage) throws RepositoryException;

    public void deleteRecord(@Param("classMessage") ClassMessage classMessage) throws RepositoryException;

    public Long getNextVal() throws RepositoryException;
}