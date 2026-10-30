package com.movieflix.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movieflix.dto.MovieDto;
import com.movieflix.entity.Movie;
import com.movieflix.exception.EmptyFileException;
import com.movieflix.exception.FileExistsException;
import com.movieflix.exception.MovieNotFoundException;
import com.movieflix.repo.MovieRepository;
import com.movieflix.service.FileService;
import com.movieflix.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	private final MovieRepository movieRepository;
	
	private final FileService fileService;

	public MovieServiceImpl(MovieRepository movieRepository, FileService fileService) {
		this.movieRepository = movieRepository;
		this.fileService = fileService;
	}
	
	@Value("${project.poster}")
	private String path;
	
	@Value("${base.url}")
	private String baseUrl;

	@Override
	public MovieDto addMovie(MovieDto moviedto, MultipartFile file) throws IOException {
		// 1. upload the file
		if(file.isEmpty()) {
			throw new EmptyFileException("File is empty ! Please send another file ");
		}
//		String  cpath=path+file.getOriginalFilename();
//		System.out.println(cpath);
		if(Files.exists(Paths.get(path  +file.getOriginalFilename()))) {
			throw new FileExistsException("file already exists ! Please enter another file name");
		}
		
		String uploadedFileName = fileService.uploadFile(path, file);
		
		
		//2. set the value of field poster as  filename
	    moviedto.setPoster(uploadedFileName);
		
		//3. map dto to entity
	    Movie saveMovie=new Movie();
		BeanUtils.copyProperties(moviedto, saveMovie);
		
	    
		//4. save the movie obj
		Movie movieEntity = movieRepository.save(saveMovie);
		
		// 5 generate the poster url
		String posterUrl=baseUrl + "/file/" + uploadedFileName;
		
		//6. return the movie obj
		MovieDto movieResponse = new MovieDto();
		BeanUtils.copyProperties(movieEntity, movieResponse);
		movieResponse.setPosterUrl(posterUrl);
		return movieResponse;
	}

	@Override
	public MovieDto getMovie(Integer movieId) {
		//1. check data in db and if exist fetch the data of given ID
		Movie movie = movieRepository.findById(movieId).orElseThrow(()-> new MovieNotFoundException("movie not found with id = "+movieId));
		//2. generate poster url
		String posterUrl=baseUrl + "/file/" + movie.getPoster();
		
		//3. map to movieDto
		MovieDto movieDto = new MovieDto();
		BeanUtils.copyProperties(movie, movieDto);
		movieDto.setPosterUrl(posterUrl);
		return movieDto;
	}

	@Override
	public List<MovieDto> getAllMovies() {
		List<Movie> list = movieRepository.findAll();
		List<MovieDto> listOfMovieDto=new ArrayList<>();
		for(Movie m: list) {
			MovieDto movieDto = new MovieDto();
			BeanUtils.copyProperties(m, movieDto);
			String posterUrl=baseUrl + "/file/" + m.getPoster();
			movieDto.setPosterUrl(posterUrl);
			listOfMovieDto.add(movieDto);
		}
		
		return listOfMovieDto;
	}

	@Override
	public MovieDto updateMovie(MovieDto movieDto, MultipartFile file) throws IOException {
		// 1. check movie obj is exist with given id
		Movie movie = movieRepository.findById(movieDto.getMovieId()).orElseThrow(()->new  MovieNotFoundException("movie not found with id = "+movieDto.getMovieId()));
		
		// if file is null do nothing
		// if is not null, then delete existing file associated with the record and upload the new file
		String filename=movie.getPoster();
		if( file!=null &&!file.isEmpty() || file.getSize()!=0) {
			Files.deleteIfExists(Paths.get(path + File.separator + filename));
			fileService.uploadFile(path, file);
			// 3. set  movie poster value according to step 2
			movie.setPoster(file.getOriginalFilename());
			movieDto.setPoster(file.getOriginalFilename());
		}
		
//		movieDto.setPosterUrl(baseUrl + File.separator + movie.getPoster());
		
		
		// 4. map it to movie obj
		BeanUtils.copyProperties(movieDto, movie);
		
		// save movie obj return movie obj
		Movie updateMovie = movieRepository.save(movie);
		// 5.generate  posterurl
		movieDto.setPosterUrl(baseUrl + "/file/" + movie.getPoster());

		// 6. map to moviedto and return it
		return movieDto;
	}

	@Override
	public String deleteMovie(Integer movieId) throws IOException {
		// 1. check movie obj is exist in db
		Movie mv=movieRepository.findById(movieId).orElseThrow(()-> new MovieNotFoundException("movie not found with id = "+movieId));
		//2. delete the file associated with this obj
		Files.deleteIfExists(Paths.get(path + File.separator + mv.getPoster()));
		// 3 delete the movie obj
		movieRepository.deleteById(movieId);
		return "movie deleted with id= "+movieId;
	}
	

}
