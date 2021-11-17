package com.moviedb.app.movies;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotes")
public class Quote {

    private long id;
    private String text;
    private Movie movie;
 
    public Quote() {
  
    }
 
    public Quote(String text, Movie movie) {
         this.text = text;
         this.movie = movie;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
 
    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
 
    @Column(name = "movie", nullable = false)
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Quote [id=" + id + ", text=" + text + ", movie=" + movie.getTitle() + "]";
    }
 
}
