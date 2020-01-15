/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rumle
 */
public class MovieDTOs {
    private List<MovieDTO> moviesdtos = new ArrayList<>();
    
    public MovieDTOs(List<Movie> movies) {
        for(Movie m : movies) {
            moviesdtos.add(new MovieDTO(m));
        }
        
    }
    
}
