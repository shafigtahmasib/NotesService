package dev.shafig.notestesttask.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
@Data
public class Post {
    @Id
    private String id = UUID.randomUUID().toString();
    private String text;
    private String mediaUrl;
    private User createdBy;
    private LocalDateTime createdAt;
    private List<Like> likes = new ArrayList<>();

}
