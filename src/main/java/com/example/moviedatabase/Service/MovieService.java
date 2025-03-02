package com.example.moviedatabase.Service;

import com.example.moviedatabase.Model.Movie;
import com.example.moviedatabase.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    //allows us to interact with the movies collection
    List<Movie> movieList = new ArrayList<>();

    @Autowired
    MovieRepository movieRepository;


    //addMovie
    public boolean addMovie(Movie movie){
        movieRepository.save(movie);
        return true;
    }

    //getAllMovies
    public List<Movie> getMovieList() {
        return movieRepository.findAll();
    }

    //getMovieByID( int ID) //returns the movie object
    public Movie getMovieById(int id){
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null); //returns the movie or else returns null
    }


    //updateMovie(Movie movie)
    public Movie updateMovie(Movie movie) {
        Optional<Movie> existingMovie = movieRepository.findById(movie.getId());

        if (existingMovie.isPresent()) {
            Movie updatedMovie = existingMovie.get();
            updatedMovie.setName(movie.getName());
            updatedMovie.setGenre(movie.getGenre());
            updatedMovie.setLanguage(movie.getLanguage());
            updatedMovie.setDuration(movie.getDuration());
            updatedMovie.setPrice(movie.getPrice());

            return movieRepository.save(updatedMovie); // Save the updated movie
        } else {
            throw new RuntimeException("Movie not found with id: " + movie.getId());
        }
    }


    //deleteMovie
    public void deleteMovie(int movieId){
        movieRepository.deleteById(movieId);
    }
    //searchMoviesByName( string name)
    public List<Movie> searchMoviesByName(String name) {
        return movieRepository.findByNameContainingIgnoreCase(name);
    }


}
