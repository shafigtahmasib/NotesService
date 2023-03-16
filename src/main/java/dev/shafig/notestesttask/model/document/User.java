package dev.shafig.notestesttask.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Data
public class User {
    @Id
    private String id = UUID.randomUUID().toString();
    @Indexed(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime createdAt;
}
