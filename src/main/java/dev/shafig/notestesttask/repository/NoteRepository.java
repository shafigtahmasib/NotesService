package dev.shafig.notestesttask.repository;

import dev.shafig.notestesttask.model.document.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
}
