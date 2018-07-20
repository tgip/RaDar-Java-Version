package org.academiadecodigo.socialsaver.persistence.dao.jpa;

import org.academiadecodigo.socialsaver.persistence.model.Doner;
import org.academiadecodigo.socialsaver.persistence.dao.CustomerDao;

import java.util.List;

/**
 * A JPA {@link CustomerDao} implementation
 */
public class JpaCustomerDao extends GenericJpaDao<Doner> implements CustomerDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaCustomerDao() {
        super(Doner.class);
    }

    /**
     * @see CustomerDao#getCustomerIds()
     */
    public List<Integer> getCustomerIds() {
        return em.createQuery("select id from Customer", Integer.class)
                .getResultList();
    }
}
