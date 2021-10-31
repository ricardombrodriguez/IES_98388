package com.spring.app.spring_web;

//Representação da classe do objeto que vai ser resultado da mensagem JSON

public class Greeting {
    
    private final long id;
    private final String name;

    public Greeting(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
