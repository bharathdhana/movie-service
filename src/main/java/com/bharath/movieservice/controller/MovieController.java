package com.bharath.movieservice.controller;

import com.bharath.movieservice.dto.MovieRequest;
import com.bharath.movieservice.dto.MovieResponse;
import com.bharath.movieservice.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieResponse> addMovie(@RequestPart("movie") @Valid MovieRequest request, @RequestPart("poster") MultipartFile poster) throws IOException{
        MovieResponse response = movieService.addMovie(request, poster);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieResponse> updateMovie(@Valid @RequestPart("movie") MovieRequest request, @PathVariable Long id, @RequestPart(value = "poster", required = false) @Valid MultipartFile poster) throws IOException {
        MovieResponse response = movieService.updateMovie(request, id, poster);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        MovieResponse response = movieService.getMovieById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> response = movieService.getAllMovies();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        String response = movieService.deleteMovie(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MovieResponse>> searchMovie(@RequestParam String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        Page<MovieResponse> response = movieService.searchMovie(title, page, size);
        return ResponseEntity.ok(response);
    }
}
