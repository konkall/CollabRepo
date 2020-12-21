package com.appinit.appinit.controller;

import com.appinit.appinit.payload.request.LoginRequest;
import com.appinit.appinit.repository.RoleRepository;
import com.appinit.appinit.repository.UserRepository;
import com.appinit.appinit.security.services.UserDetailsImpl;
import com.appinit.appinit.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class IndexController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/")
    public String sendForm(Model model) {
        model.addAttribute("index", new LoginRequest());
        return "index";
    }

    @PostMapping("/resultsindex")
    public String authenticateUser(@Valid LoginRequest loginRequest, Model model) {
        model.addAttribute("index", loginRequest);



        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        /*Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        */


        model.addAttribute("details", userDetails);


        return "resultsindex";
    }


        /*
        @PostMapping(value = "/resultsindex")
        public String formSubmit(@ModelAttribute User dataStore, Model model) {
            model.addAttribute("index", dataStore);
            model.addAttribute("email", dataStore.getEmail());
            model.addAttribute("password", dataStore.getPassword());
            //System.out.println(userRepository.existsByEmail(dataStore.getEmail()));


            if ((dataStore.getEmail().equals("A@gmail.com")) && (dataStore.getPassword().equals("1")))
            {
                return "admin";

            } else {
                if ((userRepository.existsByEmail(dataStore.getEmail()))) {

                    User check = userRepository.findAllByEmail((dataStore.getEmail()));

                     //   DataStore check = entities.get(0);

                        if (check.getPassword().equals(dataStore.getPassword())) {
                            return "resultsindex";
                        } else {
                            return "notexist";
                        }

                } else {
                    return "notexist";
                }

            }

        }
        */


}
