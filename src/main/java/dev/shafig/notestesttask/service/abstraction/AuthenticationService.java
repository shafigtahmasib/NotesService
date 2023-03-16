package dev.shafig.notestesttask.service.abstraction;

import dev.shafig.notestesttask.model.dto.LoginDTO;

public interface AuthenticationService {
    String authenticate(LoginDTO loginDTO);
}
