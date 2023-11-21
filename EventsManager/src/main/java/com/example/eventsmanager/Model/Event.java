package com.example.eventsmanager.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty(message = "id field cannot be empty")
    @Size(min = 2, message = "Length of id must be equal or greater than 2")
    private String id;
    @NotEmpty(message = "description cannot be empty")
    @Size(min = 15,message = "description must be equal or greater than 15")
    private String description;

//    @NotNull(message = "capacity cannot be empty")
    @Digits(integer = 2 , fraction = 0, message = "capacity must be a number")
    @Min(value = 25,message = "the number must be equal or greater than 25")
    private int capacity;
    //only accept present and future dates
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate endDate;

}
