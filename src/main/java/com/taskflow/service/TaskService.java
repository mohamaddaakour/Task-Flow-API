package com.taskflow.service;

import org.springframework.stereotype.Service;

import com.taskflow.entity.Task;
import com.taskflow.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // we can't say Task[] as a type, we have to use List
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> updateTask(Long id, Task updatedTask) {
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
        Task savedTask = taskRepository.save(taskParsed);

        // we put the object inside optional
        return Optional.of(savedTask);
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
