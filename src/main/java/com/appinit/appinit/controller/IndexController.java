package com.appinit.appinit.controller;

import com.appinit.appinit.model.DataStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {



        @GetMapping("/")
        public String sendForm(Model model) {
            model.addAttribute("index", new DataStore());
            return "index";
        }

        @PostMapping(value = "/resultsindex")
        public String formSubmit(@ModelAttribute DataStore dataStore, Model model) {
            model.addAttribute("index", dataStore);
           // model.addAttribute("firstname",dataStore.getFirstname());
            //model.addAttribute("lastname",dataStore.getLastname());
            model.addAttribute("email",dataStore.getEmail());
            model.addAttribute("password",dataStore.getPassword());

            return "resultsindex";
        }




}
