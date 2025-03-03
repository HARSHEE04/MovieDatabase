package com.example.moviedatabase.Controller;

import com.example.moviedatabase.Model.Movie;
import com.example.moviedatabase.Service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addMovie(@Valid @ModelAttribute Movie movie, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Validation Errors: " + result.getAllErrors());
            model.addAttribute("movie", movie);
            return "addMovie"; // Reload form with errors
        }

        movieService.addMovie(movie);
        return "redirect:/main"; // Success
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
    public String updateMovie(@PathVariable int id, @ModelAttribute Movie movie) {
        // Fetch existing movie from database
        Movie existingMovie = movieService.getMovieById(id);

        // Update fields with new values
        existingMovie.setName(movie.getName());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setLanguage(movie.getLanguage());
        existingMovie.setDuration(movie.getDuration());
        existingMovie.setPrice(movie.getPrice());

        // Save updated movie
        movieService.updateMovie(existingMovie);
        return "redirect:/main"; // Redirect to movie list
    }



    //to delete a movie
    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return "redirect:/main"; // Redirect to movie list
    }

    //to search movie by name only
    @GetMapping("/search")
    public String searchMovies(@RequestParam String name, Model model) {
        List<Movie> movies = movieService.searchMoviesByName(name);

        if (movies.isEmpty()) {
            model.addAttribute("error", "No movies found");
            return "redirect:/main"; // Redirects back if no movie is found
        }

        model.addAttribute("movie", movies.get(0)); // Assuming you want to display only the first match
        return "movieDetails"; // Displays the details page
    }


}
