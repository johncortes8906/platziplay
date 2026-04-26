package platzi.play.util;

import platzi.play.content.Content;
import platzi.play.content.Documentary;
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

    public static final String FILE_PATH = "src/content.txt";
    private static final String DELIMITER = "|";
    private static final int MOVIE_LENGTH = 5;
    private static final int DOCUMENTARY_LENGTH = 6;
    private static final String MOVIE_TYPE = "Movie";

    public static List<Content> readContent() {
        List<Content> contents = new ArrayList<>();

        try {
            Path path = Paths.get(FILE_PATH);
            List<String> fileContents = Files.readAllLines(path);
            fileContents.forEach(content -> {
                String [] data = content.split("\\" + DELIMITER);
                int contentLength = data[0].trim().equals(MOVIE_TYPE) ? MOVIE_LENGTH : DOCUMENTARY_LENGTH;
                if (data.length == contentLength) {
                    String title = data[1].trim();
                    int length = Integer.parseInt(data[2].trim());
                    Genre genre = Genre.valueOf(data[3].toUpperCase().trim());
                    double rating = data[4].isBlank() ? 0 : Double.parseDouble(data[4].trim());
                    contents.add(new Content(title, length, genre, rating));
                }
            });

        } catch (IOException e) {
            out.println("Error: " + e.getMessage());
        }

        return contents;
    }

    public static void writeContent(Content content) {
        try {
            Path path = Paths.get(FILE_PATH);

            String contentString = String.join(DELIMITER,
                    content.getTitle(),
                    String.valueOf(content.getLength()),
                    String.valueOf(content.getGenre()),
                    String.valueOf(content.getRating())
            );

            if (content instanceof Documentary documentary) {
                contentString = "Documentary" + DELIMITER + contentString + DELIMITER + documentary.getNarrow();
            } else {
                contentString = "Movie" + DELIMITER + contentString;
            }

            if (Files.exists(path)) {
                String existingContent = Files.readString(path);

                if (existingContent.contains(contentString)) {
                    out.println("Content already exists." + contentString);
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
