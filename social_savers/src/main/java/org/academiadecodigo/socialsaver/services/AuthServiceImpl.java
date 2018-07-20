package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;

/**
 * An {@link AuthService} implementation
 */
public class AuthServiceImpl implements AuthService {

    private Integer accessingEntityId;
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

        Doner doner = donerService.get(id);

        if (doner == null) {
            return false;
        }

        accessingEntityId = doner.getId();
        return true;
    }

    /**
     * @see AuthService#getAccessingCustomer()
     */
    @Override
    public Doner getAccessingCustomer() {
        return donerService.get(accessingEntityId);
    }
}
