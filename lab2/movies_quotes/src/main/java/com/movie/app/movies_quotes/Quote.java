package com.movie.app.movies_quotes;

import java.util.ArrayList;
import java.util.Random;

public class Quote {

    private final int show;
    private final String quote;
    private final String role;
    private final Boolean adult_lang;
    
    public Quote() {

        ArrayList<Quote> quotes = new ArrayList<Quote>();
        quotes.add(new Quote("Its easier to ask for forgiveness than permission.","Holden Ford",1,false));
        quotes.add(new Quote("Women are born with this little hole between their legs, which every man on earth just wants to stick something into. And they are weaker than men, so they learn strategies. They deploy their minds and their sex, and they intuitively learn to humiliate.","Edmund Kemper",1,true));
        quotes.add(new Quote("You want truffles, you gotta get in the dirt with the pigs.","Holden Ford",1,false));

        quotes.add(new Quote("People out here, its like they dont even know the outside world exists. Might as well be living on the fucking moon.","Rust Cohle",2,true));
        quotes.add(new Quote("I dont sleep. I just dream.","Rust Cohle",2,false));
        quotes.add(new Quote("Its Thursday and its past noon. Thursday is one of my days off. On my days off I start drinking at noon. You dont get to interrupt that.","Rust Cohle",2,false));
        
        quotes.add(new Quote("The game is rigged, but you cannot lose if you do not play.","Marla Daniels",3,false));
        quotes.add(new Quote("Wanna know what kills more police than bullets and liquor? Boredom. They just can’t handle that shit. You keep it boring, String. You keep it dead fucking boring.","Proposition Joe",3,true));
        quotes.add(new Quote("We are Building Something, Here, Detective, We are Building It From Scratch. All The Pieces Matter.","Lester Freamon",3,false));

        quotes.add(new Quote("Its weird. They always travel in groups of five. These programmers, there is always a tall skinny white guy, a short skinny Asian guy, fat guy with a ponytail, some guy with crazy facial hair and then an East Indian guy. Its like they trade guys until they all have the right group.","Gavin Belson",4,false));
        quotes.add(new Quote("So, with some proper funding, we should be able to get a functioning beta in time for CES. And if you do not fund us, you are a fucking slut. Ah. What's that smell? Is that a fart? You Are you a farter? Now, can I have a pastry? Or are you guys gonna eat em all? 'Cause you are gonna get fat.","Richard Hendricks",4,true));

        Random rand = new Random();
        Quote quote = quotes.get(rand.nextInt(quotes.size()));
        this.show = quote.show;
        this.quote = quote.quote;
        this.role = quote.role;
        this.adult_lang = quote.adult_lang;

    }

    public Quote(int show) {

        ArrayList<Quote> quotes = new ArrayList<Quote>();
        quotes.add(new Quote("Its easier to ask for forgiveness than permission.","Holden Ford",1,false));
        quotes.add(new Quote("Women are born with this little hole between their legs, which every man on earth just wants to stick something into. And they are weaker than men, so they learn strategies. They deploy their minds and their sex, and they intuitively learn to humiliate.","Edmund Kemper",1,true));
        quotes.add(new Quote("You want truffles, you gotta get in the dirt with the pigs.","Holden Ford",1,false));

        quotes.add(new Quote("People out here, its like they dont even know the outside world exists. Might as well be living on the fucking moon.","Rust Cohle",2,true));
        quotes.add(new Quote("I dont sleep. I just dream.","Rust Cohle",2,false));
        quotes.add(new Quote("Its Thursday and its past noon. Thursday is one of my days off. On my days off I start drinking at noon. You dont get to interrupt that.","Rust Cohle",2,false));
        
        quotes.add(new Quote("The game is rigged, but you cannot lose if you do not play.","Marla Daniels",3,false));
        quotes.add(new Quote("Wanna know what kills more police than bullets and liquor? Boredom. They just can’t handle that shit. You keep it boring, String. You keep it dead fucking boring.","Proposition Joe",3,true));
        quotes.add(new Quote("We are Building Something, Here, Detective, We are Building It From Scratch. All The Pieces Matter.","Lester Freamon",3,false));

        quotes.add(new Quote("Its weird. They always travel in groups of five. These programmers, there is always a tall skinny white guy, a short skinny Asian guy, fat guy with a ponytail, some guy with crazy facial hair and then an East Indian guy. Its like they trade guys until they all have the right group.","Gavin Belson",4,false));
        quotes.add(new Quote("So, with some proper funding, we should be able to get a functioning beta in time for CES. And if you do not fund us, you are a fucking slut. Ah. What's that smell? Is that a fart? You Are you a farter? Now, can I have a pastry? Or are you guys gonna eat em all? 'Cause you are gonna get fat.","Richard Hendricks",4,true));

        ArrayList<Quote> quotes_from_show = new ArrayList<Quote>();
        for (Quote quote : quotes) {
            if (quote.getShow() == show) {
                quotes_from_show.add(quote);
            }
        }

        Random rand = new Random();
        Quote quote = quotes_from_show.get(rand.nextInt(quotes_from_show.size()));
        this.show = quote.show;
        this.quote = quote.quote;
        this.role = quote.role;
        this.adult_lang = quote.adult_lang;

    }

    public Quote(String quote, String role, int show, Boolean adult_lang) {
        this.show = show;
        this.quote = quote;
        this.role = role;
        this.adult_lang = adult_lang;
    }

    public int getShow() {
        return show;
    }

    public String getQuote() {
        return quote;
    }

    public String getRole() {
        return role;
    }

    public Boolean getAdult_lang() {
        return adult_lang;
    }

}
