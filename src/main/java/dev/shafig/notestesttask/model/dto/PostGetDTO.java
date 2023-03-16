package dev.shafig.notestesttask.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostGetDTO {
    private String id;
    private String text;
    private String mediaUrl;
    private long likeCount;
    private UserGetDTO createdBy;
    private LocalDateTime createdAt;

}
