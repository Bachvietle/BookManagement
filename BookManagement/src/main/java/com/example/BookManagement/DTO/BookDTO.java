package com.example.BookManagement.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BookDTO {

    private Long id;

    @NotBlank(message = "Ko được trống")
    private String title;

    @NotBlank(message = "Ko được trống")
    private String author;

    @DecimalMin(value = "0.01", message = "Price phải lớn hơn 0")
    private Double price;

    private LocalDate publishedDate;
}
