package edu.test;


import edu.test.bl.impl.BookServiceImplementation;
import edu.test.config.Context;
import edu.test.entity.Book;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = {Context.class})
public class MainClass {

    @Autowired
    private static BookServiceImplementation bookServiceImplementation;

    final public static Logger log = Logger.getLogger(MainClass.class);

    public static void main(String[] args) {

        bookServiceImplementation.save(new Book("GlebsBook"));
    }

}
