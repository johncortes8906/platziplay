package platzi.play;

import platzi.play.content.Genre;
import platzi.play.content.Movie;
import platzi.play.content.MovieSummarize;
import platzi.play.exception.ExistentMovieException;
import platzi.play.platform.Platform;
import platzi.play.platform.User;
import platzi.play.util.ScannerUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String VERSION = "1.0.0";
    public static final String PLATFORM = "Platzi play 🍿";
    public static final int SHOW_ALL_MOVIES = 2;
    public static final int SHOW_MOST_POPULAR_MOVIES = 5;
    public static final int DROP_MOVIE = 8;
    public static final int EXIT_OPTION = 9;
    public static final int EXIT_SYSTEM = 0;

    public static int moviesLength;

    public static void main(String[] args) {
        Platform platform = new Platform(PLATFORM);
        loadPlatform(platform);
        moviesLength = platform.getTotalMoviesLength();
        System.out.println("Hello, Welcome to " + PLATFORM + " v" + VERSION + " we have now " + moviesLength + " of entertainment");


        //Menu of actions
        while (true) {
            String menuMessage = "1. Add content\n" +
                    "2. Show content available\n" +
                    "3. Search by title\n" +
                    "4. Search by Genre\n" +
                    "5. Get most popular Movies\n" +
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
                        System.out.println(e.getMessage());
                    }

                }
                case SHOW_ALL_MOVIES -> {
                    List<MovieSummarize> moviesSummarize = platform.getMoviesSummarize();
                    System.out.println(PLATFORM + " has currently " + moviesSummarize.size() + " movies available. Movies are the following:");
                    moviesSummarize.forEach(movieSummarize -> System.out.println(movieSummarize.toString()));
                }
                case 3 -> {
                    String movieTitle = ScannerUtils.captureText("Type the movie title to search");
                    Movie movie = platform.searchMovieByTitle(movieTitle);
                    if (movie != null) {
                        System.out.println(movieTitle + " is currently available in " + PLATFORM);
                        System.out.println(movie.getTechnicalSheet());
                    } else {
                        System.out.println(movieTitle + " is currently not available in " + PLATFORM);
                    }
                }
                case 4 -> {
                    Genre movieGenre = ScannerUtils.captureGenre("Type the movies genre for search");
                    List<Movie> moviesByGenre = platform.searchMoviesByGenre(movieGenre);
                    System.out.println("Total movies found: " +moviesByGenre.size());
                    moviesByGenre.forEach(movie -> System.out.println(movie.getTitle()));
                }
                case SHOW_MOST_POPULAR_MOVIES -> {
                    List<Movie> mostPopularMovies = platform.sortMostPopularMovies();
                    mostPopularMovies.forEach(movie ->
                            System.out.println(movie.getTitle() + " rating: " + movie.getRating()
                            )
                    );
                }
                case DROP_MOVIE -> {
                    String movieTitle = ScannerUtils.captureText("Type the movie title to remove");
                    boolean isMovieRemoved = platform.removeMovieByTitle(movieTitle);
                    if (isMovieRemoved) {
                        System.out.println(movieTitle + " has been removed successfully :)");
                    }
                }
                case EXIT_OPTION -> {
                    System.out.println("thank you, we hope to see you soon :)");
                    System.exit(EXIT_SYSTEM);
                }
            }
        }
    }

    public static void loadPlatform(Platform platform) {

        platform.addMovie(new Movie("Gladiator", 180, Genre.DRAMA, 4.8));
        platform.addMovie(new Movie("The Lion King", 118, Genre.ANIMATED, 4.2));
        platform.addMovie(new Movie("Titanic", 242, Genre.DRAMA, 4.9));
        platform.addMovie(new Movie("Avatar", 234, Genre.SCI_FI, 4.7));
        platform.addMovie(new Movie("Toy Story", 122, Genre.ANIMATED, 4.2));
        platform.addMovie(new Movie("The Godfather", 187, Genre.DRAMA, 5));
        platform.addMovie(new Movie("The Matrix", 200, Genre.SCI_FI, 5));
        platform.addMovie(new Movie("The Mask", 200, Genre.COMEDY, 4.8));
        platform.addMovie(new Movie("The Schindler's List", 188, Genre.DRAMA, 4.8));
        platform.addMovie(new Movie("There's Something about Marry", 122, Genre.COMEDY, 4.5));
        platform.addMovie(new Movie("Liar Liar", 98, Genre.COMEDY, 4.6));
        platform.addMovie(new Movie("Aladin", 120, Genre.ANIMATED, 4.9));
        platform.addMovie(new Movie("The Exorcist", 120, Genre.TERROR, 4.9));
        platform.addMovie(new Movie("The Lord of the rings", 240, Genre.FANTASY, 5));
    }
}
