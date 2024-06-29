package com.bookmanagement.web;

import com.bookmanagement.model.Role;
import com.bookmanagement.model.User;
import com.bookmanagement.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerRequest", new User());
        return "register_page";
    }
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           @RequestParam Role role){
        User registeredUser = userServiceImpl.registerUser(username, password, email, role);
        System.out.println(registeredUser);
        return registeredUser == null ? "error_page" : "redirect:/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password){
        User authentication = userServiceImpl.authentication(username, password);
        if(authentication == null){
            return "error_page";
        }
        return "home";
    }

    @GetMapping("/error_page")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent","error_page");
        return "error_page";
    }
}
