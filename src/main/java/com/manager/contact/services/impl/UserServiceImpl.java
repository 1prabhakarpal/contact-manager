package com.manager.contact.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manager.contact.entities.User;
import com.manager.contact.entities.repositories.UserRepo;
import com.manager.contact.helper.AppConstants;
import com.manager.contact.helper.ResourceNotFoundException;
import com.manager.contact.services.UserService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // @Autowired
    // private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        //Generate Dynamic UserId
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getId()).orElseThrow(()->new ResourceNotFoundException("User not found"));
        //update user2 from user
        user2.setFName(user.getFName());
        user2.setLName(user.getLName());
        user2.setEmail(user.getEmail());
        user2.setPassword(passwordEncoder.encode(user.getPassword()));
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setAbout(user.getAbout());
        user2.setGender(user.getGender());
        user2.setAddress(user.getAddress());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneNumberVerified(user.isPhoneNumberVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderId(user.getProviderId());
        user2.setDob(user.getDob());

        User savedUser = userRepo.save(user2);
        return Optional.of(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
        userRepo.delete(user);
    }

    @Override
    public boolean isUserExist(String id) {
        User user = userRepo.findById(id).orElse(null);
        return user != null;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user!= null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
