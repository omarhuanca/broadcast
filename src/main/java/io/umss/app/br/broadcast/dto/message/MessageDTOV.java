package io.umss.app.br.broadcast.dto.message;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.umss.app.br.broadcast.core.message.ClassMessage;
import io.umss.app.br.broadcast.core.message.Message;

/**
 * MessageDTOV
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class MessageDTOV {

    @Min(value = 0, message = "The id cannot be less than 0.")
    private Long uid;

    @Min(value = 0, message = "The state cannot be less than 0 o greather than 1.")
    @Max(1)
    private Integer status;

    @NotBlank(message = "The title is not valid.")
    @Size(max = 80, message = "The title should not be greather than 80 characters.")
    private String title;

    @NotBlank(message = "The body is not valid.")
    @Size(max = 160, message = "The body should not be greather than 160 characters.")
    private String body;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    @NotNull(message = "The class message is not valid.")
    private ClassMessage classMessage;

    public void copyCoreObject(Message object) {
        object.setStatus(status);
        object.setTitle(title);
        object.setBody(body);
        object.setCreateDate(createDate);
        object.setLastUpdateDate(lastUpdateDate);
        object.setClassMessage(classMessage);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public ClassMessage getClassMessage() {
        return classMessage;
    }

    public void setClassMessage(ClassMessage classMessage) {
        this.classMessage = classMessage;
    }
}