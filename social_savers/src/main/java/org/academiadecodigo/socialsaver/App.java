package org.academiadecodigo.socialsaver;


import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.academiadecodigo.socialsaver.persistence.model.Entity.Receiver;
import org.academiadecodigo.socialsaver.services.DonerService;
import org.academiadecodigo.socialsaver.services.ReceiverService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();

    }

    private void bootStrap() {

	    DonerService donerService = new DonerService();
	    // add a doner
	    Doner d1 = new Doner();
	    d1.setName("Antonino");
	    d1.setPassword("a");
	    donerService.add(d1);
	    Doner d2 = new Doner();
	    d2.setName("Glintt");
	    d2.setPassword("g");
	    donerService.add(d2);

	    ReceiverService receiverService = new ReceiverService();
	    // add an IPSS
	    Receiver r1 = new Receiver();
	    r1.setName("Humberto");

        String profile = getProfile();
        System.out.println("#### Active Profiles " + profile + " ####");

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles(getProfile());
        ctx.load(Config.SPRING_CONFIG);
        ctx.refresh();

    }

    private String getProfile() {

        String target = System.getenv(Config.SPRING_PROFILE_ENV_VAR);
        return target == null ? Config.SPRING_DEFAULT_PROFILE : target;
    }
}
