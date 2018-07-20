package org.academiadecodigo.socialsaver.persistence.model.Entity;

import org.academiadecodigo.socialsaver.persistence.model.Entity.AbstractEntity;
import org.academiadecodigo.socialsaver.persistence.model.Items;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The recipient model entity
 */

public class Receiver extends AbstractEntity {

    private List<Items> neededItems= new LinkedList<>();

    private String type= "receiver";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}
