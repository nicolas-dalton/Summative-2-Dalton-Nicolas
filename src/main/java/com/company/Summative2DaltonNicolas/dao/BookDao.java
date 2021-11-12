package com.company.Summative2DaltonNicolas.dao;

import com.company.Summative2DaltonNicolas.models.Book;

import java.util.List;

public interface BookDao {

    Book addBook(Book book);

    Book getBook(int bookId);

    List<Book> getAllBooks();

    void updateBook(Book book);

    void deleteBook(int bookId);

}
