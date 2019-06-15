package com.esig.crudesig;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esig.crudesig.entity.Note;
import com.esig.crudesig.exception.NoteNameEmptyException;
import com.esig.crudesig.service.NoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudesigApplicationTests {

	@Autowired
	private NoteService noteService;
	
	@Test
	public void saveNoteTest() {
		Note note = noteService.saveNote(Note.builder().name("test note").build());
		assertTrue(note.getId() > 0);
		noteService.deleteNote(note.getId());
	}

	@Test(expected = NoteNameEmptyException.class)
	public void saveNoteNameEmptyTest() {
	 	noteService.saveNote(Note.builder().name("").build());
	}

	@Test(expected = NoteNameEmptyException.class)
	public void saveNoteNameNullTest() {
		noteService.saveNote(new Note());
	}

	@Test(expected = RuntimeException.class)
	public void saveNoteNullTest() {
		noteService.saveNote(null);
	}

}
