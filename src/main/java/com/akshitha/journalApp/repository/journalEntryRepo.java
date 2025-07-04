package com.akshitha.journalApp.repository;

import com.akshitha.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface journalEntryRepo extends MongoRepository<JournalEntry, ObjectId>{

}
