package com.appinit.appinit.controller;

import com.appinit.appinit.model.Client;
import com.appinit.appinit.model.ConfirmationToken;
import com.appinit.appinit.model.User;
import com.appinit.appinit.payload.request.SignupRequest;
import com.appinit.appinit.repository.ConfirmationTokenRepository;
import com.appinit.appinit.repository.RoleRepository;
import com.appinit.appinit.repository.UserRepository;
import com.appinit.appinit.security.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ForgotPasswordController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;


    @GetMapping(value = "/forgot_password.html")
    public ModelAndView sendForm(ModelAndView modelAndView , User user) {

        modelAndView.addObject("user", user);
        modelAndView.setViewName("forgot_password");
        return modelAndView;
    }

    @PostMapping(path = "/forgot_password") // Map ONLY POST Requests
    public ModelAndView ResetPassword( ModelAndView modelAndView,User user) {

        User existingUser = userRepository.findAllByEmail(user.getEmail());
        if (existingUser != null) {
            // Create token
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);

            // Save it
            confirmationTokenRepository.save(confirmationToken);

            // Create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("test-email@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:8083/confirm-reset?token="+confirmationToken.getConfirmationToken());

            // Send the email
            emailSenderService.sendEmail(mailMessage);

            modelAndView.addObject("message", "If your email address exists in our database, you will receive a password recovery link at your email address in a few minutes.");
            modelAndView.setViewName("forgot_password");

        } else {
            modelAndView.addObject("message", "If your email address exists in our database, you will receive a password recovery link at your email address in a few minutes.");
        }
        return modelAndView;



    }



    @RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token")String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findAllByEmail(token.getUser().getEmail());
            user.setIs_enabled(true);
            userRepository.save(user);
            modelAndView.addObject("user", user);
            modelAndView.addObject("email", user.getEmail());
            modelAndView.setViewName("resetPassword");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error_verification_email");
        }
        return modelAndView;
    }

    // Endpoint to update a user's password
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView resetUserPassword(ModelAndView modelAndView, User user) {
        if (user.getEmail() != null) {
            if(!user.getPassword().equals(user.getRepassword())){
                modelAndView.addObject("message", "Passwords do not match.");
                modelAndView.setViewName("resetPassword");
                System.out.println(user.getPassword() + user.getRepassword());

            }else {
                System.out.println(user.getPassword() + user.getRepassword());
                // Use email to find user
                User tokenUser = userRepository.findAllByEmail(user.getEmail());
                tokenUser.setPassword(encoder.encode(user.getPassword()));
                tokenUser.setRepassword(encoder.encode(user.getRepassword()));
                userRepository.save(tokenUser);
                modelAndView.addObject("message", "Password successfully reset. You can now log in with the new credentials.");
                modelAndView.setViewName("resetPassword");
            }
        } else {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error_verification_email");
        }
        return modelAndView;
    }


}
