package platzi.play.platform;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class User {

    private String fullName;
    private String genre;
    private LocalDate dateOfBirth;
    private String email;
    private LocalDate createdDate;

    public User(
            String fullName,
            String genre,
            String dateOfBirth,
            String email
    ) {
        this.fullName = fullName;
        this.genre = genre;

        //Set date attribute according to the received String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);

        this.email = email;
        this.createdDate = LocalDate.now();
    }

    public String watchMovie(String movieTitle) {
        return this.fullName + " is watching ... " + movieTitle;
    }
}
