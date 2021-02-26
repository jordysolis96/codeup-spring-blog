package com.spring.springblog.controllers;

import com.spring.springblog.model.User;
import com.spring.springblog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public HomeController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public String landingPage() {
        return "posts/index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String showsSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String createUser(@ModelAttribute User user){
        String password = user.getPassword();
        String hash = passwordEncoder.encode(password);
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }
}
