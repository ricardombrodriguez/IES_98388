package com.moviedb.app.movies;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    private int id;
    private String title;
    private int year;

    @OneToMany(cascade = CascadeType.ALL)  
    @JoinColumn(name="movie_id")  
    @OrderColumn(name="type")  
    private List<Quote> quotes;

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
 
    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Quote> getQuotes(){
        return this.quotes;
    }
 
    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", year=" + year + "]";
    }

    public Movie orElse(Object object) {
        return null;
    }
 
}
