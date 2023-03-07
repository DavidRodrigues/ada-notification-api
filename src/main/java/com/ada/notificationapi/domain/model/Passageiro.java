package com.ada.notificationapi.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table("passageiro")
public class Passageiro {

	@EqualsAndHashCode.Include
	@Id
	private Long id;
	
	private String nome;
	
    private String email;
    
    private String telefone;
    
}
