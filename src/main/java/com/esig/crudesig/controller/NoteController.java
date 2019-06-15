package com.esig.crudesig.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esig.crudesig.entity.Note;
import com.esig.crudesig.service.NoteService;

@RestController
public class NoteController {

	@Autowired
	private NoteService noteService;
	
	@GetMapping("/note") @CrossOrigin
	public List<Note> getNotes() {
		return noteService.listNotes();
	}
	
	@PostMapping("/note") @CrossOrigin
	public ResponseEntity<Note> save(@RequestBody Note note) {
		return new ResponseEntity<Note>(noteService.saveNote(note), HttpStatus.CREATED);
	}

	@GetMapping("/note/{id}") @CrossOrigin
	public ResponseEntity<Note> getNote(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Note>(noteService.getNote(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/note/{id}") @CrossOrigin 
	public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
		try {
			noteService.deleteNote(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/note") @CrossOrigin
	public ResponseEntity<Note> updateNote(@RequestBody Note note) {
		try {
			return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
