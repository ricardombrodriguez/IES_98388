package com.moviedb.app.movies;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

    private long id;
    private String title;
    private long year;
 
    public Movie(String title, long year) {
        this.title = title;
        this.year = year;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        public long getId() {
        return id;
    }

    public void setId(long id) {
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
    public long getYear() {
        return year;
    }
    public void setYear(long year) {
        this.year = year;
    }
 
    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", year=" + year + "]";
    }
 
}
