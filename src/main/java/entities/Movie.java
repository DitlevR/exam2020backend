/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Rumle
 */
@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int year;
    private double votes;

    @JoinTable(name = "movie_directors", joinColumns = {
        @JoinColumn(name = "movie_id", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "director_id", referencedColumnName = "ID")})
    @ManyToMany
    private List<Director> directors = new ArrayList();

    @JoinTable(name = "movie_actors", joinColumns = {
        @JoinColumn(name = "movie_id", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "actor_id", referencedColumnName = "ID")})
    @ManyToMany
    private List<Actor> actors = new ArrayList();

    @JoinTable(name = "movie_genres", joinColumns = {
        @JoinColumn(name = "movie_id", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "genre_id", referencedColumnName = "ID")})
    @ManyToMany
    private List<Genre> genres = new ArrayList();

    public Movie() {
    }

    public Movie(String title, int year, double votes, List<Director> directors, List<Actor> actors, List<Genre> genres) {
        this.title = title;
        this.year = year;
        this.votes = votes;
        this.directors = directors;
        this.actors = actors;
        this.genres = genres;
    }

    public Movie(String title, int year, double votes) {
        this.title = title;
        this.year = year;
        this.votes = votes;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public void addDirectorToMovie(Director d) {
        this.directors.add(d);
    }
    
    public void addActorToMovie(Actor a) {
        this.actors.add(a);
    }
    
    public void addGenresToMovie(Genre g) {
        this.genres.add(g);
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "entities.Movie[ id=" + id + " ]";
    }

}
