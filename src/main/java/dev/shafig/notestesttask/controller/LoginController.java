package dev.shafig.notestesttask.controller;

import dev.shafig.notestesttask.model.dto.LoginDTO;
import dev.shafig.notestesttask.model.dto.ResponseDTO;
import dev.shafig.notestesttask.service.abstraction.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(new ResponseDTO(service.authenticate(loginDTO)));
    }
}
