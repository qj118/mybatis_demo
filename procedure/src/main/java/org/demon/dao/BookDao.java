package org.demon.dao;

import org.demon.domain.Book;

import java.util.List;

public interface BookDao {

    Book findById(int id);

    List<Book> findAll();
}
