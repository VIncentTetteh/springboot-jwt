package com.todo.todolist.Enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    CUSTOMER_READ("management:read"),
    CUSTOMER_CREATE("management:create"),

    ;

    @Getter
    private final String permission;
}
