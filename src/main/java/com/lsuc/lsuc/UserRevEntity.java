package com.lsuc.lsuc;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by tribhuvand on 21/4/17.
 */
@Entity
@Table(name = "USERNAME_REV")
@RevisionEntity(UserRevisionListener.class)
public class UserRevEntity extends DefaultRevisionEntity {
    private String username;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
