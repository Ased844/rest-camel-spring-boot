package com.spring.rest.camel.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.spring.rest.camel.api.dto.Book;
import com.spring.rest.camel.api.dto.BookStore;

@Service
public class BooksService {

	private List<Book> listBooks = new ArrayList<>();
	private List<BookStore> listBookStores = new ArrayList<>();

	@PostConstruct
	public void data() {
		listBooks.add(new Book(1, "Book A", "Author A", 100));
		listBooks.add(new Book(2, "Book B", "Author B", 200));
		listBooks.add(new Book(3, "Book C", "Author C", 300));
		listBooks.add(new Book(4, "Book D", "Author D", 400));
	}
	
	public BookStore addBookStore(BookStore bookStore) {
		listBookStores.add(bookStore);
		return bookStore;
	}

	public List<Book> getBooks() {
		return listBooks;
	}
	
	public List<BookStore> getBookStore() {
		return listBookStores;
	}

}
