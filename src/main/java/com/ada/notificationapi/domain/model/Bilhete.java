package com.ada.notificationapi.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("bilhete")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Bilhete {

	@EqualsAndHashCode.Include
	@Id
	private Long id;
	
	@Column("numero_bilhete")
	private String numeroBilhete;
	
	@Column("passageiro_id")
    private Long passageiroId;
    
	@Column("voo_id")
    private Long vooId;

}
