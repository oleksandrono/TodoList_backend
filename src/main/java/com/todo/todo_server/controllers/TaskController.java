package com.todo.todo_server.controllers;


import com.todo.todo_server.models.TaskDto;
import com.todo.todo_server.models.TaskModel;
import com.todo.todo_server.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

   private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> getTasksByListId(@RequestParam int listId){
        return taskService.getTasksByListId(listId);
    }

    @PostMapping
    public TaskModel postTask(@RequestBody TaskDto task){
        return taskService.postTask(task);
    }

    @PutMapping("{id}")
    public TaskDto putTask(@RequestBody TaskDto task, @PathVariable int id){
        return taskService.putTask(task, id);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
    }

}
