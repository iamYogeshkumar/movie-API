package com.movieflix.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.movieflix.dto.MovieDto;

public interface MovieService {

	public MovieDto addMovie(MovieDto moviedto,MultipartFile file ) throws IOException;
	
	public MovieDto getMovie(Integer movieId);
	
	public List<MovieDto> getAllMovies();
	
	public MovieDto updateMovie(MovieDto movieDto, MultipartFile file) throws IOException;
	
	public String deleteMovie(Integer movieId) throws IOException ;
	
}
