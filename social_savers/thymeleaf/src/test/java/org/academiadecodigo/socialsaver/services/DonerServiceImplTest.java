package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Doner;
import org.academiadecodigo.socialsaver.persistence.model.account.Account;
import org.academiadecodigo.socialsaver.persistence.model.account.CheckingAccount;
import org.academiadecodigo.socialsaver.persistence.dao.CustomerDao;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class DonerServiceImplTest {

    private static final double DOUBLE_PRECISION = 0.1;

    private CustomerDao customerDao;
    private DonerServiceImpl customerService;

    @Before
    public void setup() {

        customerDao = mock(CustomerDao.class);

        customerService = new DonerServiceImpl();
        customerService.setCustomerDao(customerDao);

    }

    @Test
    public void testGet() {

        // setup
        int fakeId = 9999;
        Doner fakeCustomer = new Doner();
        when(customerDao.findById(fakeId)).thenReturn(fakeCustomer);

        // exercise
        Doner customer = customerService.get(fakeId);

        // verify
        assertEquals(fakeCustomer, customer);
    }

    @Test
    public void testGetBalance() {

        // setup
        int fakeId = 9999;
        Account a1 = new CheckingAccount();
        Account a2 = new CheckingAccount();
        a1.credit(100);
        a2.credit(200);
        Doner fakeCustomer = new Doner();
        fakeCustomer.getAccounts().add(0, a1);
        fakeCustomer.getAccounts().add(1, a2);
        when(customerDao.findById(fakeId)).thenReturn(fakeCustomer);

        // exercise
        double result = customerService.getBalance(fakeId);

        // verify
        assertEquals(a1.getBalance() + a2.getBalance(), result, DOUBLE_PRECISION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBalanceInvalidCustomer() {

        // setup
        when(customerDao.findById(anyInt())).thenReturn(null);

        // exercise
        customerService.getBalance(1);

    }

    @Test
    public void testListCustomerAccountIds() {

        // setup
        int fakeId = 9999;
        Account a1 = new CheckingAccount();
        Account a2 = new CheckingAccount();
        a1.credit(100);
        a1.setId(1);
        a2.credit(200);
        a2.setId(2);
        Doner fakeCustomer = new Doner();
        fakeCustomer.getAccounts().add(a1);
        fakeCustomer.getAccounts().add(a2);
        when(customerDao.findById(fakeId)).thenReturn(fakeCustomer);

        // exercise
        Set<Integer> accountIds = customerService.listCustomerAccountIds(fakeId);

        // verify
        assertNotNull(accountIds);
        assertEquals(fakeCustomer.getAccounts().size(), accountIds.size());
        assertTrue(accountIds.contains(a1.getId()));
        assertTrue(accountIds.contains(a2.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListCustomerAccountIdsInvalidId() {

        // setup
        when(customerDao.findById(anyInt())).thenReturn(null);

        // exercise
        customerService.listCustomerAccountIds(1);

    }

}
