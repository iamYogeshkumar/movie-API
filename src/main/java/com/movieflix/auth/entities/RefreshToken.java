package com.movieflix.auth.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  tokenid;
	
	@Column(nullable = false,length = 800)
	@NotBlank(message = "please enter refresh Token value !")
	private String refreshToken;
	
	@Column(nullable = false)
	private Instant expirationTime;
	
	@OneToOne
	private User user;
}
