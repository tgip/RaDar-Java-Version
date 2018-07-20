package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Doner;

/**
 * An {@link AuthService} implementation
 */
public class AuthServiceImpl implements AuthService {

    private Integer accessingCustomerId;
    private DonerService donerService;

    /**
     * Sets the customer service
     *
     * @param donerService the customer service to set
     */
    public void setDonerService(DonerService donerService) {
        this.donerService = donerService;
    }

    /**
     * @see AuthService#authenticate(Integer)
     */
    @Override
    public boolean authenticate(Integer id) {

        Doner customer = donerService.get(id);

        if (customer == null) {
            return false;
        }

        accessingCustomerId = customer.getId();
        return true;
    }

    /**
     * @see AuthService#getAccessingCustomer()
     */
    @Override
    public Doner getAccessingCustomer() {
        return donerService.get(accessingCustomerId);
    }
}
