package io.umss.app.br.broadcast.dto.message;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.umss.app.br.broadcast.core.message.Attach;
import io.umss.app.br.broadcast.core.message.Message;

/**
 * AttachDTOV
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class AttachDTOV {

    @Min(value = 0, message = "The id cannot be less than 0.")
    private Long uid;

    @Min(value = 0, message = "The state cannot be less than 0 o greather than 1.")
    @Max(1)
    private Integer status;

    @NotBlank(message = "The url file is not valid.")
    @Size(max = 255, message = "The url file not be greather than 255 characters.")
    private String urlFile;

    @NotBlank(message = "The name file is not valid.")
    @Size(max = 255, message = "The name file should not be greather than 255 characters.")
    private String nameFile;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    @NotNull(message = "The message is not valid.")
    private Message message;

    public void copyCoreObject(Attach object) {
        object.setStatus(status);
        object.setUrlFile(urlFile);
        object.setNameFile(nameFile);
        object.setCreateDate(createDate);
        object.setLastUpdateDate(lastUpdateDate);
        object.setMessage(message);
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

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
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

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}