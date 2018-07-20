package org.academiadecodigo.socialsaver.services.mock;

import org.academiadecodigo.socialsaver.persistence.model.account.Account;
import org.academiadecodigo.socialsaver.services.DonationService;

/**
 * A mock {@link DonationService} implementation
 */
public class MockDonationService extends AbstractMockService<Account> implements DonationService {

    /**
     * @see DonationService#get(Integer)
     */
    @Override
    public Account get(Integer id) {
        return modelMap.get(id);
    }

    /**
     * @see DonationService#add(Account)
     */
    @Override
    public Integer add(Account account) {

        if (account.getId() == null) {
            account.setId(getNextId());
        }

        modelMap.put(account.getId(), account);

        return account.getId();
    }

    /**
     * @see DonationService#deposit(Integer, double)
     */
    public void deposit(Integer id, double amount) {
        modelMap.get(id).credit(amount);
    }

    /**
     * @see DonationService#withdraw(Integer, double)
     */
    public void withdraw(Integer id, double amount) {

        Account account = modelMap.get(id);

        if (!account.canWithdraw()) {
            return;
        }

        modelMap.get(id).debit(amount);
    }

    /**
     * @see DonationService#transfer(Integer, Integer, double)
     */
    public void transfer(Integer srcId, Integer dstId, double amount) {

        Account srcAccount = modelMap.get(srcId);
        Account dstAccount = modelMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }
}
