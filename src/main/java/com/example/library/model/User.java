package com.example.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "personal_id_number",unique = true,nullable = false)
    private int personalIdNumber;



    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Setter(AccessLevel.PRIVATE)
    private List<Book> list = new ArrayList<>();


    public void addBook(Book book) {
        list.add(book);
        book.setUser(this);
    }

    public void addListBooks(List<Book> bookList) {
        for (Book book : bookList) {
            list.add(book);
        }

    }

    public void removeBook(Book book) {
        list.remove(book);
        book.setUser(null);
    }


}
