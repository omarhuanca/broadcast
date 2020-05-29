package io.umss.app.br.broadcast.dto.message;

import java.sql.Timestamp;

import io.umss.app.br.broadcast.core.message.ClassChannel;

/**
 * ClassChannelDTOV
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class ClassChannelDTOV {

    private Long uid;

    private Integer status;

    private String name;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    public void copyCoreObject(ClassChannel object) {
        object.setStatus(status);
        object.setName(name);
        object.setCreateDate(createDate);
        object.setLastUpdateDate(lastUpdateDate);
    }

    // Getters & Setters
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}