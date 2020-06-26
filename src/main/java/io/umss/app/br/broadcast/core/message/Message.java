package io.umss.app.br.broadcast.core.message;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Message
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class Message {

    private Long uid;

    private Integer status;

    private String title;

    private String body;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    private ClassMessage classMessage;

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

    @Override
    public boolean equals(Object obj) {
        boolean response = false;

        if (this == obj) {
            response = true;
        }

        if (obj instanceof Message) {
            Message other = (Message) obj;

            response = Objects.equals(this.uid, other.uid);
        }

        return response;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}