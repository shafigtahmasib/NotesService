package dev.shafig.notestesttask.service.implementation;

import dev.shafig.notestesttask.exception.PostAlreadyLikedException;
import dev.shafig.notestesttask.exception.ResourceNotFoundException;
import dev.shafig.notestesttask.model.document.Like;
import dev.shafig.notestesttask.model.document.Post;
import dev.shafig.notestesttask.model.dto.PostGetDTO;
import dev.shafig.notestesttask.model.dto.PostSaveDTO;
import dev.shafig.notestesttask.model.enumerated.HeaderType;
import dev.shafig.notestesttask.repository.PostRepository;
import dev.shafig.notestesttask.service.abstraction.PostService;
import dev.shafig.notestesttask.service.abstraction.UserService;
import dev.shafig.notestesttask.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final UserService userService;
    private final Mapper mapper;

    @Override
    public void deleteById(String id) {
        if (findById(id) != null)
            repository.deleteById(id);
        else throw new ResourceNotFoundException();
    }

    @Override
    public Page<PostGetDTO> getAll(Integer page, Integer size) {
        var posts = repository.findAll(PageRequest.of(page, size));
        return posts.map(mapper::postToGetDTO);
    }

    @Override
    public void like(String id, HttpServletRequest httpServletRequest) {
        var createdBy = userService.getUserFromJWT(httpServletRequest.getHeader(HeaderType.AUTHORIZATION.name()));
        var post = findById(id);
        var like = post.getLikes().stream().filter(x->x.getLikedBy().equals(createdBy)).findFirst().orElse(null);
        if(like!=null)
            throw new PostAlreadyLikedException();
        else
            post.getLikes().add(new Like(createdBy));
        save(post);
    }

    @Override
    public void save(Post post) {
        repository.save(post);
    }

    @Override
    public PostSaveDTO save(PostSaveDTO postSaveDTO, HttpServletRequest httpServletRequest) {
        var createdBy = userService.getUserFromJWT(httpServletRequest.getHeader(HeaderType.AUTHORIZATION.name()));
        var post = mapper.convertOneDim(postSaveDTO, Post.class);
        post.setCreatedBy(createdBy);
        repository.save(post);
        postSaveDTO.setId(post.getId());
        return postSaveDTO;
    }

    @Override
    public Post findById(String id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
