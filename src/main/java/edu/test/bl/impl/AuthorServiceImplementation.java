package edu.test.bl.impl;

import edu.test.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by gleb-pc on 8/3/16.
 */
@Service
public class AuthorServiceImplementation {

    @Resource
    private AuthorRepository authorRepository;
}
