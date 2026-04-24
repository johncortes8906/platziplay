package platzi.play.platform;

import platzi.play.content.Genre;
import platzi.play.content.Content;
import platzi.play.content.ContentSummarize;
import platzi.play.exception.ExistentContentException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Platform {

     private String name;
     private List<Content> movies;
     private static final int TOP_TEN = 10;
     private Map<Content, Integer> views;

    public Platform(String name) {
        this.name = name;
        this.movies = new ArrayList<Content>();
        this.views = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Content> getContents() {
        return movies;
    }

    public void setContents(List<Content> movies) {
        this.movies = movies;
    }

     public void addContent(Content movie) {
        Content movieToSearch = this.searchContentByTitle(movie.getTitle());
        if (movieToSearch != null) {
            throw new ExistentContentException(movie.getTitle());
        }
         FileUtils.writeContent(movie);
         this.movies.add(movie);
     }

     public List<ContentSummarize> getContentsSummarize() {
        return this.movies.stream()
                .map(movie -> new ContentSummarize(movie.getTitle(), movie.getLength(), movie.getGenre()))
                .toList();
     }

     public void removeContent(Content movie) {
        this.movies.remove(movie);
     }

     public List<String> showContentTitles() {

        return this.movies.stream().map(Content::getTitle).toList();
     }

     public int getTotalContentsLength() {

        return this.movies.stream().mapToInt(Content::getLength).sum();
     }

     public Content searchContentByTitle(String title) {

         return this.movies.stream()
                 .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
     }

     public List<Content> sortMostPopularContents() {

        return this.movies.stream()
                .sorted(Comparator.comparingDouble(Content::getRating).reversed())
                .limit(TOP_TEN)
                .toList();
     }

     public List<Content> searchContentsByGenre(Genre genre) {

        return this.movies.stream()
                .filter(movie -> movie.getGenre().equals(genre))
                .toList();
     }

     public boolean removeContentByTitle(String title) {
        for (Content movie : this.movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                this.movies.remove(movie);

                return true;
            }
        }

        return false;
     }

    public Map<Content, Integer> getViews() {
        return views;
    }

    public void setViews(Map<Content, Integer> views) {
        this.views = views;
    }

    public void play(Content movie) {
        int count = this.countViews(movie);
        movie.play();
        System.out.println(movie.getTitle() + " has been reproduced " + count + " times.");
    }

    private int countViews(Content movie) {
        int count = this.views.getOrDefault(movie, 0);
        count += 1;
        this.views.put(movie, count);

        return count;
    }
}
