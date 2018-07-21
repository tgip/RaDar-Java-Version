package org.academiadecodigo.socialsaver.controller;

import org.academiadecodigo.socialsaver.persistence.model.Entity.Doner;
import org.academiadecodigo.socialsaver.services.DonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private DonerService donerService;

    @Autowired
    public void setDonerService(DonerService donerService) {
        this.donerService = donerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String list(@PathVariable Integer id, Model model) {
        //model.addAttribute("batata", donerService.get(id));
        return "details";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String add(Model model) {
        model.addAttribute("customer", new Doner());
        return "donater_reg";
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public String createCustomer(@ModelAttribute("customer") Doner customer) {


        return "redirect:/customer/1";
    }

}
