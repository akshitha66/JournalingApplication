package com.akshitha.journalApp.Service;

import com.akshitha.journalApp.entity.JournalEntry;
import com.akshitha.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import com.akshitha.journalApp.repository.journalEntryRepo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class journalEntryService {
 @Autowired
    private journalEntryRepo journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
 public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error has occured while saving the entry.", e);
        }

 }

 public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

 public List<JournalEntry> getAll(){
     return journalEntryRepository.findAll();
 }

 public Optional<JournalEntry> findById(ObjectId id){
     return journalEntryRepository.findById(id);
 }

 public void deleteById(ObjectId id, String userName){
     User user = userService.findByUserName(userName);
     user.getJournalEntries().removeIf(x -> x.getId().equals(id));
     userService.saveEntry(user);
     journalEntryRepository.deleteById(id);
 }
}
//Controller Calls -- Service, Service calls -- Repository