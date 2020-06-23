package io.umss.app.br.broadcast.dto.message;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.umss.app.br.broadcast.core.message.Subscriber;
import io.umss.app.br.broadcast.dto.SubscriberCellphone;

/**
 * SubscriberDTOV
 * 
 * @author Omar Huanca
 * @since 1.0
 */
public class SubscriberDTOV {

    @Min(value = 0, message = "The id cannot be less than 0.")
    private Long uid;

    @Min(value = 0, message = "The state cannot be less than 0 o greather than 1.")
    @Max(1)
    private Integer status;

    @NotBlank(message = "The firstName is not valid.")
    @Size(max = 80, message = "The firstName should not be greather than 80 characters.")
    private String firstName;

    @NotBlank(message = "The lastName is not valid.")
    @Size(max = 80, message = "The lastName should not be greather than 80 characters.")
    private String lastName;

    @NotBlank(message = "The email status is not valid.")
    @Size(max = 100, message = "The email should not be greather than 100 characters.")
    private String email;

    @NotBlank(message = "The cellphone status is not valid.")
    @Size(max = 15, message = "The cellphone should not be greather than 15 characters.")
    @SubscriberCellphone
    private String cellphone;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    public void copyCoreObject(Subscriber object) {
        object.setStatus(status);
        object.setFirstName(firstName);
        object.setLastName(lastName);
        object.setEmail(email);
        object.setCellphone(cellphone);
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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
