package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import dtos.MovieDTOs;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("movie")
public class RenameMeResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final MovieFacade FACADE = MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"this is a movie database\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovies() {
        List<Movie> movies = FACADE.getAllMovies();
        List<MovieDTO> moviedto = new ArrayList();
        for (Movie m : movies) {
            moviedto.add(new MovieDTO(m));
        }
        return GSON.toJson(moviedto);

    }

    @Path("/search/{search}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String searchMovie (@PathParam("search") String title) {
        List<Movie> movies = FACADE.searchForMovie(title);
        List<MovieDTO> moviedto = new ArrayList();
        for (Movie m : movies) {
            moviedto.add(new MovieDTO(m));
        }
        return GSON.toJson(moviedto);
    }


}
