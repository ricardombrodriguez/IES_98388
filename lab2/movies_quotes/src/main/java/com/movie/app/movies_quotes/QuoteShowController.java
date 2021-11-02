package com.movie.app.movies_quotes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteShowController {
    
    @GetMapping("/api/quotes")
    public Quote getQuote(@RequestParam(value="show", required = true) int show) {
        return new Quote(show);
    }

}
