package com.akshitha.journalApp.repository;

import com.akshitha.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userEntryRepo extends MongoRepository<User, ObjectId>{
    User findByUserName(String username);

    void deleteByUserName(String username);
}
