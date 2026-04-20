package platzi.play;

import platzi.play.content.Movie;
import platzi.play.platform.User;
import platzi.play.util.ScannerUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static final String VERSION = "1.0.0";
    public static final String PLATFORM = "Platzi play 🍿";

    public static void main(String[] args) {
        System.out.println("Hello, Welcome to " + PLATFORM + " v" + VERSION);

        String title = ScannerUtils.captureText("Name of the movie");
        String genre = ScannerUtils.captureText("Genre of the movie");
        int length = ScannerUtils.captureInt("Length of the movie");
        double rating = ScannerUtils.captureDouble("Rating of the movie");
        Movie movie = new Movie(title, length, genre, rating);
        movie.setDescription("A magical journey to save the middle Earth from Evil");
        movie.setReleaseDate(LocalDate.of(2001, 12, 04));
        System.out.println("the technical sheet of the movie is the following:");
        System.out.println(movie.getTechnicalSheet());
        User user = new User("John Cortés", "Male", "1989-06-13", "johncortes8906@gmail.com");
        System.out.println("Rounded rating of the movie: " + (int)movie.getRating());
        System.out.println(user.watchMovie(movie.getTitle()));
    }
}
