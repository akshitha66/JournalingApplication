package com.akshitha.journalApp.Controller;

import com.akshitha.journalApp.Service.UserService;
import com.akshitha.journalApp.Service.journalEntryService;
import com.akshitha.journalApp.entity.JournalEntry;
import com.akshitha.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private journalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){  // localhost:8080/journal POST
        try{
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry,  HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJourneyEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

   @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJourneyEntryById(@PathVariable ObjectId myId, @PathVariable String userName){
       journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntrybyId(@PathVariable ObjectId myId,
                                                    @RequestBody JournalEntry newEntry,
                                                    @PathVariable String userName) {
        JournalEntry oldEntry = journalEntryService.findById(myId).orElse(null);

        if (oldEntry != null) {
            oldEntry.setTitle(
                    newEntry.getTitle() != null && !newEntry.getTitle().equals("")
                            ? newEntry.getTitle()
                            : oldEntry.getTitle()
            );

            oldEntry.setContent(
                    newEntry.getContent() != null && !newEntry.getContent().equals("")
                            ? newEntry.getContent()
                            : oldEntry.getContent()
            );

            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
