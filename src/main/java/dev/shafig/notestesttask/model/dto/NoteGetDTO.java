package dev.shafig.notestesttask.model.dto;

import dev.shafig.notestesttask.model.document.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteGetDTO {
    private String id;
    private String text;
    private long likeCount;
    private User createdBy;
    private LocalDateTime createdAt;
}
