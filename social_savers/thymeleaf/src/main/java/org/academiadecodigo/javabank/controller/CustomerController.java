package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
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

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String list(@PathVariable Integer id, Model model) {
        model.addAttribute("batata", customerService.get(id));
        return "details";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String add(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public String createCustomer(@ModelAttribute("customer") Customer customer) {
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastName());

        return "redirect:/customer/1";
    }

}
