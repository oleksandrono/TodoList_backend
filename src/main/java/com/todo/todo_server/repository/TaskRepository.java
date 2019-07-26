package com.todo.todo_server.repository;

import com.todo.todo_server.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
    List<TaskModel> findByListId(int listId);
}
