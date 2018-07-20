package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;

/**
 * Common interface for authentication services, provides method for customer authentication
 */
public interface AuthService {


    boolean authenticate(Integer id);


    Doner getAccessingCustomer();
}
