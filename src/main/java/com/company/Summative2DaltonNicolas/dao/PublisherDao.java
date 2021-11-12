package com.company.Summative2DaltonNicolas.dao;

import com.company.Summative2DaltonNicolas.models.Publisher;

import java.util.List;

public interface PublisherDao {

    Publisher addPublisher(Publisher publisher);

    Publisher getPublisher(int publisherId);

    List<Publisher> getAllPublishers();

    void updatePublisher(Publisher publisher);

    void deletePublisher(int publisherId);
}
