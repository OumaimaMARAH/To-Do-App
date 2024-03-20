package com.example.Task.Service;


import com.example.Task.Entity.DTO.TaskRequestDto;
import com.example.Task.Entity.DTO.TaskResponseDto;
import com.example.Task.Entity.DTO.TasksResponseDto;
import com.example.Task.Entity.Task;
import com.example.Task.Feign.UserClient;
import com.example.Task.Repository.TaskRepo;
import com.example.Task.Utils.MappingProfile;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepo taskRepo;
    private final UserClient userClient;

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepo.findAll().stream()
                .map(MappingProfile::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskDto) {

        //if(userClient.findUserById(taskDto.getUserId())==null) throw new RuntimeException("User Not foud");
        var task = MappingProfile.mapToEntity(taskDto);
        return MappingProfile.mapToDto(taskRepo.save(task));
    }

    @Override
    public TaskResponseDto getTaskById(Long id) throws Exception {
        var task = taskRepo.findById(id).orElseThrow(() -> new Exception("Task not found"));
        return MappingProfile.mapToDto(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskDto) throws Exception {
        var task = taskRepo.findById(id).orElseThrow(() -> new Exception("Task not found"));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setId(taskDto.getId());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(task.getDueDate());
        return MappingProfile.mapToDto(taskRepo.save(task));
    }

    @Override
    public void deleteTask(Long id) throws Exception {
        var task = taskRepo.findById(id).orElseThrow(() -> new Exception("Task not found"));
        taskRepo.delete(task);
    }


   /* @RabbitListener(queues = "tasksQueue")
    public void receiveMessage(Long userId) {
        // Logic to delete tasks associated with the userId
        deleteTasksByUserId(userId);
    }

    private void deleteTasksByUserId(Long userId) {
        // Task deletion logic
        List<Task> listtasks = taskRepo.findTasksByUserId(userId);
        taskRepo.deleteAll(listtasks);
    }*/

    @Override
    public TasksResponseDto getTasksByUserId(Long userId){
        TasksResponseDto tasksResponseDto = new TasksResponseDto(taskRepo.getTasksByUserIdIs(userId).stream()
                .map(MappingProfile::mapToDto)
                .collect(Collectors.toList()));
        return tasksResponseDto;
    }

    @RabbitListener(queues = "deleteTasksQueue")
    public void receiveMessage(Long userId) {
        taskRepo.deleteAll(taskRepo.getTasksByUserIdIs(userId));
    }
    @RabbitListener(queues = "getTasksQueue")
    public Object getUsersTasks(Long userId){
        return new TasksResponseDto(taskRepo.getTasksByUserIdIs(userId).stream()
                .map(MappingProfile::mapToDto)
                .collect(Collectors.toList()));
    }

}
