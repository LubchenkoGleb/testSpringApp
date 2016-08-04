package edu.test.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by gleb-pc on 8/3/16.
 */
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long authorId;
    @Column(name = "name")
    private String authorName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "authorList")
//    @JoinTable(name = "jc_book_author",
//        joinColumns = @JoinColumn(name = "author_id"),
//        inverseJoinColumns = @JoinColumn(name = "book_id"))
//    @OrderBy(value="bookName")
    //@Transient
    private List<Book> bookList;


    public Author() {
    }

    public Author(String authorName, List<Book> bookList) {
        this.authorName = authorName;
        this.bookList = bookList;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

}
