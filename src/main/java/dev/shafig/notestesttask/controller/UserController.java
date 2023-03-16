package dev.shafig.notestesttask.controller;

import dev.shafig.notestesttask.model.dto.ResponseDTO;
import dev.shafig.notestesttask.model.dto.UserSaveDTO;
import dev.shafig.notestesttask.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(Integer page, Integer size) {
        return ResponseEntity.ok(new ResponseDTO(service.getAll(page, size)));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> post(@RequestBody UserSaveDTO userSaveDTO) {
        return ResponseEntity.ok(new ResponseDTO(service.save(userSaveDTO)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO());
    }
}
