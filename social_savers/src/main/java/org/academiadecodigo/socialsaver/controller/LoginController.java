package org.academiadecodigo.socialsaver.controller;

import org.academiadecodigo.socialsaver.command.LoginForm;
import org.academiadecodigo.socialsaver.services.DonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.ManyToOne;

@Controller
public class LoginController {

    private DonerService donerService;

    @Autowired
    public void setDonerService(DonerService donerService) {
        this.donerService = donerService;
    }

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String root(Model model) {
	    model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login(@ModelAttribute LoginForm loginForm){
        if(donerService.login(loginForm.getName(),loginForm.getPassword())){
            return "Main";
        }
        return "login";
    }
}
