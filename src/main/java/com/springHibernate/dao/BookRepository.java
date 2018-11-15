package com.springHibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springHibernate.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
