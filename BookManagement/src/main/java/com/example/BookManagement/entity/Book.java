package com.example.BookManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.apache.logging.log4j.message.Message;

import java.time.LocalDate;

@Entity
@Table(name = "books") // kết nối vs bảng books

// Dùng lombok để sinh sẵn getter, setter, constructor, builder,...
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Book {

    // vì entity sẽ dùng để giao tiếp với DB nên ms cần các key, column. Còn với DTO thì ko

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // chỉ định gtri primary key tự sinh (IDENTITY - tự tăng)
    private Long id;

    @NotBlank(message = "Ko được để trống") // validate và báo ra mh ko đc trống
    @Column(nullable = false) // mapping tới cột title và ko cho phép null
    private String title;


    @NotBlank(message = "Ko được để trống")
    @Column(nullable = false)
    private String author;

    @DecimalMin(value = "0.01", message = "Price phải lớn hơn 0") // min value = 0.01 > 0
    private Double price;

    private LocalDate publishedDate;
}
