package com.example.Task.Service;

import com.example.Task.Entity.DTO.TaskRequestDto;
import com.example.Task.Entity.DTO.TaskResponseDto;
import com.example.Task.Entity.DTO.TasksResponseDto;

import java.util.List;

public interface TaskService {
    List<TaskResponseDto> getAllTasks();

    TaskResponseDto createTask(TaskRequestDto taskDto);
    TaskResponseDto getTaskById(Long id) throws Exception;
    TaskResponseDto updateTask(Long id, TaskRequestDto taskDto) throws Exception;
    void deleteTask(Long id) throws Exception;
    TasksResponseDto getTasksByUserId(Long userId);


}
