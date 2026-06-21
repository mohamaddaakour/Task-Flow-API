package com.taskflow.service;

import org.springframework.stereotype.Service;

import com.taskflow.dto.TaskResponseDTO;
import com.taskflow.entity.Task;
import com.taskflow.mapper.TaskMapper;
import com.taskflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskResponseDTO createTask(Task task) {
        // when we save to the database i want to save the real entity not a dto
        Task savedTask = taskRepository.save(task);

        // when i print i print the dto, and i take from it what i want to take
        return TaskMapper.toResponseDTO(savedTask);
    }

    // we can't say Task[] as a type, we have to use List
    public List<TaskResponseDTO> getAllTasks() {
        // findAll method will return a List of tasks
        // stream() will makes us move in each task in each iteration
        // map() will apply specific function in each element in the list
        // stream will give us Stream<TaskResponseDTO>, collect() will transform it into a regular List
        return taskRepository.findAll().stream()
                .map(TaskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<TaskResponseDTO> getTaskById(Long id) {
        // map() will automatically handles checking if the entity is there
        // and then apply the toResponseDTO method
        return taskRepository.findById(id)
                .map(entityTask -> TaskMapper.toResponseDTO(entityTask));
    }

    public Optional<TaskResponseDTO> updateTask(Long id, Task updatedTask) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isEmpty()) {
            // return a clean empty Optional
            return Optional.empty();
        }

        // we take the value (object) from the optional
        Task taskParsed = taskOptional.get();

        // we update the data for this specific object
        taskParsed.setTitle(updatedTask.getTitle());
        taskParsed.setDescription(updatedTask.getDescription());
        taskParsed.setDone(updatedTask.isDone());

        // this will save this update in the database and will return the new updated object
        Task savedEntity = taskRepository.save(taskParsed);

        TaskResponseDTO responseDto = TaskMapper.toResponseDTO(savedEntity);

        // we put the object inside optional
        return Optional.of(responseDto);
    }

    // function to delete a task with specific id
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
