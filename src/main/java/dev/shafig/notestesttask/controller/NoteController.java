package dev.shafig.notestesttask.controller;

import dev.shafig.notestesttask.model.dto.NoteSaveDTO;
import dev.shafig.notestesttask.model.dto.ResponseDTO;
import dev.shafig.notestesttask.service.abstraction.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService service;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(Integer page, Integer size) {
        return ResponseEntity.ok(new ResponseDTO(service.getAll(page, size)));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody NoteSaveDTO noteSaveDTO, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(new ResponseDTO(service.save(noteSaveDTO, httpServletRequest)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO());
    }

    @PutMapping("{id}/like-unlike")
    public ResponseEntity<ResponseDTO> likeOrUnlike(@PathVariable String id, HttpServletRequest httpServletRequest) {
        service.likeOrUnlike(id, httpServletRequest);
        return ResponseEntity.ok(new ResponseDTO());
    }
}
