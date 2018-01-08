package com.sdaspring.notes.controller;

import com.sdaspring.notes.interfaces.NoteService;
import com.sdaspring.notes.model.Note;
import com.sdaspring.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") Long noteId) {
        Note note = noteService.getNoteById(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(note);
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteService.createNote(note);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Note noteDetails) {
        Note note = noteService.getNoteById(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteService.updateNote(note);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteService.getNoteById(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }

        noteService.deleteNote(note);
        return ResponseEntity.ok().build();
    }
}
