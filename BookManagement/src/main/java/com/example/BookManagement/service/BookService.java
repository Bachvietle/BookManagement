package com.example.BookManagement.service;

import com.example.BookManagement.DTO.BookDTO;
import com.example.BookManagement.exception.ResourceNotFound;

import java.util.List;

// Abstraction những method cần có
public interface BookService {

    // Lấy full ds:
    List<BookDTO> getAllBooks();

    // Tìm sách:
    BookDTO getBookById(Long id) throws Exception;

    // Tạo sách mới:
    BookDTO createBook(BookDTO bookDTO);

    // Update sachs
    BookDTO updateBook(Long id, BookDTO bookDTO) throws ResourceNotFound;

    // Xóa sách:
    void deleteBook(Long id);
}
