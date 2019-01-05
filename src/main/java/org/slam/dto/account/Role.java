package org.slam.dto.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class Role {
	
	private Long id;
	private String name;
	
	public Role(String name) {
		this.name = name;
	}
	
}
