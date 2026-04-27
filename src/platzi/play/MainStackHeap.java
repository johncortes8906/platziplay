package platzi.play;

import platzi.play.content.Genre;
import platzi.play.content.Content;
import platzi.play.content.Movie;

public class MainStackHeap {

    public static void main(String[] args) {
        Content animatedContent = new Movie("The Lion King", 135, Genre.ANIMATED, 4.9);
        Content fantasyContent = new Movie("Harry Potter", 122, Genre.FANTASY, 4.8);

        System.out.println("animated movie is: " +animatedContent.getTitle());
        System.out.println("Fantasy movie is: " +fantasyContent.getTitle());

        animatedContent = fantasyContent;

        fantasyContent.setTitle("The Hobbit");
        System.out.println("animated movie is: " +animatedContent.getTitle());
        System.out.println("Fantasy movie is: " +fantasyContent.getTitle());
    }
}
