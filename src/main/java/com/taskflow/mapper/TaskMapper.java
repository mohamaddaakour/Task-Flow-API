package com.taskflow.mapper;

import com.taskflow.dto.TaskRequestDTO;
import com.taskflow.dto.TaskResponseDTO;
import com.taskflow.entity.Task;

public class TaskMapper {
    
    // function to convert request dto to database entity
    public static Task toEntity(TaskRequestDTO dto) {
        if (dto == null)
            return null;

        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .done(dto.isDone())
                .build();
    }

    // function to convert the database entity into response dto
    public static TaskResponseDTO toResponseDTO(Task task) {
        if (task == null)
            return null;

        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .done(task.isDone())
                .createdAt(task.getCreatedAt())
                .build();
    }
}
