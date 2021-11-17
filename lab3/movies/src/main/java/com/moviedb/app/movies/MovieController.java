package com.moviedb.app.movies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;
    private QuoteRepository quoteRepository;

    @GetMapping("/shows")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/quote")
    public Quote getRandomQuote() {
        return quoteRepository.findAll();
    }

    @GetMapping("/quotes/{id}")
    public Quote getRandomQuoteFromMovie() {
        return quoteRepository.findAll();
    }

}