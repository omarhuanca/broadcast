package io.umss.app.br.broadcast.dao.message.classchannel;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.umss.app.br.broadcast.core.message.ClassChannel;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * MClassChannelMapper
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Mapper
public interface MClassChannelMapper {

    public ClassChannel getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<ClassChannel> getAllRecords(@Param("recordStatus") Integer recordStatus, @Param("recordName") String recordName,
            @Param("limit") Integer limit, @Param("offset") Integer offset) throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus, @Param("recordName") String recordName)
            throws RepositoryException;

    public Integer saveRecord(@Param("classChannel") ClassChannel classChannel) throws RepositoryException;

    public void updateRecord(@Param("classChannel") ClassChannel classChannel) throws RepositoryException;

    public void deleteRecord(@Param("classChannel") ClassChannel classChannel) throws RepositoryException;

    public Long getNextVal() throws RepositoryException;
}
