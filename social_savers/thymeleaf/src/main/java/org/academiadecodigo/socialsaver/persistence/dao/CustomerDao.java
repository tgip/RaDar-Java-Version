package org.academiadecodigo.socialsaver.persistence.dao;

import org.academiadecodigo.socialsaver.persistence.model.Doner;

import java.util.List;

/**
 * Common interface for customer data access objects
 */
public interface CustomerDao extends Dao<Doner> {

    /**
     * Gets a list of customer ids
     *
     * @return the list of customer ids
     */
    List<Integer> getCustomerIds();
}
