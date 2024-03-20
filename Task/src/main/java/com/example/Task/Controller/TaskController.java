package com.example.Task.Controller;

import com.example.Task.Entity.DTO.TaskRequestDto;
import com.example.Task.Entity.DTO.TaskResponseDto;
import com.example.Task.Enumeration.ExceptionsMessage;
import com.example.Task.Enumeration.UserInputNotValidException;
import com.example.Task.Exception.TaskNotFoundException;
import com.example.Task.Service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @GetMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTaskById(@PathVariable("id") Long id) throws Exception {
        try {
            TaskResponseDto taskResponseDto = taskService.getTaskById(id);
            return ResponseEntity.ok(taskResponseDto);
        }catch (TaskNotFoundException e){
            throw new TaskNotFoundException(ExceptionsMessage.TASK_NOT_FOUND.getMessage());
        }
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody TaskRequestDto taskDto) {
        try {
            TaskResponseDto taskResponseDto = taskService.createTask(taskDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(taskResponseDto);
        }catch (UserInputNotValidException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateById(@PathVariable("id") Long id ,@RequestBody TaskRequestDto taskDto) throws Exception{
        try {
            TaskResponseDto responseDto = taskService.updateTask(id,taskDto);
            return ResponseEntity.ok(responseDto);
        }catch (TaskNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) throws Exception {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok().build();
        }catch (TaskNotFoundException e){
            throw new TaskNotFoundException(ExceptionsMessage.TASK_NOT_FOUND.getMessage() +" with id = "+ id);
        }
    }




    @GetMapping("/byUser/{id}")
    public ResponseEntity<?> getTasksByUserId( @PathVariable Long id){
        return new ResponseEntity<>(taskService.getTasksByUserId(id), HttpStatus.OK);
    }

}
