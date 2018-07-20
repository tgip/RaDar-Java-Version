package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.dao.CustomerDao;
import org.academiadecodigo.socialsaver.persistence.model.Doner;
import org.academiadecodigo.socialsaver.persistence.model.Ipss;
import org.academiadecodigo.socialsaver.persistence.model.account.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An {@link DonerService} implementation
 */
public class IpssServiceImpl implements DonerService {

    private CustomerDao customerDao;

    /**
     * Sets the customer data access object
     *
     * @param customerDao the account DAO to set
     */
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * @see DonerService#get(Integer)
     */
    @Override
    public Doner get(Integer id) {
        return customerDao.findById(id);
    }

    /**
     * @see DonerService#getBalance(Integer)
     */
    @Override
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
    @Override
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
    @Override
    public List<Ipss> listRecipients(Integer id) {

        Doner customer = customerDao.findById(id);

        if (customer == null) {
            throw new IllegalArgumentException("Doner does not exists");
        }

        return new ArrayList<>(customerDao.findById(id).getIpsses());
    }
}
