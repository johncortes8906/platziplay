package platzi.play;

import platzi.play.content.Genre;
import platzi.play.content.Movie;

public class MainStackHeap {

    public static void main(String[] args) {
        Movie animatedMovie = new Movie("The Lion King", 135, Genre.ANIMATED);
        Movie fantasyMovie = new Movie("Harry Potter", 122, Genre.FANTASY);

        System.out.println("animated movie is: " +animatedMovie.getTitle());
        System.out.println("Fantasy movie is: " +fantasyMovie.getTitle());

        animatedMovie = fantasyMovie;

        fantasyMovie.setTitle("The Hobbit");
        System.out.println("animated movie is: " +animatedMovie.getTitle());
        System.out.println("Fantasy movie is: " +fantasyMovie.getTitle());
    }
}
