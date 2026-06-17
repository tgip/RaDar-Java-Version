package org.academiadecodigo.socialsaver;


import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;
import org.academiadecodigo.socialsaver.services.DonerService;
import org.academiadecodigo.socialsaver.services.ReceiverService;
import org.springframework.beans.BeansException;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();

    }

    private void bootStrap() {

        String profile = getProfile();
        System.out.println("#### Active Profiles " + profile + " ####");

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles(getProfile());
        ctx.load(Config.SPRING_CONFIG);
        ctx.refresh();

        DonerService donerService;
        ReceiverService receiverService;

        // Try to obtain services from Spring context, fall back to manual instantiation
        try {
            donerService = ctx.getBean(DonerService.class);
        } catch (BeansException e) {
            donerService = new DonerService();
        }

        try {
            receiverService = ctx.getBean(ReceiverService.class);
        } catch (BeansException e) {
            receiverService = new ReceiverService();
        }

        // Seed some data in a safe way (services will handle hashing/validation)
        Doner d1 = new Doner();
        d1.setName("Antonino");
        d1.setPassword("a");
        donerService.add(d1);

        Doner d2 = new Doner();
        d2.setName("Glintt");
        d2.setPassword("g");
        donerService.add(d2);

        Receiver r1 = new Receiver();
        r1.setName("Humberto");
        receiverService.add(r1);

    }

    private String getProfile() {

        String target = System.getenv(Config.SPRING_PROFILE_ENV_VAR);
        if (target == null || target.trim().isEmpty()) {
            target = System.getenv("SPRING_PROFILES_ACTIVE");
        }
        return target == null ? Config.SPRING_DEFAULT_PROFILE : target;
    }
}
