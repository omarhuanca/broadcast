package io.umss.app.br.broadcast.dto.message;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.umss.app.br.broadcast.core.message.ClassMessage;

/**
 * ClassMessageDTOV
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class ClassMessageDTOV {

    @Min(value = 0, message = "The id cannot be less than 0.")
    private Long uid;

    @Min(value = 0, message = "The state cannot be less than 0 o greather than 1.")
    @Max(1)
    private Integer status;

    @NotBlank(message = "The name is not valid.")
    @Size(max = 80, message = "The name should not be greather than 80 characters.")
    private String name;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    public void copyCoreObject(ClassMessage object) {
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