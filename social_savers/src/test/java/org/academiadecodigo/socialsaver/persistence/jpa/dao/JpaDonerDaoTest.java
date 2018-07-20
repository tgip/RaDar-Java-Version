package org.academiadecodigo.socialsaver.persistence.jpa.dao;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.academiadecodigo.socialsaver.persistence.dao.jpa.JpaCustomerDao;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class JpaDonerDaoTest {

    private JpaCustomerDao customerDao;
    private EntityManager em;

    @Before
    public void setup() {

        em = mock(EntityManager.class);

        customerDao = new JpaCustomerDao();
        customerDao.setEm(em);

    }

    @Test
    public void testFindAll() {

        // setup
        List<Doner> mockCustomers = new ArrayList<>();
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        TypedQuery typedQuery = mock(TypedQuery.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Doner.class)).thenReturn(criteriaQuery);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(em.createQuery(anyString(), any(Class.class))).thenReturn(typedQuery);
        when(em.createQuery(any(CriteriaQuery.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(mockCustomers);

        // exercise
        List<Doner> customers = customerDao.findAll();

        // verify
        verify(typedQuery, times(1)).getResultList();
        assertEquals(mockCustomers, customers);
    }

    @Test
    public void testFindById() {

        // setup
        int fakeId = 9999;
        Doner fakeCustomer = new Doner();
        fakeCustomer.setId(fakeId);
        when(em.find(Doner.class, fakeId)).thenReturn(fakeCustomer);

        // exercise
        Doner customer = customerDao.findById(fakeId);

        // verify
        verify(em, times(1)).find(Doner.class, fakeId);
        assertEquals(fakeCustomer, customer);

    }

    @Test
    public void testSaveOrUpdate() {

        // setup
        Doner fakeCustomer = new Doner();
        when(em.merge(any(Doner.class))).thenReturn(fakeCustomer);

        // exercise
        Doner customer = customerDao.saveOrUpdate(fakeCustomer);

        // verify
        verify(em, times(1)).merge(any(Doner.class));
        assertEquals(fakeCustomer, customer);

    }

    @Test
    public void testDelete() {

        // setup
        int fakeId = 9999;
        Doner fakeCustomer = new Doner();
        fakeCustomer.setId(fakeId);
        when(em.find(Doner.class, fakeId)).thenReturn(fakeCustomer);

        // exercise
        customerDao.delete(fakeId);

        // verify
        verify(em, times(1)).remove(fakeCustomer);

    }

    @Test
    public void testGetCustomerIds() {

        // setup
        List<Integer> fakeCustomerIds = new ArrayList<>();
        TypedQuery typedQuery = mock(TypedQuery.class);
        when(em.createQuery(anyString(), eq(Integer.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(fakeCustomerIds);

        // exercise
        List<Integer> customerIds = customerDao.getCustomerIds();

        // verify
        verify(em, times(1)).createQuery("select id from Customer", Integer.class);
        verify(typedQuery, times(1)).getResultList();
        assertEquals(fakeCustomerIds, customerIds);

    }

}
