package com.movie.app.movies_quotes;

import java.util.ArrayList;
import java.util.Random;

public class Show {

    private final String name;
    private final String slug;

    public Show() {

        ArrayList<Show> shows = getAllShows();
        Random rand = new Random();
        Show show = shows.get(rand.nextInt(shows.size()));
        this.name = show.name;
        this.slug = show.slug;

    }

    public Show(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public static ArrayList<Show> getAllShows() {
        ArrayList<Show> shows = new ArrayList<Show>();
        shows.add(new Show("Mindhunter","mindhunter"));
        shows.add(new Show("True Detective","true-detective"));
        shows.add(new Show("The Wire","the-wire"));
        shows.add(new Show("Sillicon Valley","sillicon-valley"));
        shows.add(new Show("The Office","the-office"));
        return shows;
    }

    public String getName() {
        return this.name;
    }

    public String getSlug() {
        return this.slug;
    }

}
