package platzi.play.platform;

import platzi.play.content.Movie;

import java.util.ArrayList;
import java.util.List;

public class Platform {

     private String name;
     private List<Movie> movies;

    public Platform(String name) {
        this.name = name;
        this.movies = new ArrayList<Movie>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

     public void addMovie(Movie movie) {
         this.movies.add(movie);
     }

     public void removeMovie(Movie movie) {
        this.movies.remove(movie);
     }

     public void showMovieTitles() {
        for (Movie movie : movies) {
            System.out.println("Movie Title: " +movie.getTitle());
        }
     }

     public Movie searchMovieByTitle(String title) {
        for (Movie movie : this.movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }

        return null;
     }

     public boolean removeMovieByTitle(String title) {
        for (Movie movie : this.movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                this.movies.remove(movie);

                return true;
            }
        }

        return false;
     }
}
