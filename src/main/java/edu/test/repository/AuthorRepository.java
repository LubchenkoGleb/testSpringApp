package edu.test.repository;

import edu.test.entity.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gleb-pc on 8/3/16.
 */
public interface AuthorRepository extends CrudRepository<Author, Long>{
}
