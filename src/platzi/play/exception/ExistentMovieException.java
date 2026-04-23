package platzi.play.exception;

public class ExistentMovieException extends RuntimeException {

    public ExistentMovieException(String title) {
        super("The movie " + title + " already exist in the catalog");
    }
}
