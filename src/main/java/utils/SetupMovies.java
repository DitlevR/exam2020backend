/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Actor;
import entities.Director;
import entities.Genre;
import entities.Movie;
import entities.Role;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Rumle
 */
public class SetupMovies {

    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
//        EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
//                "pu",
//                "jdbc:mysql://206.81.20.15:3306/moviedb",
//                "ditlev",
//                "Harley27",
//                EMF_Creator.Strategy.CREATE);
        EntityManager em = emf.createEntityManager();
        Movie m1 = new Movie("The Lord of the Rings: The Fellowship of the Ring", 2001, 8.8);
        Movie m2 = new Movie("Catch Me If You Can", 2002, 8.1);
        Movie m3 = new Movie("The Irishman", 2019, 8.1);
        Movie m4 = new Movie("Psycho", 1960, 8.5);
        Movie m5 = new Movie("The Shining", 1980, 8.4);

        Director d1 = new Director("Peter Jackson", "Peter Jackson was born as an only child in a small coast-side town in New Zealand in 1961");
        Director d2 = new Director("Steven Spielberg", "One of the most influential personalities in the history of cinema");
        Director d3 = new Director("Martin Scorsese", "Martin Charles Scorsese was born on November 17, 1942 in Queens, New York City");
        Director d4 = new Director("Alfred Hitchcock", "Alfred Joseph Hitchcock was born in Leytonstone, Essex, England");
        Director d5 = new Director("Stanley Kubrick", "Stanley Kubrick was born in Manhattan, New York City");

        Actor a1 = new Actor("Viggo Mortensen", "Since his screen debut as a young Amish farmer in Peter Weir's Witness (1985), Viggo Mortensen's career has been marked by a steady string of well-rounded performances.");
        Actor a2 = new Actor("Leonardo DiCaprio", "Few actors in the world have had a career quite as diverse as Leonardo DiCaprio's");
        Actor a3 = new Actor("Al Pacino", "Alfredo James \"Al\" 'Pacino established himself as a film actor during one of cinema's most vibrant decades, the 1970s, and has become an enduring and iconic figure in the world of American movies");
        Actor a4 = new Actor("Anthony Perkins", "Anthony Perkins was born April 4, 1932 in New York City to Janet Esselstyn (Rane) and Osgood Perkins");
        Actor a5 = new Actor("Jack Nicholson", "Jack Nicholson, an American actor, producer, director and screenwriter, is a three-time Academy Award winner and twelve-time nominee");
        Actor a6 = new Actor("Elijah Wood", "Elijah Wood is an American actor best known for portraying Frodo Baggins in Peter Jackson's blockbuster Lord of the Rings film trilogy");
        Actor a7 = new Actor("Robert De Niro", "One of the greatest actors of all time, Robert De Niro was born on August 17, 1943 in Manhattan");
        Actor a8 = new Actor("Tom Hanks", "Thomas Jeffrey Hanks was born in Concord, California, to Janet Marylyn (Frager), a hospital worker, and Amos Mefford Hanks, an itinerant cook");

        Genre g1 = new Genre("Thriller");
        Genre g2 = new Genre("Adventure");
        Genre g3 = new Genre("Action");
        Genre g4 = new Genre("Fantasy");
        Genre g5 = new Genre("Drama");
        Genre g6 = new Genre("Crime");

        User user = new User("user", "");
        User admin = new User("admin", "");
        User both = new User("user_admin", "");

        try {

            em.getTransaction().begin();

            m1.addDirectorToMovie(d1);
            m1.addActorToMovie(a1);
            m1.addActorToMovie(a6);
            m1.addGenresToMovie(g4);
            m1.addGenresToMovie(g2);

            m2.addDirectorToMovie(d2);
            m2.addActorToMovie(a2);
            m2.addActorToMovie(a8);
            m2.addGenresToMovie(g3);
            m2.addGenresToMovie(g6);

            m3.addDirectorToMovie(d3);
            m3.addActorToMovie(a3);
            m3.addActorToMovie(a7);
            m3.addGenresToMovie(g5);
            m3.addGenresToMovie(g6);

            m4.addDirectorToMovie(d4);
            m4.addActorToMovie(a4);
            m4.addGenresToMovie(g1);

            m5.addDirectorToMovie(d5);
            m5.addActorToMovie(a5);
            m5.addGenresToMovie(g1);

            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);
            em.persist(m5);

            em.persist(d1);
            em.persist(d2);
            em.persist(d3);
            em.persist(d4);
            em.persist(d5);

            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            em.persist(a4);
            em.persist(a5);
            em.persist(a6);
            em.persist(a7);
            em.persist(a8);

            em.persist(g1);
            em.persist(g2);
            em.persist(g3);
            em.persist(g4);
            em.persist(g5);
            em.persist(g6);

            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            user.addRole(userRole);
            admin.addRole(adminRole);
            both.addRole(userRole);
            both.addRole(adminRole);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(admin);
            em.persist(both);

            em.getTransaction().commit();

        } finally {
            em.close();
        }

    }
}
