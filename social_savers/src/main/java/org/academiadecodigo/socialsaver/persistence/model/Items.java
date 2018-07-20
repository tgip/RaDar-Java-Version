package org.academiadecodigo.socialsaver.persistence.model;

import org.academiadecodigo.socialsaver.persistence.model.Entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Doners")
public class Items implements Model {

    private int id;
    private String type;
    private int receiverId;
    private boolean donated;

    public boolean isDonated() {
        return donated;
    }

    public void setDonated(boolean donated) {
        this.donated = donated;
    }

    @ManyToOne
    private AbstractEntity entity;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }
}
