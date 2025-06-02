package com.example.BookManagement;

import com.example.BookManagement.entity.Book;
import com.example.BookManagement.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class BookManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner run(BookRepository bookRepository) {
		return args -> {
			// Thêm 1 cuốn sách mẫu
			Book b = Book.builder()
					.title("Bụi Sao")
					.author("Nguyen Van A")
					.price(99.0)
					.publishedDate(LocalDate.now())
					.build();
			bookRepository.save(b);

			// In ra số lượng sách
			long count = bookRepository.count();
			System.out.println("Total books in DB: " + count);
		};
	}
}
