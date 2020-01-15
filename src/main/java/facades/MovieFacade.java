package facades;

import entities.Movie;
import entities.RenameMe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Movie> getAllMovies() {
        EntityManager em = getEntityManager();
        List<Movie> movies = new ArrayList<>();
        try {
            
            movies = em.createQuery("select m from Movie m", Movie.class).getResultList();
            
            return movies;
        } finally {
            em.close();
        }

    }

    public List<Movie> searchForMovie(String title) {
        EntityManager em = getEntityManager();
        List<Movie> movies = getAllMovies();
        List<Movie> result = new ArrayList();
        for (Movie m : movies) {
            if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(m);
            }

        }
        return result;

    }
    
    public Movie addMovie(String title, int year, double votes) {
        EntityManager em = getEntityManager();
        Movie m = new Movie(title, year, votes);
        try {
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
        return m;
        } finally {
            em.close();
        }
    }

}
