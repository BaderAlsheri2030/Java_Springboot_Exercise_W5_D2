package com.example.projecttrackervalidation.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class ProjectTracker {
        @NotEmpty(message = "ID cannot be empty")
        @Size (min = 2,message = "length of id must be more than 2 ")
        private String id;
        @NotEmpty(message = "title cannot be empty")
        @Size( min = 8,message = "length  of title must be more than 8")
        private String title;
        @NotEmpty(message = "description cannot be empty")
        @Size (min = 15,message = "length of description must be more than 15")
        private String description;
        @NotEmpty(message = "status cannot be empty")
        @Pattern(regexp = "not started|in progress|Completed",message = "Status must be set to (not started),(in progress),(Completed)")
        private String status;
        @NotEmpty(message = "Company must not be empty")
        @Size (min = 6, message = "company names must be grater than 6 characters")
        private String companyName;



}
