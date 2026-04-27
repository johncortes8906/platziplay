package platzi.play;

import platzi.play.content.*;
import platzi.play.exception.ExistentContentException;
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
    public static final int ADD_CONTENT = 1;
    public static final int SHOW_ALL_MOVIES = 2;
    public static final int SHOW_MOST_POPULAR_MOVIES = 5;
    public  static final int PLAY_MOVIE = 6;
    public static final int  SEARCH_BY_CONTENT_TYPE = 7;
    public static final int DROP_MOVIE = 8;
    public static final int EXIT_OPTION = 9;
    public static final int EXIT_SYSTEM = 0;
    public static final int ADD_MOVIE = 1;
    public static final int ADD_DOCUMENTARY = 2;

    public static int moviesLength;

    public static void main(String[] args) {
        Platform platform = new Platform(PLATFORM);
        out.println("Hello, Welcome to " + PLATFORM + " v" + VERSION);
        loadPlatform(platform);
        moviesLength = platform.getTotalContentsLength();
        out.println("We offer you " + platform.getContent().size() + " different titles with " +
                platform.getTotalContentsLength() + " min of entertainment.");
        platform.getPromotableContent()
                .forEach(promotableContent -> out.println(promotableContent.promoteContent()
        ));

        //Menu of actions
        while (true) {
            String menuMessage = "1. Add content\n" +
                    "2. Show content available\n" +
                    "3. Search by title\n" +
                    "4. Search by Genre\n" +
                    "5. Get most popular Contents\n" +
                    "6. Play movie\n" +
                    "7. Search by Content type\n" +
                    "8. Drop movie\n" +
                    "9. Exit\n" +
                    "Type and press Enter to choose your option below";
            int option = ScannerUtils.captureInt(menuMessage);
            switch (option) {
                case ADD_CONTENT -> {
                    int contentType = ScannerUtils.captureInt("Type the content type you want to add:\n" +
                            "1. Movie\n" +
                            "2. Documentary");
                    String title = ScannerUtils.captureText("Name of the movie");
                    Genre genre = Genre.DOCUMENTARY;
                    int length = ScannerUtils.captureInt("Length of the movie");
                    double rating = ScannerUtils.captureDouble("Rating of the movie");
                    try {
                        if (contentType == ADD_MOVIE) {
                            genre = ScannerUtils.captureGenre("Genre of the movie");
                            Movie content = new Movie(title, length, genre, rating);
                            platform.addContent(content);
                        } else if (contentType == ADD_DOCUMENTARY) {
                            String narrow = ScannerUtils.captureText("Type the Arrow's name");
                            Documentary documentary = new Documentary(title, length, genre, rating, narrow);
                            platform.addContent(documentary);
                        }
                    } catch (ExistentContentException e) {
                        out.println(e.getMessage());
                    }

                }
                case SHOW_ALL_MOVIES -> {
                    List<ContentSummarize> moviesSummarize = platform.getContentsSummarize();
                    out.println(PLATFORM + " has currently " + moviesSummarize.size() + " movies available. Contents are the following:");
                    moviesSummarize.forEach(movieSummarize -> out.println(movieSummarize.toString()));
                }
                case 3 -> {
                    String movieTitle = ScannerUtils.captureText("Type the movie title to search");
                    Content movie = platform.searchContentByTitle(movieTitle);
                    if (movie != null) {
                        out.println(movieTitle + " is currently available in " + PLATFORM);
                        out.println(movie.getTechnicalSheet());
                    } else {
                        out.println(movieTitle + " is currently not available in " + PLATFORM);
                    }
                }
                case 4 -> {
                    Genre movieGenre = ScannerUtils.captureGenre("Type the movies genre for search");
                    List<Content> moviesByGenre = platform.searchContentsByGenre(movieGenre);
                    out.println("Total movies found: " +moviesByGenre.size());
                    moviesByGenre.forEach(movie -> out.println(movie.getTitle()));
                }
                case SHOW_MOST_POPULAR_MOVIES -> {
                    List<Content> mostPopularContents = platform.sortMostPopularContents();
                    mostPopularContents.forEach(movie ->
                            out.println(movie.getTitle() + " rating: " + movie.getRating()
                            )
                    );
                }
                case PLAY_MOVIE -> {
                    String movieTitle = ScannerUtils.captureText("Type the movie title that you want to watch");
                    Content movie = platform.searchContentByTitle(movieTitle);
                    if (movie != null) {
                        platform.play(movie);
                    }
                }
                case SEARCH_BY_CONTENT_TYPE -> {
                    int contentType = ScannerUtils.captureInt("Type the content type you want to search:\n" +
                        "1. Movie\n" +
                        "2. Documentary");
                    if (contentType == ADD_MOVIE) {
                        List<Movie> movies = platform.getMovies();
                        movies.forEach(movie -> out.println(movie.getTechnicalSheet() + "\n"));
                    }
                }
                case DROP_MOVIE -> {
                    String movieTitle = ScannerUtils.captureText("Type the movie title to remove");
                    boolean isContentRemoved = platform.removeContentByTitle(movieTitle);
                    if (isContentRemoved) {
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

        List<Content> contents = FileUtils.readContent();
        platform.setContent(contents);
    }
}
