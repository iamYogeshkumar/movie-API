package com.movieflix.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.movieflix.dto.MovieDto;
import com.movieflix.dto.MoviePageResponse;
import com.movieflix.entity.Movie;

public interface MovieService {

	public MovieDto addMovie(MovieDto moviedto,MultipartFile file ) throws IOException;
	
	public MovieDto getMovie(Integer movieId);
	
	public List<MovieDto> getAllMovies();
	
	public MovieDto updateMovie(MovieDto movieDto, MultipartFile file) throws IOException;
	
	public String deleteMovie(Integer movieId) throws IOException ;
	
    MoviePageResponse getAllMoviesWithPagination(Integer pageNumber,Integer pageSize);
    
    MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber,Integer pageSize, String sortBy, String dir);
	
}
