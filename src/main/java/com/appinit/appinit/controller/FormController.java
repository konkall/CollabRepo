package com.appinit.appinit.controller;

import com.appinit.appinit.model.ERole;
import com.appinit.appinit.model.Role;
import com.appinit.appinit.model.User;
import com.appinit.appinit.payload.request.SignupRequest;
import com.appinit.appinit.repository.UserRepository;
import com.appinit.appinit.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appinit.appinit.repository.RoleRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

//MVC =Model view controller s a software architecture pattern, which separates application into three parts: model, view, and controller.
// The model represents a Java object carrying data. The view visualizes the data that the model contains.
// The controller manages the data flow into model object and updates the view whenever data changes; it keeps view and model separate.

@Controller
public class FormController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping(value = "/form.html")
    public String sendForm(Model model) {
        model.addAttribute("form", new SignupRequest());
        return "form";
    }

    @PostMapping(path = "/resultsform") // Map ONLY POST Requests
    public String registerUser(@Valid @ModelAttribute("form") SignupRequest signUpRequest, RedirectAttributes attributes, Model model) {
        // @ResponseBody means the returned String is the response, not a view name if you want to put5 it
        // @RequestParam means it is a parameter from the GET or POST request

        System.out.println(signUpRequest.getUsername() +" "+ signUpRequest.getEmail() + " " +
                signUpRequest.getRoles() + " " + signUpRequest.getPassword() + " " + signUpRequest.getRepassword() + "\n");

        if(!signUpRequest.getPassword().equals(signUpRequest.getRepassword())){
            attributes.addFlashAttribute("message", "Passwords do not match.");
            model.addAttribute("form", new SignupRequest());
            return "redirect:/form.html";
        }

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            System.out.println("1");

            model.addAttribute("form", new SignupRequest());
            attributes.addFlashAttribute("message", "Error: Username is already taken!");
            return "redirect:/form.html";
            //return ResponseEntity
            //       .badRequest()
            //        .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            System.out.println("2");

            model.addAttribute("form", new SignupRequest());
            attributes.addFlashAttribute("message", "Error: Email is already in use!");
            return "redirect:/form.html";
            //return ResponseEntity
             //       .badRequest()
             //       .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account



        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strRoles = signUpRequest.getRoles();


        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            //strRoles.forEach(role -> {
                switch (strRoles) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            }
        //);
        //}

        user.setRoles(roles);
        userRepository.save(user);

        System.out.println("3");
        attributes.addFlashAttribute("message", "User registered successfully!");
        model.addAttribute("form", new SignupRequest());

        return "redirect:/form.html";
        //return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    }


