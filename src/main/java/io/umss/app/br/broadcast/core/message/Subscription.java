package io.umss.app.br.broadcast.core.message;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Subscription
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class Subscription {

    private Long uid;

    private Integer status;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    private ClassChannel classChannel;

    private Subscriber subscriber;

    private Category category;

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

    public ClassChannel getClassChannel() {
        return classChannel;
    }

    public void setClassChannel(ClassChannel classChannel) {
        this.classChannel = classChannel;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        boolean response = false;

        if (this == obj) {
            response = true;
        }

        if (obj instanceof Subscription) {
            Subscription other = (Subscription) obj;

            response = Objects.equals(this.uid, other.uid);
        }

        return response;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}