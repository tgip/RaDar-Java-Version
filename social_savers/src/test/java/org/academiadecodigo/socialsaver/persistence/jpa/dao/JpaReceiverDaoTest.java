package org.academiadecodigo.socialsaver.persistence.jpa.dao;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;
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

public class JpaReceiverDaoTest {

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
        List<Receiver> mockReceivers = new ArrayList<>();
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        TypedQuery typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Receiver.class)).thenReturn(criteriaQuery);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(any(CriteriaQuery.class))).thenReturn(typedQuery);
        when(em.createQuery(anyString(), any(Class.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(mockReceivers);

        // exercise
        List<Receiver> receivers = recipientDao.findAll();

        // verify
        verify(typedQuery, times(1)).getResultList();
        assertEquals(mockReceivers, receivers);
    }

    @Test
    public void testFindById() {

        // setup
        int fakeId = 9999;
        Receiver fakeReceiver = new Receiver();
        fakeReceiver.setId(fakeId);
        when(em.find(Receiver.class, fakeId)).thenReturn(fakeReceiver);

        // exercise
        Receiver receiver = recipientDao.findById(fakeId);

        // verify
        verify(em, times(1)).find(Receiver.class, fakeId);
        assertEquals(fakeReceiver, receiver);

    }

    @Test
    public void testSaveOrUpdate() {

        // setup
        Receiver fakeReceiver = new Receiver();
        when(em.merge(any(Receiver.class))).thenReturn(fakeReceiver);

        // exercise
        Receiver receiver = recipientDao.saveOrUpdate(fakeReceiver);

        // verify
        verify(em, times(1)).merge(any(Receiver.class));
        assertEquals(fakeReceiver, receiver);

    }

    @Test
    public void testDelete() {

        // setup
        int fakeId = 9999;
        Receiver fakeReceiver = new Receiver();
        fakeReceiver.setId(fakeId);
        when(em.find(Receiver.class, fakeId)).thenReturn(fakeReceiver);

        // exercise
        recipientDao.delete(fakeId);

        // verify
        verify(em, times(1)).remove(fakeReceiver);

    }
}
