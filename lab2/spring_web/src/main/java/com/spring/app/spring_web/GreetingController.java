package com.spring.app.spring_web;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    // This URL Path needs to differ from the /welcome path we specified in the last exercise.
    @GetMapping("/greeting")
    public Greeting getGreeting(@RequestParam(value="name", defaultValue="dear friend!") String name) {
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }

}
