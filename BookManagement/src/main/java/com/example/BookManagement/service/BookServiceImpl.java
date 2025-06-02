package com.example.BookManagement.service;

import com.example.BookManagement.DTO.BookDTO;
import com.example.BookManagement.entity.Book;
import com.example.BookManagement.exception.ResourceNotFound;
import com.example.BookManagement.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookServiceImpl implements  BookService{

    private BookRepository bookRepository; // gọi Respository
    private ModelMapper modelMapper;

    @Override
    public List<BookDTO> getAllBooks() { // lấy toàn bộ sách
       List<Book> bookList = bookRepository.findAll();

       return bookList.stream()// tạo 1 luồng stream từ danh sách
               .map(book -> modelMapper.map(book, BookDTO.class)) // map sang DTO, tự động copy các trường trùng tên v kiểu dl từ book -> DTO
               .collect(Collectors.toList()); // sau khi chuyển gom toàn bộ KQ thành 1 list
    }

    @Override
    public BookDTO getBookById(Long id) throws ResourceNotFound {
       Book checkBook = bookRepository.findById(id) // chỉ check được ở dạng Entity
               .orElseThrow(() -> new ResourceNotFound("Ko tìm thấy sách"));

        return modelMapper.map(checkBook, BookDTO.class); // rồi map qua lại DTO
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class); // DTO -> entity

        Book saved = bookRepository.save(book); // saved

        return modelMapper.map(saved, BookDTO.class); // map sang lại DTO
        // ko return luôn bookDTO vì nó chỉ là dữ liệu đầu vào, chưa chắc phản ánh đúng thứ lưu vào DB
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) throws ResourceNotFound {
        Book checkBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Ko tìm thấy sách"));

        checkBook.setTitle(bookDTO.getTitle());
        checkBook.setAuthor(bookDTO.getAuthor());
        checkBook.setPrice(bookDTO.getPrice());
        checkBook.setPublishedDate(bookDTO.getPublishedDate());

        Book updated = bookRepository.save(checkBook);

        return modelMapper.map(updated, BookDTO.class);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
