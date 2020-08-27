package org.demon.dao;

import org.demon.domain.Book;

public interface BookDao {

    Book findById(int id);

    void updateBook(Book book);
}
