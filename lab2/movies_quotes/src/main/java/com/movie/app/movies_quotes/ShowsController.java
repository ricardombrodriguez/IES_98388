package com.movie.app.movies_quotes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
public class ShowsController {

    @GetMapping("/api/shows")
    public ArrayList<Show> getShow() {
        return Show.getAllShows();
    }

}