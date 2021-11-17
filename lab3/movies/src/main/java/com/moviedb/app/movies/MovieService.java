package com.moviedb.app.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;

    public Movie saveMovie(Movie Movie) {
        return repository.save(Movie);
    }

    public List<Movie> saveMovies(List<Movie> Movies) {
        return repository.saveAll(Movies);
    }

    public List<Movie> getMovies() {
        return repository.findAll();
    }

    public Movie getMovieById(int id) {
        return repository.findMovieById(id);
    }

    public Movie getMovieByTitle(String title) {
        return repository.findMovieByTitle(title);
    }

    public String deleteMovie(int id) {
        repository.deleteById(id);
        return "Movie removed !! " + id;
    }

    public Movie updateMovie(Movie Movie) {
        Movie existingMovie = repository.findMovieById(Movie.getId()).orElse(null);
        existingMovie.setTitle(Movie.getTitle());
        existingMovie.setYear(Movie.getYear());
        return repository.save(existingMovie);
    }
}