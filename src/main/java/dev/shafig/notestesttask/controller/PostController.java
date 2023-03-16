package dev.shafig.notestesttask.controller;

import dev.shafig.notestesttask.model.dto.PostSaveDTO;
import dev.shafig.notestesttask.model.dto.ResponseDTO;
import dev.shafig.notestesttask.service.abstraction.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(Integer page, Integer size) {
        return ResponseEntity.ok(new ResponseDTO(service.getAll(page, size)));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> post(@RequestBody PostSaveDTO postSaveDTO, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(new ResponseDTO(service.save(postSaveDTO, httpServletRequest)));
    }

    @PutMapping("/{id}/like")
    public ResponseEntity<ResponseDTO> like(@PathVariable String id, HttpServletRequest httpServletRequest) {
        service.like(id, httpServletRequest);
        return ResponseEntity.ok(new ResponseDTO());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO());
    }
}
