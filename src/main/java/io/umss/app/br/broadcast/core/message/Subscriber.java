package io.umss.app.br.broadcast.core.message;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Subscriber
 * 
 * @author Omar Huanca
 * @since 1.0
 *
 */
public class Subscriber {

    public static final String INVALID_STATUS = "Status can not be diff 0 or 1";
    public static final String INVALID_FIRST_NAME = "First name can not be empty";
    public static final String INVALID_LAST_NAME = "Last name can not be empty";
    public final String INVALID_EMAIL = "Email can not be empty";
    public final String INVALID_CELLPHONE = "Cellphone can not be empty";

    private Long uid;

    private Integer status;

    private String firstName;

    private String lastName;

    private String email;

    private String cellphone;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    public static Subscriber at(Integer status, String firstName, String lastName, String email, String cellphone) {
        if (firstName.isEmpty()) throw new RuntimeException(INVALID_FIRST_NAME);
        if (lastName.isEmpty()) throw new RuntimeException(INVALID_LAST_NAME);
        return new Subscriber(status, firstName, lastName, email, cellphone);
    }

    public Subscriber(Integer status, String firstName, String lastName, String email, String cellphone) {
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cellphone = cellphone;
    }

    public Subscriber() {
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

    @Override
    public boolean equals(Object obj) {
        boolean response = false;

        if (this == obj) {
            response = true;
        }

        if (obj instanceof Subscriber) {
            Subscriber other = (Subscriber) obj;

            response = Objects.equals(this.uid, other.uid);
        }

        return response;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}
