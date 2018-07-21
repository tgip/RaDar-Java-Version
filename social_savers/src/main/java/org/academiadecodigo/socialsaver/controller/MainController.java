package org.academiadecodigo.socialsaver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String root() {
        return "redirect:/login";
    }
    /*public String root() {
        return "redirect:/customer/1";
    }*/
}
