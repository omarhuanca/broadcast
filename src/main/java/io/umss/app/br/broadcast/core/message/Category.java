package io.umss.app.br.broadcast.core.message;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Category
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class Category {

    private Long uid;

    private Integer status;

    private String name;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    private List<Subscription> listSubscription;

    private List<BroadcastMessage> listBroadcastMessage;

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

    public List<Subscription> getListSubscription() {
        return listSubscription;
    }

    public void setListSubscription(List<Subscription> listSubscription) {
        this.listSubscription = listSubscription;
    }

    public List<BroadcastMessage> getListBroadcastMessage() {
        return listBroadcastMessage;
    }

    public void setListBroadcastMessage(List<BroadcastMessage> listBroadcastMessage) {
        this.listBroadcastMessage = listBroadcastMessage;
    }

    @Override
    public boolean equals(Object obj) {
        boolean response = false;

        if (this == obj) {
            response = true;
        }

        if (obj instanceof Category) {
            Category other = (Category) obj;

            response = Objects.equals(this.uid, other.uid);
        }

        return response;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}