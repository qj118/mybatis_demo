package org.demon.dao;

import org.demon.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

    Book findById(Integer id);
}
