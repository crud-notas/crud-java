package com.esig.crudesig.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esig.crudesig.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
