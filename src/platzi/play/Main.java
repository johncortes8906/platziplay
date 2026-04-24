package platzi.play;

import platzi.play.content.Genre;
import platzi.play.content.Movie;
import platzi.play.content.MovieSummarize;
import platzi.play.exception.ExistentMovieException;
import platzi.play.platform.Platform;
import platzi.play.platform.User;
import platzi.play.util.FileUtils;
import platzi.play.util.ScannerUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {

    public static final String VERSION = "1.0.0";
    public static final String PLATFORM = "Platzi play 🍿";
    public static final int SHOW_ALL_MOVIES = 2;
    public static final int SHOW_MOST_POPULAR_MOVIES = 5;
    public  static final int PLAY_MOVIE = 6;
    public static final int DROP_MOVIE = 8;
    public static final int EXIT_OPTION = 9;
    public static final int EXIT_SYSTEM = 0;

    public static int moviesLength;

    public static void main(String[] args) {
        Platform platform = new Platform(PLATFORM);
        loadPlatform(platform);
        moviesLength = platform.getTotalMoviesLength();
        out.println("Hello, Welcome to " + PLATFORM + " v" + VERSION + " we have now " + moviesLength + " of entertainment");


        //Menu of actions
        while (true) {
            String menuMessage = "1. Add content\n" +
                    "2. Show content available\n" +
                    "3. Search by title\n" +
                    "4. Search by Genre\n" +
                    "5. Get most popular Movies\n" +
                    "6. Play movie\n" +
                    "8. Drop movie\n" +
                    "9. Exit\n" +
                    "Type and press Enter to choose your option below";
            int option = ScannerUtils.captureInt(menuMessage);
            switch (option) {
                case 1 -> {
                    String title = ScannerUtils.captureText("Name of the movie");
                    Genre genre = ScannerUtils.captureGenre("Genre of the movie");
                    int length = ScannerUtils.captureInt("Length of the movie");
                    double rating = ScannerUtils.captureDouble("Rating of the movie");
                    Movie movie = new Movie(title, length, genre, rating);
                    try {
                        platform.addMovie(movie);
                    } catch (ExistentMovieException e) {
                        out.println(e.getMessage());
                    }

                }
                case SHOW_ALL_MOVIES -> {
                    List<MovieSummarize> moviesSummarize = platform.getMoviesSummarize();
                    out.println(PLATFORM + " has currently " + moviesSummarize.size() + " movies available. Movies are the following:");
                    moviesSummarize.forEach(movieSummarize -> out.println(movieSummarize.toString()));
                }
                case 3 -> {
                    String movieTitle = ScannerUtils.captureText("Type the movie title to search");
                    Movie movie = platform.searchMovieByTitle(movieTitle);
                    if (movie != null) {
                        out.println(movieTitle + " is currently available in " + PLATFORM);
                        out.println(movie.getTechnicalSheet());
                    } else {
                        out.println(movieTitle + " is currently not available in " + PLATFORM);
                    }
                }
                case 4 -> {
                    Genre movieGenre = ScannerUtils.captureGenre("Type the movies genre for search");
                    List<Movie> moviesByGenre = platform.searchMoviesByGenre(movieGenre);
                    out.println("Total movies found: " +moviesByGenre.size());
                    moviesByGenre.forEach(movie -> out.println(movie.getTitle()));
                }
                case SHOW_MOST_POPULAR_MOVIES -> {
                    List<Movie> mostPopularMovies = platform.sortMostPopularMovies();
                    mostPopularMovies.forEach(movie ->
                            out.println(movie.getTitle() + " rating: " + movie.getRating()
                            )
                    );
                }
                case PLAY_MOVIE -> {
                    String movieTitle = ScannerUtils.captureText("Type the movie title that you want to watch");
                    Movie movie = platform.searchMovieByTitle(movieTitle);
                    if (movie != null) {
                        platform.play(movie);
                    }
                }
                case DROP_MOVIE -> {
                    String movieTitle = ScannerUtils.captureText("Type the movie title to remove");
                    boolean isMovieRemoved = platform.removeMovieByTitle(movieTitle);
                    if (isMovieRemoved) {
                        out.println(movieTitle + " has been removed successfully :)");
                    }
                }
                case EXIT_OPTION -> {
                    out.println("thank you, we hope to see you soon :)");
                    exit(EXIT_SYSTEM);
                }
            }
        }
    }

    public static void loadPlatform(Platform platform) {

        String pathFile = "src/movies.txt";
        List<Movie> movies = FileUtils.readContent(pathFile);
        movies.forEach(movie ->
                platform.addMovie(new Movie(movie.getTitle(), movie.getLength(), movie.getGenre(), movie.getRating())
                )
        );
    }
}
