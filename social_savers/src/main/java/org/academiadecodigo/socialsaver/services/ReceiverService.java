package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.dao.CustomerDao;
import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * An {@link DonerService} implementation
 */
public class ReceiverService implements DonerService {

    private List<Receiver> receivers= new LinkedList<>();

    public Doner get(Integer id) {
        return customerDao.findById(id);
    }


    public double getBalance(Integer id) {

        Doner customer = customerDao.findById(id);

        if (customer == null) {
            throw new IllegalArgumentException("Doner does not exists");
        }

        List<Account> accounts = customer.getAccounts();

        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }

        return balance;
    }

    /**
     * @see DonerService#listCustomerAccountIds(Integer)
     */
    public Set<Integer> listCustomerAccountIds(Integer id) {

        Doner customer = customerDao.findById(id);

        if (customer == null) {
            throw new IllegalArgumentException("Doner does not exist");
        }

        Set<Integer> accountIds = new HashSet<>();
        List<Account> accounts = customer.getAccounts();

        for (Account account : accounts) {
            accountIds.add(account.getId());
        }

        return accountIds;
    }

    /**
     * @see DonerService#listRecipients(Integer)
     */
    @Transactional(readOnly = true)
    public List<Receiver> listRecipients(Integer id) {

        Doner customer = customerDao.findById(id);

        if (customer == null) {
            throw new IllegalArgumentException("Doner does not exists");
        }

        return new ArrayList<>(customerDao.findById(id).getReceivers());
    }
}
