package edu.miu.cs.cs544.examples;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor @Getter @Setter @ToString
public class AuditData {

	@Column(name = "rowguid")
	private String guid;
	
	@Column(name = "ModifiedDate")
	private LocalDate modifiedDate;
	
}
