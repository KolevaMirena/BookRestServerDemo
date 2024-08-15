package com.softuni.book_server.service.impl;


import com.softuni.book_server.model.dto.AuthorDTO;
import com.softuni.book_server.model.dto.BookDTO;
import com.softuni.book_server.model.entity.Book;
import com.softuni.book_server.repository.AuthorRepository;
import com.softuni.book_server.repository.BookRepository;
import com.softuni.book_server.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;


    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;

    }

    @Override
    public List<BookDTO> getAllBooks() {

      return   bookRepository.findAll().stream().map(BookServiceImpl::mapBookToDTO).toList();

    }

    @Override
    public Optional<BookDTO> findBookById(Long id) {

        return bookRepository.findById(id).map(BookServiceImpl::mapBookToDTO);

    }

    @Override
    public void deleteBookById(Long id) {
        //TODO

        this.bookRepository.deleteById(id);

    }


    private  static  BookDTO mapBookToDTO(Book book){

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(book.getAuthor().getName());


        BookDTO bookDTO =new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setAuthor(authorDTO);
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());


        return  bookDTO;

    }
}
