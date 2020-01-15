/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Actor;
import entities.Director;
import entities.Genre;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rumle
 */
public class MovieDTO {
    private String title;
    private int year;
    private double votes;
    private List<String> directors_name = new ArrayList();
    private List<String> actors = new ArrayList();
    private List<String> genres = new ArrayList();
    
    public MovieDTO(Movie m) {
        this.title = m.getTitle();
        this.year = m.getYear();
        this.votes = m.getVotes();
        for(Director d : m.getDirectors()) {
            this.directors_name.add(d.getName());
        }
        
        for(Actor a : m.getActors()) {
        this.actors.add(a.getName());
        }
        for(Genre g : m.getGenres()) {
        this.genres.add(g.getGenreName());
        }
        
    }
    public MovieDTO(String title, int year, double votes) {
        this.title = title;
        this.year = year;
        this.votes = votes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getVotes() {
        return votes;
    }

    public void setVotes(double votes) {
        this.votes = votes;
    }

    public List<String> getDirectors_name() {
        return directors_name;
    }

    public void setDirectors_name(List<String> directors_name) {
        this.directors_name = directors_name;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    
    
    
    
}
