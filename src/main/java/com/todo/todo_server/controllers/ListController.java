package com.todo.todo_server.controllers;

import com.todo.todo_server.models.ListModel;
import com.todo.todo_server.services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("lists")
public class ListController {

    private ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping()
    public List<ListModel> getLists(){
        return listService.getLists();
    }

    @PostMapping
    public ListModel postList(@RequestBody ListModel list){
        return listService.postList(list);
    }

    @DeleteMapping("{id}")
    public void deleteList(@PathVariable int id){
        listService.deleteList(id);
    }


}
