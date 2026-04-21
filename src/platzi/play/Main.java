package platzi.play;

import platzi.play.content.Movie;
import platzi.play.platform.Platform;
import platzi.play.platform.User;
import platzi.play.util.ScannerUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static final String VERSION = "1.0.0";
    public static final String PLATFORM = "Platzi play 🍿";
    public static final int EXIT_OPTION = 5;

    public static void main(String[] args) {
        System.out.println("Hello, Welcome to " + PLATFORM + " v" + VERSION);
        Platform platform = new Platform(PLATFORM);
        loadPlatform(platform);

        //Menu of actions
        while (true) {
            String menuMessage = "1. Add content\n" +
                    "2. Show content available\n" +
                    "3. Search by title\n" +
                    "4. Drop movie\n" +
                    "5. Exit\n" +
                    "Type and press Enter to choose your option below";
            int option = ScannerUtils.captureInt(menuMessage);
            switch (option) {
                case 1 -> {
                    String title = ScannerUtils.captureText("Name of the movie");
                    String genre = ScannerUtils.captureText("Genre of the movie");
                    int length = ScannerUtils.captureInt("Length of the movie");
                    double rating = ScannerUtils.captureDouble("Rating of the movie");
                    Movie movie = new Movie(title, length, genre, rating);
                    platform.addMovie(movie);
                }
                case 2 -> {
                    System.out.println("Current movies available in " + PLATFORM + " are the following ones:");
                    platform.showMovieTitles();
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
                    String movieTitle = ScannerUtils.captureText("Type the movie title to remove");
                    boolean isMovieRemoved = platform.removeMovieByTitle(movieTitle);
                    if (isMovieRemoved) {
                        System.out.println(movieTitle + " has been removed successfully :)");
                    }
                }
                case EXIT_OPTION -> {
                    System.out.println("thank you, we hope to see you soon :)");
                    System.exit(0);
                }
            }
        }
    }

    public static void loadPlatform(Platform platform) {

        Movie defaultMovie = new Movie("Gladiator", 180, "Drama");
        platform.addMovie(defaultMovie);
        Movie secondMovie = new Movie("The Lion King", 118, "Animated", 4.9);
        platform.addMovie(secondMovie);
        Movie thirdMovie = new Movie("Titanic", 242, "Drama");
        platform.addMovie(thirdMovie);
    }
}
