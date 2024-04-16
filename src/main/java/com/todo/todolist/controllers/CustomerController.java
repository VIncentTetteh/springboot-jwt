package com.todo.todolist.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo/v1/management")
public class CustomerController {

    @GetMapping
    public String getMember() {
        return "Secured Endpoint :: GET - Customer controller";
    }

    @PostMapping
    public String post() {
        return "POST:: management controller";
    }
}
