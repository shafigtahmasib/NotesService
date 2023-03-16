package dev.shafig.notestesttask.service.implementation;

import dev.shafig.notestesttask.exception.InvalidCredentialException;
import dev.shafig.notestesttask.model.dto.LoginDTO;
import dev.shafig.notestesttask.model.dto.ResponseDTO;
import dev.shafig.notestesttask.service.abstraction.AuthenticationService;
import dev.shafig.notestesttask.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public String authenticate(LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );
        } catch (Exception ex) {
            throw new InvalidCredentialException();
        }
        return jwtUtil.generateToken(loginDTO.getUsername());
    }
}
