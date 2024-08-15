package com.softuni.book_server.service.impl;


import com.softuni.book_server.model.dto.AuthorDTO;
import com.softuni.book_server.model.dto.BookDTO;
import com.softuni.book_server.model.entity.Author;
import com.softuni.book_server.model.entity.Book;
import com.softuni.book_server.repository.AuthorRepository;
import com.softuni.book_server.repository.BookRepository;
import com.softuni.book_server.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, AuthorRepository authorRepository1) {
        this.bookRepository = bookRepository;

        this.authorRepository = authorRepository1;
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

    @Override
    public Long createBook(BookDTO bookDTO) {


        Author author = authorRepository.findByName(bookDTO.getAuthor().getName()).orElse(null);

        if(author == null){
            author = new Author();
            author.setName(bookDTO.getAuthor().getName());
            author.setBooks(new ArrayList<>());
            authorRepository.save(author);

        }

        Book newBook = new Book();
        newBook.setIsbn(bookDTO.getIsbn());
        newBook.setAuthor(author);
        newBook.setTitle(bookDTO.getTitle());

        newBook = bookRepository.save(newBook);

        author.getBooks().add(newBook);
        author = authorRepository.save(author);

        return newBook.getId();

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
