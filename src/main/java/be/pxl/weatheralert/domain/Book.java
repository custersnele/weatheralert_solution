package be.pxl.weatheralert.domain;

import jakarta.validation.constraints.NotBlank;

public class Book {
    private Long id;
    @NotBlank
    private String title;
    private String author;

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
}
