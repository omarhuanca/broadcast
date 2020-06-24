package io.umss.app.br.broadcast.dao.message.category;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.umss.app.br.broadcast.core.message.Category;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * MCategoryMapper
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Mapper
public interface MCategoryMapper {

    public Category getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<Category> getAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordName") String recordName, @Param("limit") Integer limit, @Param("offset") Integer offset)
            throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordName") String recordName) throws RepositoryException;

    public Integer saveRecord(@Param("category") Category category) throws RepositoryException;

    public void updateRecord(@Param("category") Category category) throws RepositoryException;

    public void deleteRecord(@Param("category") Category category) throws RepositoryException;

    public Long getNextVal() throws RepositoryException;
}