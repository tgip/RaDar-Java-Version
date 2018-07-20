package org.academiadecodigo.socialsaver.persistence.model.Entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The customer model entity
 */
@javax.persistence.Entity
@Table(name = "Doners")
public class Doner extends AbstractEntity {

    private String type= "doner";

    @OneToMany(

            cascade = {CascadeType.ALL},

            // make sure to remove accounts if unlinked from customer
            orphanRemoval = true,

            // user customer foreign key on account table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "Items",

            // fetch accounts from database together with user
            fetch = FetchType.EAGER
    )

    private List<Doner> doners = new ArrayList<>();

    /**
     * Gets the customer accounts
     *
     * @return the accounts
     */
    public List<Doner> getAccounts() {
        return doners;
    }


    public void addDoner(Doner doner){
        doners.add(doner);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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



