package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.account.Account;
import org.academiadecodigo.socialsaver.persistence.dao.AccountDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * An {@link DonationService} implementation
 */
public class DonationServiceImpl implements DonationService {

    private AccountDao accountDao;

    /**
     * Sets the account data access object
     *
     * @param accountDao the account DAO to set
     */
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * @see DonationService#get(Integer)
     */
    @Override
    public Account get(Integer id) {
        return accountDao.findById(id);
    }

    /**
     * @see DonationService#add(Account)
     */
    @Transactional
    @Override
    public Integer add(Account account) {
        return accountDao.saveOrUpdate(account).getId();
    }

    /**
     * @see DonationService#deposit(Integer, double)
     */
    @Transactional
    @Override
    public void deposit(Integer id, double amount) {

        Account account = accountDao.findById(id);

        if (account == null) {
            throw new IllegalArgumentException("invalid account id");
        }

        account.credit(amount);

        accountDao.saveOrUpdate(account);
    }

    /**
     * @see DonationService#withdraw(Integer, double)
     */
    @Transactional
    @Override
    public void withdraw(Integer id, double amount) {

        Account account = accountDao.findById(id);

        if (account == null) {
            throw new IllegalArgumentException("invalid account id");
        }

        if (!account.canWithdraw()) {
            throw new IllegalArgumentException("invalid account type");
        }

        account.debit(amount);

        accountDao.saveOrUpdate(account);
    }

    /**
     * @see DonationService#transfer(Integer, Integer, double)
     */
    @Transactional
    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        Account srcAccount = accountDao.findById(srcId);
        Account dstAccount = accountDao.findById(dstId);

        if (srcAccount == null || dstAccount == null) {
            throw new IllegalArgumentException("invalid account id");
        }

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }

        accountDao.saveOrUpdate(srcAccount);
        accountDao.saveOrUpdate(dstAccount);
    }
}

