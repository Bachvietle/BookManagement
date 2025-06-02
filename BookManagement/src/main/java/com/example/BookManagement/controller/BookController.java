package com.example.BookManagement.controller;

import com.example.BookManagement.DTO.BookDTO;
import com.example.BookManagement.exception.ResourceNotFound;
import com.example.BookManagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookdl")
public class BookController {

    private BookService bookService;

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks() { // lấy toàn bộ sách
        List<BookDTO> bookList = bookService.getAllBooks();

        return new ResponseEntity<>(bookList, HttpStatus.OK); // trả về 200 OK và bookList
    }

    @GetMapping("/{id}") // Biến trong URL người dùng truyền vào
    public ResponseEntity<BookDTO> getBookByID(@PathVariable("id") Long id) throws Exception { // lấy sách theo ID
                                                                              //@PathVariable: map tên biến trong URL(id) với tham số tương ứng trong Java(id)
        BookDTO bookDTO = bookService.getBookById(id); // hàm getBookByID (Service)

        return ResponseEntity.ok(bookDTO); // trả về sách cần lấy (cách viết ngắn gọn của OK)
    }

    @PostMapping // (trả về đtg vừa tạo)
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO){ //@RequestBody: map JSON (clients gửi) -> DTO

        BookDTO bookSaved = bookService.createBook(bookDTO); // hàm createBook (Servive)

        return new ResponseEntity<>(bookSaved, HttpStatus.CREATED); // trả về bookSaved + HTTP Status: 201 Created( status chuẩn bị tạo thành công 1 tài nguyên mới)
    }

    @PutMapping // (trả về đtg vừa) update
    ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) throws ResourceNotFound {

        BookDTO update = bookService.updateBook(id, bookDTO);

        return ResponseEntity.ok(update);
    }

    ResponseEntity<Void> deleteBook(@PathVariable Long id){

        bookService.deleteBook(id); // xóa và ko cần trả về gì

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
