package com.tutorial.keycloakbackend.controller;

import com.tutorial.keycloakbackend.dto.ResponseMessage;
import com.tutorial.keycloakbackend.model.MovieEntity;
import com.tutorial.keycloakbackend.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

    MovieRepository movieRepository;

    public MovieController(MovieRepository repository) {
        this.movieRepository = repository;
    }

    @GetMapping("/list")
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<List<MovieEntity>> list(){
        List<MovieEntity> movies = movieRepository.findAll();
        return new ResponseEntity(movies, HttpStatus.OK);
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/detail/{id}")
    public ResponseEntity<MovieEntity> detail(@PathVariable("id") Long id){
        Optional<MovieEntity> movie = movieRepository.findById(id);
        if(movie.isPresent()) {
            return new ResponseEntity(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> create(@RequestBody MovieEntity movie){
        movieRepository.save(movie);
        return new ResponseEntity(new ResponseMessage("Créé"), HttpStatus.CREATED);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody MovieEntity movie){
        Optional<MovieEntity> movieData = movieRepository.findById(id);
        if (movieData.isPresent()) {
            MovieEntity _movie = movieData.get();
            _movie.setTitle(movie.getTitle());
            _movie.setOverview(movie.getOverview());
            _movie.setRelease_date(movie.getRelease_date());
            movieRepository.save(_movie);
            return new ResponseEntity(new ResponseMessage("Actualisé"), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<MovieEntity> movie = movieRepository.findById(id);
        if(movie.isPresent()) {
            movieRepository.delete(movie.get());
            return new ResponseEntity(new ResponseMessage("Supprimé"), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}

