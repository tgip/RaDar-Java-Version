package org.academiadecodigo.socialsaver.persistence.dao.jpa;

import org.academiadecodigo.socialsaver.persistence.model.Ipss;
import org.academiadecodigo.socialsaver.persistence.dao.RecipientDao;

/**
 * A JPA {@link RecipientDao} implementation
 */
public class JpaRecipientDao extends GenericJpaDao<Ipss> implements RecipientDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaRecipientDao() {
        super(Ipss.class);
    }
}
