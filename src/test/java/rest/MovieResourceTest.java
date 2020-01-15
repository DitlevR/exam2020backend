package rest;

import entities.Actor;
import entities.Director;
import entities.Genre;
import entities.Movie;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private Movie m1, m2;
    private Actor a1, a2;
    private Director d1, d2;
    private Genre g1, g2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
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

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/movie").then().statusCode(200);
    }

    
    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("movie").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("this is a movie database"));
    }

    @Test
    public void testNumberOfMovies() {
        given().contentType("application/json").get("movie/all")
                .then().assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
                
    }
}
