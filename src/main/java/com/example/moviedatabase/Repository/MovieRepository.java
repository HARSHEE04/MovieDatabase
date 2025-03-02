package com.example.moviedatabase.Repository;

import com.example.moviedatabase.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByNameContainingIgnoreCase(String name);


}
