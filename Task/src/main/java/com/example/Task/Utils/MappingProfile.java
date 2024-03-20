package com.example.Task.Utils;

import com.example.Task.Entity.DTO.TaskRequestDto;
import com.example.Task.Entity.DTO.TaskResponseDto;
import com.example.Task.Entity.Task;
import org.modelmapper.ModelMapper;

public class MappingProfile {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static Task mapToEntity(TaskRequestDto taskDto) {

        return modelMapper.map(taskDto, Task.class);
    }

    public static TaskResponseDto mapToDto(Task task) {

        return modelMapper.map(task, TaskResponseDto.class);
    }






}
