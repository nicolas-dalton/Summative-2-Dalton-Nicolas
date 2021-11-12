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
public class PublisherJdbcTemplateDaoImplTest {

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

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

    @Test
    public void addGetDeletePublisher() {

        Publisher publisher = new Publisher();
        publisher.setName("Graywolf Press");
        publisher.setStreet("250 Third Avenue North");
        publisher.setCity("Minneapolis");
        publisher.setState("MN");
        publisher.setPostalCode("55401");
        publisher.setPhone("651-641-0077");
        publisher.setEmail("wolves@graywolfpress.org ");

        publisher = publisherDao.addPublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getPublisherId());

        assertEquals(publisher1, publisher);

        publisherDao.deletePublisher(publisher.getPublisherId());

        publisher1 = publisherDao.getPublisher(publisher.getPublisherId());

        assertNull(publisher1);
    }

    @Test
    public void getAllPublishers() {

        Publisher publisher = new Publisher();
        publisher.setName("Graywolf Press");
        publisher.setStreet("250 Third Avenue North");
        publisher.setCity("Minneapolis");
        publisher.setState("MN");
        publisher.setPostalCode("55401");
        publisher.setPhone("651-641-0077");
        publisher.setEmail("wolves@graywolfpress.org");

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

        List<Publisher> publisherList = publisherDao.getAllPublishers();

        assertEquals(2, publisherList.size());
    }

    @Test
    public void updatePublisher() {

        Publisher publisher = new Publisher();
        publisher.setName("Graywolf Press");
        publisher.setStreet("250 Third Avenue North");
        publisher.setCity("Minneapolis");
        publisher.setState("MN");
        publisher.setPostalCode("55401");
        publisher.setPhone("651-641-0077");
        publisher.setEmail("wolves@graywolfpress.org");

        publisher = publisherDao.addPublisher(publisher);

        publisher.setPostalCode("55420");
        publisher.setPhone("322-570-5298");
        publisher.setCity("St Paul");

        publisherDao.updatePublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getPublisherId());

        assertEquals(publisher1, publisher);
    }

}