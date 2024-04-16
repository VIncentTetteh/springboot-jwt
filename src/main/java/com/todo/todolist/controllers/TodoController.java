package com.todo.todolist.controllers;

import com.todo.todolist.domain.Todo;
import com.todo.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Todo> createTodo(@PathVariable UUID userId, @RequestBody Todo todo){
        return todoService.createTodo(userId,todo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<List<Todo>> getTod(@PathVariable UUID todoId){
        return todoService.getTodos(todoId);
    }



}
