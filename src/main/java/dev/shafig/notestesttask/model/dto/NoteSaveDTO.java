package dev.shafig.notestesttask.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteSaveDTO {
    private String id;
    private String text;
    private LocalDateTime createdAt;
}
