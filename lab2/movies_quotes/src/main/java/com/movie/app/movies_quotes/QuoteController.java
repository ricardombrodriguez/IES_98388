package com.movie.app.movies_quotes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
    
    @GetMapping("/api/quote")
    public Quote getQuote() {
        return new Quote();
    }

}
