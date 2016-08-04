package edu.test.repository;

import edu.test.entity.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gleb-pc on 8/3/16.
 */
public interface BookRepository extends CrudRepository<Book, Long> {
}
