package com.example.moviedatabase.Model;

//validation done using validation dependency
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="Movies")
public class Movie {

    public enum Genre{
        comedy, drama, action
    }

    public enum Language{
        english, french, other
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotBlank(message = "Title cannot be blank")
    private String name;


    private Genre genre;


    private Language language;

    @Min(10)
    @Max(300)
    private int duration;

    @Min(1)
    private double price;

    public Movie() {
        this.genre=Genre.drama;
        this.language=Language.english;
    }

    public Movie(String name, Genre genre, Language language, int duration, double price) {
        this.name = name;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Language getLanguage() {
        return language;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name != null && name.matches("^[A-Za-z0-9 ]+$")) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Title contains invalid characters.");
        }
    }


    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "Movie Name: " + name + "Genre: " + genre
                + "Language: " + language + "Duration: " +
                duration + "Price: " + price;
    }

}
