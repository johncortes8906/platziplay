package platzi.play.content;

public class Movie extends Content {


    public Movie(String title, int length, Genre genre, double rating) {
        super(title, length, genre, rating);
    }

    @Override
    public void play() {
        System.out.println("Playing movie ... " + getTitle());
    }
}
