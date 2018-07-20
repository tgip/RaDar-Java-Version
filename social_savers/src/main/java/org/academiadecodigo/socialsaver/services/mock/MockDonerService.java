package org.academiadecodigo.socialsaver.services.mock;

import org.academiadecodigo.socialsaver.persistence.model.Doner;
import org.academiadecodigo.socialsaver.persistence.model.Ipss;
import org.academiadecodigo.socialsaver.persistence.model.account.Account;
import org.academiadecodigo.socialsaver.services.DonerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A mock {@link DonerService} implementation
 */
public class MockDonerService extends AbstractMockService<Doner> implements DonerService {

    /**
     * @see DonerService#get(Integer)
     */
    @Override
    public Doner get(Integer id) {
        return modelMap.get(id);
    }

    /**
     * @see DonerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer customerId) {

        List<Account> accounts = modelMap.get(customerId).getAccounts();

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

        Set<Integer> accountIds = new HashSet<>();
        List<Account> accounts = modelMap.get(id).getAccounts();

        for (Account account : accounts) {
            accountIds.add(account.getId());
        }

        return accountIds;
    }

    /**
     * @see DonerService#listRecipients(Integer)
     */
    @Override
    public List<Ipss> listRecipients(Integer id) {
        return modelMap.get(id).getIpsses();
    }
}
