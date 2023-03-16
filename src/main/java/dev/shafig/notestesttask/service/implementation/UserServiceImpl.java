package dev.shafig.notestesttask.service.implementation;

import dev.shafig.notestesttask.exception.InvalidCredentialException;
import dev.shafig.notestesttask.exception.ResourceNotFoundException;
import dev.shafig.notestesttask.exception.UsernameAlreadyExistsException;
import dev.shafig.notestesttask.model.document.Like;
import dev.shafig.notestesttask.model.document.Note;
import dev.shafig.notestesttask.model.document.User;
import dev.shafig.notestesttask.model.dto.UserGetDTO;
import dev.shafig.notestesttask.model.dto.UserSaveDTO;
import dev.shafig.notestesttask.repository.UserRepository;
import dev.shafig.notestesttask.service.abstraction.UserService;
import dev.shafig.notestesttask.util.jwt.JwtUtil;
import dev.shafig.notestesttask.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final Mapper mapper;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @Override
    public User getUserFromJWT(String token) {
        return token != null ? findByUsername(jwtUtil.extractUsername(token.substring(7))) : null;
    }

    @Override
    public User findById(String id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void handleIfUsernameExists(String username) {
        repository.findByUsername(username).ifPresent(x -> {
            throw new UsernameAlreadyExistsException();
        });
    }

    @Override
    public UserSaveDTO save(UserSaveDTO userSaveDTO) {
        User user;
        if (userSaveDTO.getId() == null)
            handleIfUsernameExists(userSaveDTO.getUsername());
        else {
            user = findById(userSaveDTO.getId());
            if (!user.getUsername().equals(userSaveDTO.getUsername()))
                handleIfUsernameExists(userSaveDTO.getUsername());
        }
        user = mapper.convertOneDim(userSaveDTO, User.class);
        user.setPassword(encoder.encode(userSaveDTO.getPassword()));
        user = repository.save(user);
        userSaveDTO.setPassword(null);
        userSaveDTO.setId(user.getId());
        return userSaveDTO;
    }

    @Override
    public Page<UserGetDTO> getAll(Integer page, Integer size) {
        var users = repository.findAll(PageRequest.of(page, size));
        return users.map(x -> mapper.convertOneDim(x, UserGetDTO.class));
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        if (findById(id) != null)
            repository.deleteById(id);
        else throw new ResourceNotFoundException();
    }
}
