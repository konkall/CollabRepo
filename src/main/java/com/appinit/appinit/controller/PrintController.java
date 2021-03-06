package com.appinit.appinit.controller;

import com.appinit.appinit.model.Client;
import com.appinit.appinit.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//MVC =Model view controller s a software architecture pattern, which separates application into three parts: model, view, and controller.
// The model represents a Java object carrying data. The view visualizes the data that the model contains.
// The controller manages the data flow into model object and updates the view whenever data changes; it keeps view and model separate.

@Controller
public class PrintController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ClientRepository clientRepository;

    @RequestMapping(value="/printAll.html", method = RequestMethod.GET)
    public String showPrintPage(){

        return "printAll";
    }

    @ModelAttribute("allClients")
    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

}
