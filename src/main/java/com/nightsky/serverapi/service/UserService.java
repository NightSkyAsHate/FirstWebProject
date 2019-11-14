package com.nightsky.serverapi.service;

import com.nightsky.serverapi.domain.Role;
import com.nightsky.serverapi.domain.User;
import com.nightsky.serverapi.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean createUser(User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            return false;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return true;
        }
    }

    public User updateUser(User userFromDb, User user, User currentUser){
        User userByUsername = userRepo.findByUsername(user.getUsername());
        if (currentUser.equals(userFromDb) && (userByUsername == null || userFromDb.equals(userByUsername))){
            BeanUtils.copyProperties(user, userFromDb, "id", "password");
            BeanUtils.copyProperties(user, currentUser, "id", "password");
            return userRepo.save(userFromDb);
        } else {
            return null;
        }
    }

    public List<User> getClients(){
        List<User> all = userRepo.findAll();
        List<User> result = new ArrayList<>();
        for (User a: all){
            boolean flag = true;
            for (Role role: a.getRoles()){
                if (role.equals(Role.MANAGER)) flag = false;
            }
            if (flag) result.add(a);
        }
        return result;
    }
}
