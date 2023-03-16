package dev.shafig.notestesttask.service.abstraction;

import dev.shafig.notestesttask.model.document.Post;
import dev.shafig.notestesttask.model.dto.PostGetDTO;
import dev.shafig.notestesttask.model.dto.PostSaveDTO;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

public interface PostService {
    PostSaveDTO save(PostSaveDTO postSaveDTO, HttpServletRequest httpServletRequest);

    void like(String id, HttpServletRequest httpServletRequest);

    Page<PostGetDTO> getAll(Integer page, Integer size);

    void deleteById(String id);

    Post findById(String id);

    void save(Post post);

}
