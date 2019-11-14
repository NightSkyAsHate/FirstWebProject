package com.nightsky.serverapi.controller;

import com.nightsky.serverapi.domain.User;
import com.nightsky.serverapi.domain.UserDto;
import com.nightsky.serverapi.repository.CargoRepository;
import com.nightsky.serverapi.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ReviewRepo reviewRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){
        HashMap<Object, Object> data = new HashMap<>();
        if (user != null){
            data.put("profile", new UserDto(user.getId(), user.getUsername(), user.getPhoneNumber(), user.getRoles()));
        }
        data.put("reviews", reviewRepo.findAll());
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}
