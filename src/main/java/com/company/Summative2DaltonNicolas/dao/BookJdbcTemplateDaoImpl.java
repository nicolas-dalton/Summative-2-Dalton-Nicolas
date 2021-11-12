package com.company.Summative2DaltonNicolas.dao;

import com.company.Summative2DaltonNicolas.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookJdbcTemplateDaoImpl implements BookDao {

    private static final String INSERT_BOOK_SQL =
            "insert into book (isbn, publish_date, author_id, title, publisher_id, price) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";

    private static final String SELECT_ALL_BOOK_SQL =
            "select * from book";

    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id = ?";

    private static final String UPDATE_BOOK_SQL =
            "update book set isbn = ?, publish_date = ?, author_id = ?, title = ?, publisher_id = ?, price = ? where book_id = ?";

    // instance variable
    private JdbcTemplate jdbcTemplate;

    /*
        Autowired = DEPENDENCY INJECTION
        Enables you to inject the object dependency implicitly.
        It internally uses setter or constructor injection.
     */
    @Autowired
    public BookJdbcTemplateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    // CREATE
    @Override
    @Transactional
    public Book addBook(Book book) {
        jdbcTemplate.update(INSERT_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice());

        int bookId = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        book.setBookId(bookId);

        return book;
    }

    // READ
    @Override
    public Book getBook(int bookId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, bookId);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //READ ALL
    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOK_SQL, this::mapRowToBook);
    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice(),
                book.getBookId());
    }

    @Override
    public void deleteBook(int bookId) {
        jdbcTemplate.update(DELETE_BOOK_SQL, bookId);
    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setBookId(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublishDate(rs.getDate("publish_date").toLocalDate());
        book.setAuthorId(rs.getInt("author_id"));
        book.setTitle(rs.getString("title"));
        book.setPublisherId(rs.getInt("publisher_id"));
        book.setPrice(rs.getBigDecimal("price"));

        return book;

    }
}
