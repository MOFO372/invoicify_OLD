package com.libertymutual.goforcode.invoicify.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="invoicify_user")
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	//cascade = when I do something with this object, it will flow down to associated objects; if I update a user, it will flow down and update all
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "user", cascade=CascadeType.ALL) 
	private List<UserRole> roles;

	public User() {} //create an empty constructor so javax persistence can create a new user
	
	public User(String username, String password, String roleName) {
		this.username = username; 
		this.password = password; 
		
		roles = new ArrayList<UserRole>();
		roles.add(new UserRole(roleName, this)); 
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//list is acting as an interface, array list is storing the list items
//		List<String> roleNames = new ArrayList<String>();
//		for (UserRole role : roles) {
//			roleNames.add(role.getName()); 
//		}
		
		
		//this is a lambda, takes an input and converts it 
		List<String> roleNames = roles.stream()
			.map(userRole -> "ROLE_" + userRole.getName()) //find a user role and return a user role name, turns each user role into a string; need ROLE_ since spring formats role names this way
			.collect(Collectors.toList()); //adds the string user role to the list
		String authorityString = String.join(",", roleNames);
		AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString); //takes comma separated list from previous line
		
		return AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString); 
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	} 

}
