package edu.test.bl.impl;

import edu.test.MainClass;
import edu.test.bl.interf.BookServiceInterface;
import edu.test.entity.Book;
import edu.test.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by gleb-pc on 8/3/16.
 */
@Service
public class BookServiceImplementation implements BookServiceInterface{

    @Resource
    private BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
        MainClass.log.debug(book);
    }
}
