package com.movie.app.movies_quotes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowsController {

    @GetMapping("/api/shows")
    public Show getShow() {
        return new Show();
    }

}