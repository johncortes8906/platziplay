package platzi.play.platform;

import platzi.play.content.Genre;
import platzi.play.content.Content;
import platzi.play.content.ContentSummarize;
import platzi.play.content.Movie;
import platzi.play.exception.ExistentContentException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Platform {

     private String name;
     private List<Content> content;
     private static final int TOP_TEN = 10;
     private Map<Content, Integer> views;

    public Platform(String name) {
        this.name = name;
        this.content = new ArrayList<Content>();
        this.views = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Content> getContent() {
        return this.content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

     public void addContent(Content content) {
        Content contentToSearch = this.searchContentByTitle(content.getTitle());
        if (contentToSearch != null) {
            throw new ExistentContentException(content.getTitle());
        } else {
            FileUtils.writeContent(content);
            this.content.add(content);
        }
     }

     public List<ContentSummarize> getContentsSummarize() {
        return this.content.stream()
                .map(content -> new ContentSummarize(content.getTitle(), content.getLength(), content.getGenre()))
                .toList();
     }

     public List<Movie> getMovies() {
        return this.content.stream()
                .filter(content -> content instanceof Movie)
                .map(filteredContent -> (Movie) filteredContent)
                .toList();
     }

     public void removeContent(Content content) {
        this.content.remove(content);
     }

     public List<String> showContentTitles() {

        return this.content.stream().map(Content::getTitle).toList();
     }

     public int getTotalContentsLength() {

        return this.content.stream().mapToInt(Content::getLength).sum();
     }

     public Content searchContentByTitle(String title) {

        return this.content.stream()
                 .filter(content -> content.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
     }

     public List<Content> sortMostPopularContents() {

        return this.content.stream()
                .sorted(Comparator.comparingDouble(Content::getRating).reversed())
                .limit(TOP_TEN)
                .toList();
     }

     public List<Content> searchContentsByGenre(Genre genre) {

        return this.content.stream()
                .filter(content -> content.getGenre().equals(genre))
                .toList();
     }

     public boolean removeContentByTitle(String title) {
        for (Content content : this.content) {
            if (content.getTitle().equalsIgnoreCase(title)) {
                this.content.remove(content);

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

    public void play(Content content) {
        int count = this.countViews(content);
        content.play();
        System.out.println(content.getTitle() + " has been reproduced " + count + " times.");
    }

    private int countViews(Content content) {
        int count = this.views.getOrDefault(content, 0);
        count = count + 1;
        this.views.put(content, count);

        return count;
    }
}
