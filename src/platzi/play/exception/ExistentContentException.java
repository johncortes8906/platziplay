package platzi.play.exception;

public class ExistentContentException extends RuntimeException {

    public ExistentContentException(String title) {
        super("The movie " + title + " already exist in the catalog");
    }
}
