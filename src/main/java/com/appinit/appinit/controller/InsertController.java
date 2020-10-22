package com.appinit.appinit.controller;

import com.appinit.appinit.utilities.CSV;
import com.appinit.appinit.model.Client;
import com.appinit.appinit.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

//MVC =Model view controller s a software architecture pattern, which separates application into three parts: model, view, and controller.
// The model represents a Java object carrying data. The view visualizes the data that the model contains.
// The controller manages the data flow into model object and updates the view whenever data changes; it keeps view and model separate.

@Controller
public class InsertController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ClientRepository clientRepository;


    @GetMapping(value = "/insert.html")
    public String sendForm(Model model) {
        model.addAttribute("insert", new Client());
        return "insert";
    }

    @PostMapping(path="/insertResultsform") // Map ONLY POST Requests
    public String addNewUser (@RequestParam String name
            , @RequestParam String ssn, @RequestParam String accountType, @RequestParam double balance, @ModelAttribute Client n, Model model) {
        // @ResponseBody means the returned String is the response, not a view name if you want to put5 it
        // @RequestParam means it is a parameter from the GET or POST request


        n.setName(name);
        n.setSsn(ssn);
        n.setAccountType(accountType);
        n.setBalance(balance);
        clientRepository.save(n);

        model.addAttribute("insert",n);
        model.addAttribute("name",n.getName());
        model.addAttribute("ssn",n.getSsn());
        model.addAttribute("accountType",n.getAccountType());
        model.addAttribute("balance",n.getBalance());
        return "insertResultsform";
    }

    @PostMapping(path="/upload") // Map ONLY POST Requests
    public String addNewUser (@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Model model) {
        // @ResponseBody means the returned String is the response, not a view name if you want to put5 it
        // @RequestParam means it is a parameter from the GET or POST request

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/insert.html";
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path path=null;
        try {
            path = Paths.get("C:\\Users\\Konstantinos\\Desktop\\upload\\" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);



        } catch (IOException e) {
            e.printStackTrace();
        }

        //int i = 0;
        String pathString = path.toString();
        List<String[]> newAccountHolders = CSV.read(pathString);
        for (String[] accountHolder : newAccountHolders){
            Client n = new Client();

            n.setName(accountHolder[0]);
            n.setSsn(accountHolder[1]);
            n.setAccountType(accountHolder[2]);
            n.setBalance(Double.parseDouble(accountHolder[3]));

            clientRepository.save(n);

        }
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        model.addAttribute("insert", new Client());
        //model.addAttribute("file",fileName);
        return "redirect:/insert.html";
    }

    //cntrol +alt+sft + /  --- registry -- compile automate allow
    //control+als+s  --- build project automatically
}
