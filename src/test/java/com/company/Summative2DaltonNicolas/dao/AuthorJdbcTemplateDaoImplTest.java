package com.company.Summative2DaltonNicolas.dao;

import com.company.Summative2DaltonNicolas.models.Author;
import com.company.Summative2DaltonNicolas.models.Book;
import com.company.Summative2DaltonNicolas.models.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorJdbcTemplateDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private BookDao bookDao;

    @Before
    public void setUp() throws Exception {

        List<Book> bookList = bookDao.getAllBooks();

        for(Book book: bookList) {
            bookDao.deleteBook(book.getBookId());
        }

        List<Author> authorList = authorDao.getAllAuthors();

        for(Author author: authorList) {
            authorDao.deleteAuthor(author.getAuthorId());
        }

        List<Publisher> publisherList = publisherDao.getAllPublishers();

        for(Publisher publisher : publisherList) {
            publisherDao.deletePublisher(publisher.getPublisherId());
        }
    }
    // CREATE, READ, UPDATE, DELETE
    @Test
    public void addGetDeleteAuthor() {
        // ARRANGE
        Author author = new Author();
        author.setFirstName("Bob");
        author.setLastName("Smith");
        author.setStreet("6th st.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("78701");
        author.setPhone("512-710-5567");
        author.setEmail("bobsmith23@gmail.com");


        // ACT
        author = authorDao.addAuthor(author);
        Author author2 = authorDao.getAuthor(author.getAuthorId());

        // ASSERT
        assertEquals(author, author2);

        // ACT
        authorDao.deleteAuthor(author.getAuthorId());
        author2 = authorDao.getAuthor(author.getAuthorId());

        // ASSERT
        assertNull(author2);
    }
    // READ ALL TEST
    @Test
    public void getAllAuthors() {
        // ARRANGE
        Author author = new Author();
        author.setFirstName("Bob");
        author.setLastName("Smith");
        author.setStreet("6th st.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("78701");
        author.setPhone("512-710-5567");
        author.setEmail("bobsmith23@gmail.com");

        author = authorDao.addAuthor(author);

        // ARRANGE
        author.setFirstName("Jim");
        author.setLastName("Johnson");
        author.setStreet("S. Congress Ave");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("78704");
        author.setPhone("512-140-9230");
        author.setEmail("jimmy20@gmail.com");

        // ACT
        authorDao.addAuthor(author);
        List<Author> authorList = authorDao.getAllAuthors();

        // ASSERT
        assertEquals(2, authorList.size());
    }

    // UPDATE TEST
    @Test
    public void updateAuthor() {
        // ARRANGE
        Author author = new Author();
        author.setFirstName("Bob");
        author.setLastName("Smith");
        author.setStreet("6th st.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("78701");
        author.setPhone("512-710-5567");
        author.setEmail("bobsmith23@gmail.com");

        author = authorDao.addAuthor(author);

        // ACT
        author.setEmail("bobby70@yahoo.com");
        authorDao.updateAuthor(author);
        Author author2 = authorDao.getAuthor(author.getAuthorId());

        //ASSERT
        assertEquals(author, author2);

    }
}