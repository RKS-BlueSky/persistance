package com.Library.Library.repository;

import com.Library.Library.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long id);

    List<Book> findByCategory_Id(Long id);
}

