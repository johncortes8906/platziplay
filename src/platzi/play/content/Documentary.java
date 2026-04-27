package platzi.play.content;

public class Documentary extends Content implements PromotableContent {

    private String narrow;
    public Documentary(String title, int length, Genre genre) {
        super(title, length, genre);
    }

    public Documentary(String title, int length, Genre genre, double rating, String narrow) {
        super(title, length, genre, rating);
        this.narrow = narrow;
    }

    public String getNarrow() {
        return narrow;
    }

    public void setNarrow(String narrow) {
        this.narrow = narrow;
    }

    @Override
    public void play() {
        System.out.println("Playing movie ... " + getTitle() + " narrow by: " + getNarrow());
    }

    @Override
    public String promoteContent() {
        return "Find this documentary :) " + getTitle() + ", narrow by: " + getNarrow() +
                ". Available now in Platzi play";
    }


}
