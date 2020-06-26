package io.umss.app.br.broadcast.core.message;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Attach
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class Attach {

    private Long uid;

    private Integer status;

    private String urlFile;

    private String nameFile;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    private Message message;

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

    @Override
    public boolean equals(Object obj) {
        boolean response = false;

        if (this == obj) {
            response = true;
        }

        if (obj instanceof Attach) {
            Attach other = (Attach) obj;

            response = Objects.equals(this.uid, other.uid);
        }

        return response;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}