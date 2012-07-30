package com.tangerinagames.tangauth.model;

import com.avaje.ebean.EbeanServer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class User extends Model {

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public static User create(String userName, String password, Date lastLogin) {
        User user = (User) create(User.class);
        user.setUserName(userName);
        user.setPassword(password);
        user.setLastLogin(lastLogin);
        return user;
    }

    public static User find(long key) {
        return (User) find(User.class, key);
    }
}
