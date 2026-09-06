package com.bharath.movieservice.service;

import com.bharath.movieservice.dto.MovieRequest;
import com.bharath.movieservice.dto.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    MovieResponse addMovie(MovieRequest request, MultipartFile poster) throws IOException;
    MovieResponse updateMovie(MovieRequest request, Long id, MultipartFile poster) throws IOException;
    MovieResponse getMovieById(Long id);
    List<MovieResponse> getAllMovies();
    String deleteMovie(Long id);
    Page<MovieResponse> searchMovie(String title, int page, int size);

}
