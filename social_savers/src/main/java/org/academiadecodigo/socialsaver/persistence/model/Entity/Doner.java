package org.academiadecodigo.socialsaver.persistence.model.Entity;



import org.academiadecodigo.socialsaver.persistence.model.Items;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The customer model entity
 */
public class Doner extends AbstractEntity {

    private String type= "doner";

    //todo add feature list items to donate
    private List<Items> toDonateItems= new LinkedList<>();


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setToDonateItems(Items items){
        toDonateItems.add(items);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Doner{" +
                "type='" + type + '\'' +
                "} " + super.toString();
    }
}



