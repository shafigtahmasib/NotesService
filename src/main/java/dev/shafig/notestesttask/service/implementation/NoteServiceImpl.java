package dev.shafig.notestesttask.service.implementation;

import dev.shafig.notestesttask.exception.ResourceNotFoundException;
import dev.shafig.notestesttask.model.document.Like;
import dev.shafig.notestesttask.model.document.Note;
import dev.shafig.notestesttask.model.dto.NoteGetDTO;
import dev.shafig.notestesttask.model.dto.NoteSaveDTO;
import dev.shafig.notestesttask.model.enumerated.HeaderType;
import dev.shafig.notestesttask.repository.NoteRepository;
import dev.shafig.notestesttask.service.abstraction.NoteService;
import dev.shafig.notestesttask.service.abstraction.UserService;
import dev.shafig.notestesttask.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;
    private final UserService userService;
    private final Mapper mapper;

    @Override
    public void likeOrUnlike(String id, HttpServletRequest httpServletRequest) {
        var createdBy = userService.getUserFromJWT(httpServletRequest.getHeader(HeaderType.AUTHORIZATION.name()));
        var note = findById(id);
        var like = note.getLikes().stream().filter(x->x.getLikedBy().equals(createdBy)).findFirst().orElse(null);
        if(like!=null)
            note.getLikes().remove(like);
        else
            note.getLikes().add(new Like(createdBy));
        save(note);
    }

    @Override
    public void save(Note note) {
        repository.save(note);
    }

    @Override
    public Page<NoteGetDTO> getAll(Integer page, Integer size) {
        var notes = repository.findAll(PageRequest
                .of(page, size, Sort.by("createdAt").descending()));
        return notes.map(mapper::noteToGetDTO);
    }

    @Override
    public void deleteById(String id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public NoteSaveDTO save(NoteSaveDTO noteSaveDTO, HttpServletRequest httpServletRequest) {
        var createdBy = userService.getUserFromJWT(httpServletRequest.getHeader(HeaderType.AUTHORIZATION.name()));
        var note = mapper.convertOneDim(noteSaveDTO, Note.class);
        note.setCreatedBy(createdBy);
        repository.save(note);
        noteSaveDTO.setId(note.getId());
        return noteSaveDTO;
    }

    @Override
    public Note findById(String id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
