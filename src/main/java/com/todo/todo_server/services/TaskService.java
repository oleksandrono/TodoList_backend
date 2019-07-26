package com.todo.todo_server.services;

import com.todo.todo_server.models.ListModel;
import com.todo.todo_server.models.TaskDto;
import com.todo.todo_server.models.TaskModel;
import com.todo.todo_server.repository.ListRepository;
import com.todo.todo_server.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private ListRepository listRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ListRepository listRepository) {
        this.taskRepository = taskRepository;
        this.listRepository = listRepository;
    }

    private TaskModel convertDtoToModel(TaskDto taskDto){
        TaskModel taskModel = new TaskModel();
        taskModel.setId(taskDto.getId());
        taskModel.setDone(taskDto.isDone());
        taskModel.setTaskName(taskDto.getTaskName());
        taskModel.setList(listRepository.findById(taskDto.getListId()).orElse(new ListModel()));
        return taskModel;
    }
    private TaskDto convertModelToDto(TaskModel taskModel){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(taskModel.getId());
        taskDto.setListId(taskModel.getList().getId());
        taskDto.setDone(taskModel.isDone());
        taskDto.setTaskName(taskModel.getTaskName());
        return taskDto;
    }

    public List<TaskDto> getTasksByListId(int listId){
        return taskRepository.findByListId(listId)
                .stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    public TaskModel postTask(TaskDto task){
        return taskRepository.save(convertDtoToModel(task));
    }

    public TaskDto putTask(TaskDto taskDto, int id){
        TaskModel updatedTaskModel = taskRepository.findById(id).orElse(new TaskModel());
        TaskDto updatedTaskDto = convertModelToDto(updatedTaskModel);
        updatedTaskDto.setTaskName(taskDto.getTaskName());
        updatedTaskDto.setDone(taskDto.isDone());
        TaskModel changedTaskModel = convertDtoToModel(updatedTaskDto);
        taskRepository.save(changedTaskModel);
        return updatedTaskDto;
    }

    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }
}
