package platzi.play.util;

import platzi.play.content.Content;
import platzi.play.content.Genre;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class FileUtils {

    public static final String FILE_PATH = "src/movies.txt";
    private static final String DELIMITIER = "|";

    public static List<Content> readContent(String pathFile) {
        List<Content> movies = new ArrayList<>();

        try {
            Path path = Paths.get(FILE_PATH);
            List<String> fileContents = Files.readAllLines(path);
            fileContents.forEach(movie -> {
                String [] data = movie.split("\\" + DELIMITIER);
                if (data.length == 4) {
                    String title = data[0].trim();
                    int length = Integer.parseInt(data[1].trim());
                    Genre genre = Genre.valueOf(data[2].toUpperCase().trim());
                    double rating = data[3].isBlank() ? 0 : Double.parseDouble(data[3].trim());
                    movies.add(new Content(title, length, genre, rating));
                }
            });

        } catch (IOException e) {
            out.println("Error: " + e.getMessage());
        }

        return movies;
    }

    public static void writeContent(Content movie) {
        try {
            Path path = Paths.get(FILE_PATH);

            String contentString = String.join(DELIMITIER,
                    movie.getTitle(),
                    String.valueOf(movie.getLength()),
                    String.valueOf(movie.getGenre()),
                    String.valueOf(movie.getRating())
            );

            if (Files.exists(path)) {
                String existingContent = Files.readString(path);

                if (existingContent.contains(contentString)) {
                    out.println("Movie already exists.");
                    return;
                }

                Files.writeString(path,
                        System.lineSeparator() + contentString,
                        StandardOpenOption.APPEND);
            } else {
                Files.writeString(path,
                        contentString,
                        StandardOpenOption.CREATE);
            }

        } catch (IOException e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
