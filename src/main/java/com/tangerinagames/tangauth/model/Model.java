package com.tangerinagames.tangauth.model;

import com.avaje.ebean.EbeanServer;

import javax.persistence.Id;

public abstract class Model {

    protected static EbeanServer database;

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static void setDatabase(EbeanServer database) {
        Model.database = database;
    }

    protected static Object create(Class<? extends Model> clazz) {
        return database.createEntityBean(clazz);
    }

    protected static Object find(Class<? extends Model> clazz, long key) {
        return database.find(clazz, key);
    }

    public void save() {
        database.save(this);
    }
}


