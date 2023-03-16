package dev.shafig.notestesttask.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
public class Like {
    @Id
    private String id = UUID.randomUUID().toString();
    private User likedBy;

    public Like(User likedBy) {
        this.likedBy = likedBy;
    }
}
