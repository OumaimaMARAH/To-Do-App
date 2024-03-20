package com.example.Task.Repository;

import com.example.Task.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    //List<Task> findTasksByUserId(Long id);
    List<Task> getTasksByUserIdIs(Long userId);
}
