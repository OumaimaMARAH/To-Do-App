package com.example.User.Service;

import com.example.User.Model.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="spring-cloud-eureka-Task")
public interface TaskClient {
    @GetMapping("/tasks")
    List<Task> getTasksByUserId(@RequestParam("userId") Long userId);
}
