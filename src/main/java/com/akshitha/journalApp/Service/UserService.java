package com.akshitha.journalApp.Service;

import com.akshitha.journalApp.entity.User;
import com.akshitha.journalApp.repository.userEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private userEntryRepo userEntryRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User userEntry){
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USERS"));
        userEntryRepository.save(userEntry);
    }

    public List<User> getAll(){
        return userEntryRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userEntryRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userEntryRepository.findByUserName(userName);
    }
}
