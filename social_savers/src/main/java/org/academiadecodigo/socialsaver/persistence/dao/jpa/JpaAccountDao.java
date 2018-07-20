package org.academiadecodigo.socialsaver.persistence.dao.jpa;

import org.academiadecodigo.socialsaver.persistence.model.account.Account;
import org.academiadecodigo.socialsaver.persistence.dao.AccountDao;

/**
 * A JPA {@link AccountDao} implementation
 */
public class JpaAccountDao extends GenericJpaDao<Account> implements AccountDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaAccountDao() {
        super(Account.class);
    }
}
