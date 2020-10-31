package com.appinit.appinit.controller;

import com.appinit.appinit.model.User;
import com.appinit.appinit.payload.request.LoginRequest;
import com.appinit.appinit.payload.response.JwtResponse;
import com.appinit.appinit.repository.RoleRepository;
import com.appinit.appinit.repository.UserRepository;
import com.appinit.appinit.security.jwt.JwtUtils;
import com.appinit.appinit.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class IndexController {

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


        @GetMapping("/")
        public String sendForm(Model model) {
            model.addAttribute("index", new User());
            return "index";
        }

    @PostMapping("/resultsindex")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
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
