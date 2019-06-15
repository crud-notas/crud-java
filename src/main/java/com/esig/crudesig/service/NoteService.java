package com.esig.crudesig.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esig.crudesig.entity.Note;
import com.esig.crudesig.exception.NoteNameEmptyException;
import com.esig.crudesig.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public Note getNote(Long id) {
		Optional<Note> optNote = noteRepository.findById(id);
		if (optNote.isPresent()) {
			return optNote.get();
		} 
		throw new RuntimeException();
	}
	
	public List<Note> listNotes() {
		return noteRepository.findAll();
	}
	
	public Note saveNote(Note note) {
		
		if (note == null)
			throw new RuntimeException();

		if (note.getName() == null || note.getName().length() == 0)
			throw new NoteNameEmptyException("O atributo name não pode ser vazio.");
		
		return noteRepository.save(note);
	}
	
	public void deleteNote(Long id) {
		Optional<Note> optNote = noteRepository.findById(id);
		if (optNote.isPresent()) {
			noteRepository.delete(optNote.get());
		} else {
			throw new RuntimeException();
		}
	}

    public Note updateNote(Note note) {
    	Optional<Note> optNote = noteRepository.findById(note.getId());
    	if (optNote.isPresent()) {
    		Note oldNote = optNote.get();
    		oldNote.setName(note.getName());
			return noteRepository.save(oldNote);
    	} else {
    		throw new RuntimeException();    		
    	}
    }
}
