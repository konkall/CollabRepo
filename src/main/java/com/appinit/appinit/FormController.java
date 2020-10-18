package com.appinit.appinit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {


    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String sendForm(Model model) {
        model.addAttribute("form", new DataStore());
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String formSubmit(@ModelAttribute DataStore dataStore, Model model) {
        model.addAttribute("form", dataStore);
        return "results";
    }

}
