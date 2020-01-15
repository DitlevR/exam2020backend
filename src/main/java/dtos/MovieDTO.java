/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Movie;

/**
 *
 * @author Rumle
 */
public class MovieDTO {
    private String title;
    private int year;
    private double votes;
    private String directors_name;
    private String actors;
    private String genres;
    
    private MovieDTO(Movie m) {
        this.title = m.getTitle();
        this.year = m.getYear();
        this.votes = m.getVotes();
        this.directors_name = m.getDirectors().toString();
        this.actors = m.getActors().toString();
        this.genres = m.getGenres().toString();
    }
    
    
}
