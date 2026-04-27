package platzi.play.content;

import java.time.LocalDate;

public abstract class Content {

    private String title;
    private String description;
    private int length;
    private int year;
    private Genre genre;
    private int yearLaunched;
    private double rating;
    private boolean available;
    private LocalDate releaseDate;

    public Content(String title, int length, Genre genre) {
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.available = true;
    }

    public Content(String title, int length, Genre genre, double rating) {
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.rating = rating;
    }

    public abstract void play();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getYearLaunched() {
        return yearLaunched;
    }

    public void setYearLaunched(int yearLaunched) {
        this.yearLaunched = yearLaunched;
    }

    public double getRating() {
        return rating;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTechnicalSheet() {
        return "Title: " + this.title +
                "\nGenre: " + this.genre +
                "\nRating: " + this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public String movieOverview() {
        String movieOverview = "Average";
        if (this.rating > 4 && this.rating < 5) {
            movieOverview = "Good";
        } else if (this.rating == 5) {
            movieOverview = "Excellent";
        }

        return movieOverview;
    }
}
