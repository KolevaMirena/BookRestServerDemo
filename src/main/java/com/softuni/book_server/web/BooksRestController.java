package com.softuni.book_server.web;


import com.softuni.book_server.model.dto.BookDTO;
import com.softuni.book_server.service.BookService;
import org.apache.juli.logging.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksRestController {


    private final BookService bookService;

    public BooksRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){

        return ResponseEntity.ok(bookService.getAllBooks());

    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable("id") Long id){

              Optional<BookDTO> bookDTOoptional = bookService.findBookById(id);

              if(bookDTOoptional.isEmpty()){
                  return ResponseEntity.notFound().build();
              }else{
                  return ResponseEntity.ok(bookDTOoptional.get());
              }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deletebyId(@PathVariable("id") Long id){

        bookService.deleteBookById(id);

        return  ResponseEntity.noContent().build();

    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO, UriComponentsBuilder uriComponentsBuilder){

        long newBookId = bookService.createBook(bookDTO);

        System.out.println(bookDTO);

        return ResponseEntity.created(
                uriComponentsBuilder.path("/api/books/{id}").build(newBookId)).build();

    }
}
