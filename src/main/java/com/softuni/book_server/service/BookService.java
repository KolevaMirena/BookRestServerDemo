package com.softuni.book_server.service;

import com.softuni.book_server.model.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {


    List<BookDTO> getAllBooks();

    Optional<BookDTO> findBookById(Long id);

    void deleteBookById(Long id);
}
