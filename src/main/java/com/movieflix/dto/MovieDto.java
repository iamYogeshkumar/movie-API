package com.movieflix.dto;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Integer movieId;
	
	
	@NotBlank(message = "please provide movie's title" )    // app level validation
	private String title;
	
	@NotBlank(message = "please provide movie's director")
	private String director;
	
	@NotBlank(message = "please provide movie's studio")
	private String studio;
	
	
	private Set<String> movieCast; //list of actor & actress
	
	
	private Integer releaseYear;
	
	@NotBlank(message = "please provide movie's poster !")
	private String poster;  //movie img name
	
	@NotBlank(message = "please provide  poster url !")
	private String posterUrl;
}
