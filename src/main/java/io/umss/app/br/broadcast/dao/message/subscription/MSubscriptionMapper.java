package io.umss.app.br.broadcast.dao.message.subscription;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.umss.app.br.broadcast.core.message.Subscription;
import io.umss.app.br.broadcast.util.exception.RepositoryException;

/**
 * MSubscriptionMapper
 * 
 * @author Omar Huanca
 * @since 1.0
 */
@Mapper
public interface MSubscriptionMapper {

    public Subscription getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<Subscription> getAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("classChannelId") Long classChannelId, @Param("subscriberId") Long subscriberId,
            @Param("categoryId") Long categoryId, @Param("limit") Integer limit, @Param("offset") Integer offset)
            throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("classChannelId") Long classChannelId, @Param("subscriberId") Long subscriberId,
            @Param("categoryId") Long categoryId) throws RepositoryException;

    public Integer saveRecord(@Param("subscription") Subscription subscription) throws RepositoryException;

    public void updateRecord(@Param("subscription") Subscription subscription) throws RepositoryException;

    public void deleteRecord(@Param("subscription") Subscription subscription) throws RepositoryException;

    public Long getNextVal() throws RepositoryException;
}