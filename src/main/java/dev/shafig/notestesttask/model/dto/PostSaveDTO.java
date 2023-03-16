package dev.shafig.notestesttask.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostSaveDTO {
    private String id;
    private String text;
    private String mediaUrl;
    private LocalDateTime createdAt;

}
