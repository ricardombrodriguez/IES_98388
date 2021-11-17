package com.moviedb.app.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>{

    Quote findQuoteByText(String text);
    Quote findQuoteById(int id);
    String deleteQuoteById(int id);
    Quote getRandomQuote();
    Quote getRandomQuoteFromMovie(int id);

}
