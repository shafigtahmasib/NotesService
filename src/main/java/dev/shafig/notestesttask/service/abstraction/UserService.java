package dev.shafig.notestesttask.service.abstraction;

import dev.shafig.notestesttask.model.document.User;
import dev.shafig.notestesttask.model.dto.UserGetDTO;
import dev.shafig.notestesttask.model.dto.UserSaveDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    UserSaveDTO save(UserSaveDTO userSaveDTO);

    void handleIfUsernameExists(String username);

    User findById(String id);

    User findByUsername(String username);

    Page<UserGetDTO> getAll(Integer page, Integer size);

    void deleteById(String id);

    User getUserFromJWT(String token);
}
