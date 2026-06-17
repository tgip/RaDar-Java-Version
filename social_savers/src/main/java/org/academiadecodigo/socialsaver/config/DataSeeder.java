package org.academiadecodigo.socialsaver.config;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;
import org.academiadecodigo.socialsaver.services.DonerService;
import org.academiadecodigo.socialsaver.services.ReceiverService;
import org.academiadecodigo.socialsaver.security.PasswordUtil;
import org.springframework.context.event.EventListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;

/**
 * Seeds initial data after the Spring context is ready. Only active in non-prod profiles.
 */
@Component
@Profile("!prod")
public class DataSeeder {

    private final DonerService donerService;
    private final ReceiverService receiverService;

    public DataSeeder(DonerService donerService, ReceiverService receiverService) {
        this.donerService = donerService;
        this.receiverService = receiverService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seed() {
        // Seed doners
        Doner d1 = new Doner();
        d1.setName("Antonino");
        d1.setPassword(PasswordUtil.hash("a"));
        donerService.add(d1);

        Doner d2 = new Doner();
        d2.setName("Glintt");
        d2.setPassword(PasswordUtil.hash("g"));
        donerService.add(d2);

        // Seed receiver
        Receiver r1 = new Receiver();
        r1.setName("Humberto");
        receiverService.add(r1);
    }
}
