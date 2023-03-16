package dev.shafig.notestesttask.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSaveDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String gender;
    private LocalDateTime createdAt;
}
