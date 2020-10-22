package com.appinit.appinit.controller;

import com.appinit.appinit.model.DataStore;
import com.appinit.appinit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

        @GetMapping("/")
        public String sendForm(Model model) {
            model.addAttribute("index", new DataStore());
            return "index";
        }

        @PostMapping(value = "/resultsindex")
        public String formSubmit(@ModelAttribute DataStore dataStore, Model model) {
            model.addAttribute("index", dataStore);
            model.addAttribute("email",dataStore.getEmail());
            model.addAttribute("password",dataStore.getPassword());
            //System.out.println(userRepository.existsByEmail(dataStore.getEmail()));
            if((dataStore.getEmail().equals("A@gmail.com"))&&(dataStore.getPassword().equals("12345678Aa!"))) {
                return "admin";

            }else{
                if ((userRepository.existsByEmail(dataStore.getEmail())) && (userRepository.existsByPassword(dataStore.getPassword()))) {

                    return "resultsindex";
                } else {
                    return "notexist";
                }
            }

        }




}
