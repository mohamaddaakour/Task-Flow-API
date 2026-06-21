package com.taskflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    // here using the validation dependency we specify these constraints
    @NotBlank(message = "Title is required and cannot be blank")
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    private String title;

    private String description;
    private boolean done;
}
