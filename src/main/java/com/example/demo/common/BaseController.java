package com.example.demo.common;

import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseController<T> {
    public abstract ResponseEntity<T> create();
    public abstract ResponseEntity<T> put();
    public abstract ResponseEntity<T> getOne();
    public abstract ResponseEntity<T> getAll();

}
