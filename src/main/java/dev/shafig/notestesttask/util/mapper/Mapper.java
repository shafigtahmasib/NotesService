package dev.shafig.notestesttask.util.mapper;

import dev.shafig.notestesttask.model.document.Note;
import dev.shafig.notestesttask.model.document.Post;
import dev.shafig.notestesttask.model.dto.NoteGetDTO;
import dev.shafig.notestesttask.model.dto.PostGetDTO;
import dev.shafig.notestesttask.model.dto.PostSaveDTO;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private ModelMapper mapper;

    public Mapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <O, I> O convertOneDim(I data, Class<O> entityClass) {
        return mapper.map(data, entityClass);
    }

    public NoteGetDTO noteToGetDTO(Note note) {
        var getDTO = mapper.map(note, NoteGetDTO.class);
        getDTO.setLikeCount(note.getLikes().size());
        return getDTO;
    }

    public PostGetDTO postToGetDTO(Post post) {
        var getDTO = mapper.map(post, PostGetDTO.class);
        getDTO.setLikeCount(post.getLikes().size());
        return getDTO;
    }

}
