package org.academiadecodigo.socialsaver.services;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {

    private AuthServiceImpl authService;
    private DonerService donerService;

    @Before
    public void setup() {

        authService = new AuthServiceImpl();
        donerService = mock(DonerService.class);

        authService.setDonerService(donerService);
    }

    @Test
    public void testAuthenticate() {

        // setup
        int fakeId = 9999;
        Doner fakeCustomer = mock(Doner.class);
        when(donerService.get(fakeId)).thenReturn(fakeCustomer);

        // exercise
        boolean authResult = authService.authenticate(fakeId);


        // verify
        assertTrue(authResult);

    }

    @Test
    public void testAuthenticateFail() {

        // setup
        int fakeId = 9999;
        Doner fakeCustomer = mock(Doner.class);
        when(donerService.get(fakeId)).thenReturn(null);

        // exercise
        boolean authResult = authService.authenticate(fakeId);


        // verify
        assertFalse(authResult);
    }

    @Test
    public void testGetAccessingCustomer() {

        // setup
        int fakeId = 9999;
        Doner fakeCustomer = mock(Doner.class);
        when(donerService.get(fakeId)).thenReturn(fakeCustomer);
        when(fakeCustomer.getId()).thenReturn(fakeId);
        authService.authenticate(fakeId);

        // exercise
        Doner customer = authService.getAccessingCustomer();

        // verify
        verify(donerService, times(2)).get(fakeId);
        assertEquals(fakeCustomer, customer);

    }
}
