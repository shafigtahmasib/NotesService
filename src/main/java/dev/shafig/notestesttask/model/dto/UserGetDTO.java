package dev.shafig.notestesttask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserGetDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDateTime createdAt;

}
