package com.example.moviedatabase.Service;

import com.example.moviedatabase.Model.Movie;
import com.example.moviedatabase.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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


    //updateMovie(Movie movie)


    //deleteMovie
    public void deleteMovie(int movieId){
        movieRepository.deleteById(movieId);
    }
    //searchMoviesByName( string name)


}
