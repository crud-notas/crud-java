package com.esig.crudesig.menagerBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esig.crudesig.entity.Note;
import com.esig.crudesig.service.NoteService;

import lombok.Getter;

@Scope(value = "session")
@Component(value = "noteBean")
@ELBeanName(value = "noteBean")
@Join(path = "/", to = "/note.jsf")
public class NoteBean {
 
	@Autowired
    private NoteService noteService;
    
	@Getter
	private List<Note> notes;

	@Getter
	private Note note = new Note();
    
	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadData() {
		notes = noteService.listNotes();
	}

	public String save() {
        noteService.saveNote(note);
        loadData();
        note = new Note();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Note saved", note.getName()));
        return null;
    }

	public String deleteNote(Note note) {
		noteService.deleteNote(note.getId());
		loadData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Note deleted", note.getName()));
		return null;
	}

    public void onCellEdit(CellEditEvent event) {
        Object newValue = event.getNewValue();
        if(newValue != null && !newValue.equals(event.getOldValue())) {
        	noteService.updateNote(notes.get(event.getRowIndex()));
        	FacesMessage msg = new FacesMessage("Note Edited", (String) newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
