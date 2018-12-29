package org.slam.entity.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@EntityListeners(value = AuditingEntityListener.class)
public class Account {
	
	@Id
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@OneToMany
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	public Account(String username, String password, String name, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.roles = roles;
	}
	
}
