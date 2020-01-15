package facades;

import entities.Actor;
import entities.Director;
import entities.Genre;
import entities.Movie;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private Movie m1, m2;
    private Actor a1, a2;
    private Director d1, d2;
    private Genre g1, g2;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MovieFacade.getFacadeExample(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        m1 = new Movie("Testmovie1", 2000, 10.0);
        m2 = new Movie("Testmovie2", 1999, 9.0);

        a1 = new Actor("Testactor1", "test1");
        a2 = new Actor("Testactor2", "test2");

        d1 = new Director("Testinstructor1", "testd1");
        d2 = new Director("Testinstructor2", "testd2");

        g1 = new Genre("drama");
        g2 = new Genre("thriller");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();

            m1.addDirectorToMovie(d1);
            m1.addActorToMovie(a1);
            m1.addActorToMovie(a2);
            m1.addGenresToMovie(g1);

            m2.addDirectorToMovie(d1);
            m2.addDirectorToMovie(d2);

            m2.addGenresToMovie(g1);
            m2.addGenresToMovie(g2);

            em.persist(m1);
            em.persist(m2);

            em.persist(d1);
            em.persist(d2);

            em.persist(a1);
            em.persist(a2);

            em.persist(g1);
            em.persist(g2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetMovies() {
        Assertions.assertEquals(facade.getAllMovies().size(), 2);
    }
    
    @Disabled
    @Test
    public void testGetActors() {
        //Assertions.assertTrue(facade.getAllMovies().);
    }
}
