package dev.shafig.notestesttask.model.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private Boolean success;
    private Object data;

    public ResponseDTO() {
        this.success = true;
    }

    public ResponseDTO(Object data) {
        this.success = true;
        this.data = data;
    }
}
