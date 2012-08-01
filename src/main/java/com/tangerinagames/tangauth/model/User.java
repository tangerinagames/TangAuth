package com.tangerinagames.tangauth.model;

import com.avaje.ebean.EbeanServer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class User {

    protected static EbeanServer database;

    @Id
    private Integer id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void save() {
        database.save(this);
    }

    public static User create(String userName, String password, Date lastLogin) {
        User user = (User) database.createEntityBean(User.class);
        user.setUserName(userName);
        user.setPassword(password);
        user.setLastLogin(lastLogin);
        return user;
    }

    public static User findByUserName(String userName) {
        return database.find(User.class).where().ieq("userName", userName).findUnique();
    }

    public static boolean exists(String userName) {
        return findByUserName(userName) != null;
    }

    public static void setDatabase(EbeanServer database) {
        User.database = database;
    }

}

