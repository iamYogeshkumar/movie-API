package com.movieflix.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movieflix.dto.MovieDto;
import com.movieflix.service.MovieService;

import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@PostMapping("/add-movie")
	public ResponseEntity<?> addMovieHandler(@RequestPart MultipartFile file,@RequestPart String movieDto) throws IOException{
		ObjectMapper objMapper = new ObjectMapper();
		MovieDto movieDtoObj = objMapper.readValue(movieDto, MovieDto.class);
		MovieDto response = movieService.addMovie(movieDtoObj, file);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/{movieId}")
	public ResponseEntity<?> getMovieHandler(@PathVariable Integer movieId){
		MovieDto movieDto = movieService.getMovie(movieId);
		
		return ResponseEntity.ok(movieDto);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllMovie(){
		return ResponseEntity.ok(movieService.getAllMovies());
	}
	
	@PutMapping("/update/{movieId}")
	public ResponseEntity<?> updateMovieHandler(@PathVariable Integer movieId, @RequestPart MultipartFile file, String movieDtoObj) throws IOException{
		MovieDto movieDto = new ObjectMapper().readValue(movieDtoObj,MovieDto.class);
		movieDto.setMovieId(movieId);
		
		
		return ResponseEntity.ok(movieService.updateMovie(movieDto, file));
	}
	
	@DeleteMapping("/delete/{movieId}")
	public ResponseEntity<?> deleteMovieHandler(@PathVariable Integer movieId) throws IOException{
		
		return ResponseEntity.ok(movieService.deleteMovie(movieId));
	}
	
	
}
