package com.moviedb.app.movies;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quote")
public class Quote {

    private int id;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;

 
    public Quote() {
  
    }
 
    public Quote(String text, Movie movie) {
         this.text = text;
         this.movie = movie;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
 
    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    public Movie getMovie() {
        return this.movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Quote [id=" + id + ", text=" + text + ", movie=" + movie.getTitle() + "]";
    }

    public Quote orElse(Object object) {
        return null;
    }
 
}
