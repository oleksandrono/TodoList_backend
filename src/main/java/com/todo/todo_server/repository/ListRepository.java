package com.todo.todo_server.repository;

import com.todo.todo_server.models.ListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository  extends JpaRepository<ListModel, Integer> {
}
