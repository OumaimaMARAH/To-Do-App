package com.example.Task.Feign;

import com.example.Task.Model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "spring-cloud-eureka-User")
public interface UserClient {
    @GetMapping("/api/v1/users/{id}")
    User findUserById(@PathVariable("id") Long id);
}
