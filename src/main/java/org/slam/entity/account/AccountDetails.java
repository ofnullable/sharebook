package org.slam.entity.account;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDetails extends User {
	
	private static final String ROLE_PREFIX = "ROLE_";
	
	public AccountDetails(Account account) {
		super(account.getUsername(), account.getPassword(), getAuthorities(account.getRoles()));
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
		return roles.stream()
				.map( r -> new SimpleGrantedAuthority( ROLE_PREFIX + r.getName() ))
				.collect(Collectors.toList());
	}
	
}
