package platzi.play.util;

import platzi.play.content.Genre;
import platzi.play.content.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class FileUtils {

    public static List<Movie> readContent(String pathFile) {
        List<Movie> movies = new ArrayList<>();

        try {
            List<String> fileMovies = Files.readAllLines(Paths.get(pathFile));
            fileMovies.forEach(movie -> {
                String [] data = movie.split("\\|");
                if (data.length == 4) {
                    String title = data[0].trim();
                    int length = Integer.parseInt(data[1].trim());
                    Genre genre = Genre.valueOf(data[2].toUpperCase().trim());
                    double rating = data[3].isBlank() ? 0 : Double.parseDouble(data[3].trim());
                    //platform.addMovie(new Movie(title, length, genre, rating));
                    movies.add(new Movie(title, length, genre, rating));
                }
            });

        } catch (IOException e) {
            out.println("Error: " + e.getMessage());
        }

        return movies;
    }
}
