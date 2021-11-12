package com.company.Summative2DaltonNicolas.dao;

import com.company.Summative2DaltonNicolas.models.Author;

import java.util.List;

public interface AuthorDao {
    Author addAuthor(Author author);

    Author getAuthor(int authorId);

    List<Author> getAllAuthors();

    void updateAuthor(Author author);

    void deleteAuthor(int authorId);
}
