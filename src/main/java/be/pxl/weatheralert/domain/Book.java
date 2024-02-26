package be.pxl.weatheralert.domain;

public class Book {
    private Long id;
    private String title;
    private String author;

    // Constructors, Getters, and Setters
    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    // getters and setters
}
