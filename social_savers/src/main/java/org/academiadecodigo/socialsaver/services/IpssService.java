package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;

import java.util.List;
import java.util.Set;

/**
 * Common interface for customer services, provides methods to manage customers
 */
public interface IpssService {

    /**
     * Gets the customer with the given id
     *
     * @param id the customer id
     * @return the customer
     */
    Doner get(Integer id);

    /**
     * Gets the balance of the customer
     *
     * @param id the customer id
     * @return the balance of the customer with the given id
     */
    double getBalance(Integer id);

    /**
     * Gets the set of customer account ids
     *
     * @param id the customer id
     * @return the accounts of the given customer id
     */
    Set<Integer> listCustomerAccountIds(Integer id);

    /**
     * Gets the list of customer recipients
     *
     * @param id the customer id
     * @return the list of recipients of the customer
     */
    List<Receiver> listRecipients(Integer id);
}
