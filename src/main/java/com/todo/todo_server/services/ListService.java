package com.todo.todo_server.services;

import com.todo.todo_server.models.ListModel;
import com.todo.todo_server.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListService {

    private ListRepository listRepository;

    @Autowired
    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public List<ListModel> getLists(){
        return listRepository.findAll();
    }

    public ListModel postList(ListModel list){
        return listRepository.save(list);
    }

    public void deleteList(int id){
        listRepository.deleteById(id);
    }

}

