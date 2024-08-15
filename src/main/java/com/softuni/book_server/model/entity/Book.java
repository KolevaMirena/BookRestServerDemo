package com.softuni.book_server.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

    private Long id;

    private String name;
    private String isbn;



}
