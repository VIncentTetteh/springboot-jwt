package com.todo.todolist.services;

import com.todo.todolist.domain.Todo;
import com.todo.todolist.repositories.TodoRepository;
import com.todo.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    public TodoService(TodoRepository todoRepository,UserRepository userRepository){
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Todo> createTodo(UUID userId,Todo todo){

        Todo todo1 = todoRepository.findById(todo.getId()).orElse(null);
        userRepository.findById(userId).ifPresent(todo::setUser);

        if(todo1 != null){
            todoRepository.save(todo);
            return new ResponseEntity<>(todo1, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Todo>> getTodos(UUID uuid){
        List<Todo> todos = todoRepository.findByUserId(uuid);
        return new ResponseEntity<>(todos,HttpStatus.OK);
    }
}
