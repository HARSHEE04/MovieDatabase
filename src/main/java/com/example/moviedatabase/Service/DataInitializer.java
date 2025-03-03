package com.example.moviedatabase.Service;

import com.example.moviedatabase.Model.Movie;
import com.example.moviedatabase.Repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        movieRepository.deleteAll(); // Clears all existing movies

        Movie movie1 = new Movie("Inception", Movie.Genre.DRAMA, Movie.Language.ENGLISH, 148, 12.99);
        Movie movie2 = new Movie("Amélie", Movie.Genre.COMEDY, Movie.Language.FRENCH, 122, 10.99);
        Movie movie3 = new Movie("Parasite", Movie.Genre.DRAMA, Movie.Language.OTHER, 132, 11.99);

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);

        System.out.println("Preloaded movies added to the database!");
    }

}

