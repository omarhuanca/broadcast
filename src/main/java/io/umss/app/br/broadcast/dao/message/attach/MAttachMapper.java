package io.umss.app.br.broadcast.dao.message.attach;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.umss.app.br.broadcast.core.message.Attach;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * MAttachMapper
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Mapper
public interface MAttachMapper {

    public Attach getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<Attach> getAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordUrlFile") String recordUrlFile, @Param("recordNameFile") String recordNameFile,
            @Param("messageId") Long messageId, @Param("limit") Integer limit, @Param("offset") Integer offset)
            throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordUrlFile") String recordUrlFile, @Param("recordNameFile") String recordNameFile,
            @Param("messageId") Long messageId) throws RepositoryException;

    public Integer saveRecord(@Param("attach") Attach attach) throws RepositoryException;

    public void updateRecord(@Param("attach") Attach attach) throws RepositoryException;

    public void deleteRecord(@Param("attach") Attach attach) throws RepositoryException;

    public Long getNextVal() throws RepositoryException;
}