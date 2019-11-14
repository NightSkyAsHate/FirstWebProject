package com.nightsky.serverapi.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.nightsky.serverapi.domain.User;
import com.nightsky.serverapi.domain.Views;
import com.nightsky.serverapi.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/dbUser")
public class UserAPI {
    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    @JsonView(Views.UserInfo.class)
    public User getById(@PathVariable("id") User user){
        return user;
    }

    @GetMapping("/validation")
    public HashMap checkUser(@RequestBody User user){
        HashMap<String, String> mapa = new HashMap<>();
        User userFromDb = userService.loadUserByUsername(user.getUsername());
        if (userFromDb.getUsername().equals(user.getUsername()) && userFromDb.getPassword().equals(user.getPassword())){
            mapa.put("response", "true");
            return mapa;
        }
        mapa.put("response", "false");
        return mapa;
    }

    @GetMapping
    @JsonView(Views.UserInfo.class)
    public List<User> getAll(){
        return userService.getClients();
    }

    @PostMapping
    @JsonView(Views.UserInfo.class)
    public boolean create(@RequestBody User user){
        return userService.createUser(user);
    }

    @JsonView(Views.UserInfo.class)
    @PutMapping("{id}")
    public User update(@PathVariable("id") User userFromDb, @RequestBody User user, @AuthenticationPrincipal User currentUser){
        return userService.updateUser(userFromDb, user, currentUser);
    }
}
