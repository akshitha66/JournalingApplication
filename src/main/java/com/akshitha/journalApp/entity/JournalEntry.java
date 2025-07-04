package com.akshitha.journalApp.entity;
import lombok.*;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.core.mapping.Document; // ✅ correct
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;

    private LocalDateTime date;


}
