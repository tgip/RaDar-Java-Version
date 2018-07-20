package org.academiadecodigo.socialsaver.persistence.jpa.dao;

import org.academiadecodigo.socialsaver.persistence.model.Ipss;
import org.academiadecodigo.socialsaver.persistence.dao.jpa.JpaRecipientDao;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class JpaIpssDaoTest {

    private JpaRecipientDao recipientDao;
    private EntityManager em;

    @Before
    public void setup() {

        em = mock(EntityManager.class);
        recipientDao = new JpaRecipientDao();
        recipientDao.setEm(em);

    }

    @Test
    public void testFindAll() {

        // setup
        List<Ipss> mockIpsses = new ArrayList<>();
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        TypedQuery typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Ipss.class)).thenReturn(criteriaQuery);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(any(CriteriaQuery.class))).thenReturn(typedQuery);
        when(em.createQuery(anyString(), any(Class.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(mockIpsses);

        // exercise
        List<Ipss> ipsses = recipientDao.findAll();

        // verify
        verify(typedQuery, times(1)).getResultList();
        assertEquals(mockIpsses, ipsses);
    }

    @Test
    public void testFindById() {

        // setup
        int fakeId = 9999;
        Ipss fakeIpss = new Ipss();
        fakeIpss.setId(fakeId);
        when(em.find(Ipss.class, fakeId)).thenReturn(fakeIpss);

        // exercise
        Ipss ipss = recipientDao.findById(fakeId);

        // verify
        verify(em, times(1)).find(Ipss.class, fakeId);
        assertEquals(fakeIpss, ipss);

    }

    @Test
    public void testSaveOrUpdate() {

        // setup
        Ipss fakeIpss = new Ipss();
        when(em.merge(any(Ipss.class))).thenReturn(fakeIpss);

        // exercise
        Ipss ipss = recipientDao.saveOrUpdate(fakeIpss);

        // verify
        verify(em, times(1)).merge(any(Ipss.class));
        assertEquals(fakeIpss, ipss);

    }

    @Test
    public void testDelete() {

        // setup
        int fakeId = 9999;
        Ipss fakeIpss = new Ipss();
        fakeIpss.setId(fakeId);
        when(em.find(Ipss.class, fakeId)).thenReturn(fakeIpss);

        // exercise
        recipientDao.delete(fakeId);

        // verify
        verify(em, times(1)).remove(fakeIpss);

    }
}
