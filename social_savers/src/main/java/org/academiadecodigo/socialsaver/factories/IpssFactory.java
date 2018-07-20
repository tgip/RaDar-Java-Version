package org.academiadecodigo.socialsaver.factories;

import org.academiadecodigo.socialsaver.persistence.model.account.Account;
import org.academiadecodigo.socialsaver.persistence.model.account.AccountType;
import org.academiadecodigo.socialsaver.persistence.model.account.CheckingAccount;
import org.academiadecodigo.socialsaver.persistence.model.account.SavingsAccount;

/**
 * A factory for creating accounts of different types
 */
public class IpssFactory {

    /**
     * Creates a new {@link Account}
     *
     * @param accountType the account type
     * @return the new account
     */
    public Account createAccount(AccountType accountType) {

        Account newAccount;
        switch (accountType) {
            case CHECKING:
                newAccount = new CheckingAccount();
                break;
            case SAVINGS:
                newAccount = new SavingsAccount();
                break;
            default:
                newAccount = null;

        }

        return newAccount;
    }
}
