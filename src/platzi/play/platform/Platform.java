package platzi.play.platform;

import platzi.play.content.Genre;
import platzi.play.content.Movie;
import platzi.play.content.MovieSummarize;
import platzi.play.exception.ExistentMovieException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Platform {

     private String name;
     private List<Movie> movies;
     private static final int TOP_TEN = 10;

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
        Movie movieToSearch = this.searchMovieByTitle(movie.getTitle());
        if (movieToSearch != null) {
            throw new ExistentMovieException(movie.getTitle());
        }
         this.movies.add(movie);
     }

     public List<MovieSummarize> getMoviesSummarize() {
        return this.movies.stream()
                .map(movie -> new MovieSummarize(movie.getTitle(), movie.getLength(), movie.getGenre()))
                .toList();
     }

     public void removeMovie(Movie movie) {
        this.movies.remove(movie);
     }

     public List<String> showMovieTitles() {

        return this.movies.stream().map(Movie::getTitle).toList();
     }

     public int getTotalMoviesLength() {

        return this.movies.stream().mapToInt(Movie::getLength).sum();
     }

     public Movie searchMovieByTitle(String title) {

         return this.movies.stream()
                 .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
     }

     public List<Movie> sortMostPopularMovies() {

        return this.movies.stream()
                .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                .limit(TOP_TEN)
                .toList();
     }

     public List<Movie> searchMoviesByGenre(Genre genre) {

        return this.movies.stream()
                .filter(movie -> movie.getGenre().equals(genre))
                .toList();
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
