package org.academiadecodigo.socialsaver.controller;


import org.academiadecodigo.socialsaver.services.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private ReceiverService receiver;

    @Autowired
    public void setReceiver(ReceiverService receiver) {
        this.receiver = receiver;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String listProduts(Model model) {
        model.addAttribute("list", receiver.listAllItems() );
        return "Main";
    }
    /*public String root() {
        return "redirect:/customer/1";
    }*/
    @RequestMapping(method = RequestMethod.GET, value = "/donate")
    public String donate(Model model) {

        return "donate";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/Main")
    public String main(Model model) {

        return "Main";
    }

}
