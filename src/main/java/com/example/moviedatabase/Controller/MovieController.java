package com.example.moviedatabase.Controller;

import com.example.moviedatabase.Model.Movie;
import com.example.moviedatabase.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    /*
GET /movies/add (form to add a movie)
POST /movies/add (submit new movie)
GET /movies/edit/{id} (form to edit movie)
POST /movies/edit/{id} (update movie)
POST /movies/delete/{id} (delete movie)
GET /movies/search (search movies by name)

     */

    @Autowired
    MovieService movieService;

    //to view all the movies collection
    @GetMapping("/main")
    public String listMovies(Model model) {
        List<Movie> movies = movieService.getMovieList();
        model.addAttribute("movies", movies);
        return "Movies"; // Returns movies.html (Thymeleaf template)
    }

    //to view a single movie
    @GetMapping("/movies/{id}")
    public String viewMovie(@PathVariable int id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "movieDetails";
    }

    //form to add a movie
    @GetMapping("/add")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie()); // Empty movie object for the form
        return "addMovie";
    }

    //to submit the movie
    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/Movies"; // Redirect to movie list
    }

    //to edit a movie
    @GetMapping("/edit/{id}")
    public String showEditMovieForm(@PathVariable int id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "editMovie";
    }

    //to submit the edited movie
    @PostMapping("/edit/{id}")
    public String updateMovie(@ModelAttribute Movie movie) {
        movieService.updateMovie(movie);
        return "redirect:/Movies"; // Redirect to movie list
    }


    //to delete a movie
    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return "redirect:/Movies"; // Redirect to movie list
    }

    //to search movie by name only
    @GetMapping("/search")
    public String searchMovies(@RequestParam String name, Model model) {
        List<Movie> movies = movieService.searchMoviesByName(name);
        model.addAttribute("movies", movies);
        return  "redirect:/Movies"; // Returns updated movie list page
    }

}
