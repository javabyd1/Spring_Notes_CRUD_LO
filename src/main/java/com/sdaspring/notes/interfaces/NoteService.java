package com.sdaspring.notes.interfaces;

import com.sdaspring.notes.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> getAllNotes();
    Note getNoteById(Long id);
    Note createNote(Note note);
    Note updateNote(Note note);
    void deleteNote(Note note);
}
