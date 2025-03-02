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
        if (movieRepository.count() == 0) { // Ensure movies are only added once



            Movie movie1 = new Movie(21,"Inception", Movie.Genre.drama, Movie.Language.english, 148, 12.99);
            Movie movie2 = new Movie(31,"Amélie", Movie.Genre.comedy, Movie.Language.french, 122, 10.99);
            Movie movie3 = new Movie(41,"Parasite", Movie.Genre.drama, Movie.Language.other, 132, 11.99);

            movieRepository.save(movie1);
            movieRepository.save(movie2);
            movieRepository.save(movie3);

            System.out.println("Preloaded movies added to the database!");
        }
    }
}
