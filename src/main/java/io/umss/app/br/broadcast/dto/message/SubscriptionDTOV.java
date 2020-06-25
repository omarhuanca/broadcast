package io.umss.app.br.broadcast.dto.message;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.umss.app.br.broadcast.core.message.Category;
import io.umss.app.br.broadcast.core.message.ClassChannel;
import io.umss.app.br.broadcast.core.message.Subscriber;
import io.umss.app.br.broadcast.core.message.Subscription;

/**
 * SubscriptionDTOV
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class SubscriptionDTOV {

    @Min(value = 0, message = "The id cannot be less than 0.")
    private Long uid;

    @Min(value = 0, message = "The state cannot be less than 0 o greather than 1.")
    @Max(1)
    private Integer status;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    @NotNull(message = "The class channel is not valid.")
    private ClassChannel classChannel;

    @NotNull(message = "The subscriber is not valid.")
    private Subscriber subscriber;

    @NotNull(message = "The category is not valid.")
    private Category category;

    public void copyCoreObject(Subscription object) {
        object.setStatus(status);
        object.setCreateDate(createDate);
        object.setLastUpdateDate(lastUpdateDate);
        object.setClassChannel(classChannel);
        object.setSubscriber(subscriber);
        object.setCategory(category);
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
}