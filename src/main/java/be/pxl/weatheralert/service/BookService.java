package be.pxl.weatheralert.service;


import be.pxl.weatheralert.domain.Book;

public interface BookService {
    Book saveBook(Book book);
    Book getBookById(Long id);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
}
