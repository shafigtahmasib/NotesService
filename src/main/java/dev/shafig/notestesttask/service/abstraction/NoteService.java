package dev.shafig.notestesttask.service.abstraction;

import dev.shafig.notestesttask.model.document.Note;
import dev.shafig.notestesttask.model.dto.NoteGetDTO;
import dev.shafig.notestesttask.model.dto.NoteSaveDTO;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

public interface NoteService {
    NoteSaveDTO save(NoteSaveDTO noteSaveDTO, HttpServletRequest httpServletRequest);

    Note findById(String id);

    void deleteById(String id);

    Page<NoteGetDTO> getAll(Integer page, Integer size);

    void likeOrUnlike(String id, HttpServletRequest httpServletRequest);

    void save(Note note);

}
