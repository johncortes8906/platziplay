package platzi.play;

import platzi.play.content.Genre;
import platzi.play.content.Content;

public class MainStackHeap {

    public static void main(String[] args) {
        Content animatedContent = new Content("The Lion King", 135, Genre.ANIMATED);
        Content fantasyContent = new Content("Harry Potter", 122, Genre.FANTASY);

        System.out.println("animated movie is: " +animatedContent.getTitle());
        System.out.println("Fantasy movie is: " +fantasyContent.getTitle());

        animatedContent = fantasyContent;

        fantasyContent.setTitle("The Hobbit");
        System.out.println("animated movie is: " +animatedContent.getTitle());
        System.out.println("Fantasy movie is: " +fantasyContent.getTitle());
    }
}
