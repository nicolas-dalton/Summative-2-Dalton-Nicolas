package com.company.Summative2DaltonNicolas.dao;

import com.company.Summative2DaltonNicolas.models.Author;
import com.company.Summative2DaltonNicolas.models.Book;
import com.company.Summative2DaltonNicolas.models.Publisher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookJdbcTemplateDaoImplTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private PublisherDao publisherDao;

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
    public void addGetDeleteBook() {
        //ARRANGE
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

        Publisher publisher = new Publisher();
        publisher.setName("Graywolf Press");
        publisher.setStreet("250 Third Avenue North");
        publisher.setCity("Minneapolis");
        publisher.setState("MN");
        publisher.setPostalCode("55401");
        publisher.setPhone("651-641-0077");
        publisher.setEmail("wolves@graywolfpress.org ");

        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("97831614841");
        book.setPublishDate(LocalDate.of(2010, 1, 5));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("My Life Story");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("47.10"));

        book = bookDao.addBook(book);

        Book book1 = bookDao.getBook(book.getBookId());

        assertEquals(book1, book);

        bookDao.deleteBook(book.getBookId());

        book1 = bookDao.getBook(book.getBookId());

        assertNull(book1);
    }

    @Test
    public void getAllBooks() {
        //ARRANGE
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

        Publisher publisher = new Publisher();
        publisher.setName("Graywolf Press");
        publisher.setStreet("250 Third Avenue North");
        publisher.setCity("Minneapolis");
        publisher.setState("MN");
        publisher.setPostalCode("55401");
        publisher.setPhone("651-641-0077");
        publisher.setEmail("wolves@graywolfpress.org ");

        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("97831614841");
        book.setPublishDate(LocalDate.of(2010, 1, 5));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("My Life Story");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("47.10"));

        book = bookDao.addBook(book);

        book.setIsbn("83026802");
        book.setPublishDate(LocalDate.of(2011, 2, 9));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("A true book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("53.99"));
    }

    @Test
    public void updateBook() {
        //ARRANGE
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

        Publisher publisher = new Publisher();
        publisher.setName("Graywolf Press");
        publisher.setStreet("250 Third Avenue North");
        publisher.setCity("Minneapolis");
        publisher.setState("MN");
        publisher.setPostalCode("55401");
        publisher.setPhone("651-641-0077");
        publisher.setEmail("wolves@graywolfpress.org ");

        publisher = publisherDao.addPublisher(publisher);

        publisher = new Publisher();
        publisher.setName("Penguin Random House");
        publisher.setStreet("7752 Yukon Avenue");
        publisher.setCity("Nanuet");
        publisher.setState("NY");
        publisher.setPostalCode("10954");
        publisher.setPhone("1-800-733-3000");
        publisher.setEmail("consumerservices@penguinrandomhouse.com");

        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("97831614841");
        book.setPublishDate(LocalDate.of(2010, 1, 5));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("My Life Story");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("47.10"));

        book = bookDao.addBook(book);


        book.setPrice(new BigDecimal("50.00"));
        book.setPublishDate(LocalDate.of(2010, 3, 15));
        book.setPublisherId(publisher.getPublisherId());

        bookDao.updateBook(book);

        Book book1 = bookDao.getBook(book.getBookId());

        assertEquals(book1, book);
    }

}