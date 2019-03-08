package com.example.dao;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book,Integer>{

}
