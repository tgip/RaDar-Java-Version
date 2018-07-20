package org.academiadecodigo.socialsaver.persistence.jpa.dao;

import org.academiadecodigo.socialsaver.persistence.model.Doner;
import org.academiadecodigo.socialsaver.persistence.model.account.Account;
import org.academiadecodigo.socialsaver.persistence.model.account.CheckingAccount;
import org.academiadecodigo.socialsaver.persistence.model.account.SavingsAccount;
import org.academiadecodigo.socialsaver.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.socialsaver.persistence.jpa.JpaIntegrationTestHelper;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.*;

public class JpaDonerDaoIntegrationTest extends JpaIntegrationTestHelper {

    private final static Integer INVALID_ID = 9999;
    private final static double DOUBLE_DELTA = 0.1;

    private JpaCustomerDao customerDao;

    @Before
    public void setup() {
        customerDao = new JpaCustomerDao();
        customerDao.setEm(em);
    }

    @Test
    public void testFindById() {

        // setup
        int id = 1;

        // exercise
        Doner customer = customerDao.findById(id);

        // verify
        assertNotNull("Doner is null", customer);
        assertEquals("Doner id is wrong", id, customer.getId().intValue());
        assertEquals("Doner first name is wrong", "Rui", customer.getFirstName());
        assertEquals("Doner last name is wrong", "Ferrão", customer.getLastName());
        assertEquals("Doner email is wrong", "mail@gmail.com", customer.getEmail());
        assertEquals("Doner phone is wrong", "777888", customer.getPhone());

    }

    @Test()
    public void testFindByIdInvalid() {

        // exercise
        Doner customer = customerDao.findById(INVALID_ID);

        // verify
        assertNull("invalid customer should not be found", customer);

    }

    @Test
    public void testFindAll() {

        // exercise
        List<Doner> customers = customerDao.findAll();

        // verify
        assertNotNull("customers are null", customers);
        assertEquals("Number of customer is wrong", 4, customers.size());

    }

    @Test
    public void testFindAllFail() {

        // setup
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Account ");
        query.executeUpdate();
        query = em.createQuery("delete from Recipient ");
        query.executeUpdate();
        query = em.createQuery("delete from Customer");
        query.executeUpdate();
        em.getTransaction().commit();

        // exercise
        List<Doner> customers = customerDao.findAll();

        // verify
        assertNotNull("Customers are null", customers);
        assertEquals("Number of customers is wrong", 0, customers.size());

    }

    @Test
    public void testAddCustomerNoAccounts() {

        // setup
        String firstName = "new first name";
        String lastName = "new last name";
        String email = "new email";
        String phone = "999666";
        Doner newCustomer = new Doner();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setPhone(phone);

        // exercise
        em.getTransaction().begin();
        Doner addedCustomer = customerDao.saveOrUpdate(newCustomer);
        em.getTransaction().commit();

        // verify
        assertNotNull("customer not added", addedCustomer);
        Doner customer = em.find(Doner.class, addedCustomer.getId());
        assertNotNull("Doner not found", customer);
        assertEquals(newCustomer.getFirstName(), customer.getFirstName());
        assertEquals(newCustomer.getLastName(), customer.getLastName());
        assertEquals(newCustomer.getEmail(), customer.getEmail());
        assertEquals(newCustomer.getPhone(), customer.getPhone());

    }

    @Test
    public void testAddCustomerWithAccounts() {

        // setup
        double caBalance = 100;
        double saBalance = 101;
        Account ca = new CheckingAccount();
        Account sa = new SavingsAccount();
        ca.credit(caBalance);
        sa.credit(saBalance);

        Doner newCustomer = new Doner();
        newCustomer.addAccount(ca);
        newCustomer.addAccount(sa);

        // exercise
        em.getTransaction().begin();
        Doner addedCustomer = customerDao.saveOrUpdate(newCustomer);
        em.getTransaction().commit();

        // verify
        assertNotNull("customer not added", addedCustomer);
        Doner customer = em.find(Doner.class, addedCustomer.getId());
        assertNotNull("customer not found", addedCustomer);
        assertNotNull("customer accounts not found", customer.getAccounts());
        assertEquals("customer number of accounts wrong", newCustomer.getAccounts().size(), customer.getAccounts().size());
        assertEquals("first account balance is wrong", caBalance, customer.getAccounts().get(0).getBalance(), DOUBLE_DELTA);
        assertEquals("second account balance is wrong", saBalance, customer.getAccounts().get(1).getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testUpdateCustomer() {

        // setup
        int id = 1;
        String firstName = "updated customer";
        Doner customer = em.find(Doner.class, id);
        customer.setFirstName(firstName);

        // exercise
        em.getTransaction().begin();
        customerDao.saveOrUpdate(customer);
        em.getTransaction().commit();

        // verify
        customer = em.find(Doner.class, id);
        assertEquals("customer first name is wrong", firstName, customer.getFirstName());

    }

    @Test
    public void testUpdateCustomerWithAccounts() {

        // setup
        int id = 1;
        String firstName = "updated customer";
        Doner existingCustomer = em.find(Doner.class, id);
        existingCustomer.setFirstName(firstName);
        existingCustomer.getAccounts().get(0).canCredit(100);

        // exercise
        em.getTransaction().begin();
        customerDao.saveOrUpdate(existingCustomer);
        em.getTransaction().commit();

        // verify
        Doner customer = em.find(Doner.class, id);
        assertEquals("customer first name is wrong", firstName, customer.getFirstName());
        assertEquals("number of accounts is wrong", 2, customer.getAccounts().size());
        assertEquals("account balance is wrong", 100, customer.getAccounts().get(0).getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testUpdatedCustomerOrphanAccountDelete() {

        // setup
        int id = 1;
        String firstName = "updated customer";
        Doner existingCustomer = em.find(Doner.class, id);
        existingCustomer.setFirstName(firstName);
        existingCustomer.removeAccount(existingCustomer.getAccounts().get(1));

        // exercise
        em.getTransaction().begin();
        customerDao.saveOrUpdate(existingCustomer);
        em.getTransaction().commit();

        // verify
        Doner customer = em.find(Doner.class, id);
        assertEquals("customer first name is wrong", firstName, customer.getFirstName());
        assertEquals("number of accounts is wrong", 1, customer.getAccounts().size());
        assertEquals("account balance is wrong", 100, customer.getAccounts().get(0).getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testDeleteCustomer() {

        // setup
        int id = 1;

        // exercise
        em.getTransaction().begin();
        customerDao.delete(id);
        em.getTransaction().commit();

        // verify
        Doner customer = em.find(Doner.class, id);
        assertNull("should be null", customer);
    }

    @Test
    public void testDeleteCustomerNoAccounts() {

        // setup
        int id = 4;

        // exercise
        em.getTransaction().begin();
        customerDao.delete(id);
        em.getTransaction().commit();

        // verify
        Doner customer = em.find(Doner.class, id);
        assertNull("should be null", customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteInvalid() {

        // exercise
        em.getTransaction().begin();
        customerDao.delete(INVALID_ID);
        em.getTransaction().commit();
    }
}
