package com.example.BookManagement.repository;

import com.example.BookManagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {
    // repository của Book với primary key là Long

    /* Spring Data JPA tự động cung cấp:
       - List<Book> findAll();
       - Optional<Book> findById(Long id);
       - Book save(Book book);
       - void deleteById(Long id);
     … và nhiều method hỗ trợ khác nếu cần
     */
}
