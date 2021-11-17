package com.moviedb.app.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

    Movie findMovieByTitle(String title);
    Movie findMovieById(int id);
    String deleteMovieById(int id);

}
