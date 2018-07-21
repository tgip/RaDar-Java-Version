package org.academiadecodigo.socialsaver.controller;

import org.academiadecodigo.socialsaver.services.DonerService;
import org.academiadecodigo.socialsaver.services.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


	private ReceiverService receiverService;

	@Autowired
	public void setDonerService(ReceiverService receiverService) {
		this.receiverService = receiverService;
	}


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String root(Model model) {
		model.addAttribute("list", receiverService.listAllItems());
        return "list";
    }
    /*public String root() {
        return "redirect:/customer/1";
    }*/
}
