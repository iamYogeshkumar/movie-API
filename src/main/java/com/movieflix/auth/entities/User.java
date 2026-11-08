package com.movieflix.auth.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class User  implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@NotBlank(message = "The name field can't be blank")
	private String name;
	
	@NotBlank(message = "The username field can't be blank")
	@Column(unique = true)
	private String username;
	
	@NotBlank(message = "The email field can't be blank")
	@Column(unique = true)
	@Email(message = "Please enter email in proper format")
	private String email;
	
	@NotBlank(message = "The password field can't be blank")
	@Size(min = 5, message = "The password must have at least 5 character")
	private String password;
	
	@OneToOne(mappedBy = "user")
	private RefreshToken  refreshToken;
	
	private boolean isEnable=true;
	
	private boolean isAccountNonExpired=true;
	
	private boolean isAccountNonLocked=true;
	
	private boolean isCredentialsNonExpired=true;
	
	
	@Enumerated(EnumType.STRING)
	private UserRole userRole;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isEnabled() {
		
		return isEnabled();
	}


	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}


	
}
