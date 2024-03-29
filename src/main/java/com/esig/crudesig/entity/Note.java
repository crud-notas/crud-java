package com.esig.crudesig.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Note {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@NotBlank
	@Column
    private String name;

	@Column
    private boolean status;
    
}
