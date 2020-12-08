package com.spring.rest.camel.api.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.rest.camel.api.dto.Book;
import com.spring.rest.camel.api.dto.BookStore;
import com.spring.rest.camel.api.service.BooksService;

@Component
public class BookProcessor implements Processor{
	
	@Autowired
	private BooksService service;

	@Override
	public void process(Exchange exchange) throws Exception {
		Book book = exchange.getIn().getBody(Book.class);
		
		BookStore bookStore = new BookStore();
		bookStore.setId(book.getId());
		bookStore.setName(book.getName());
		bookStore.setPrice(book.getPrice());
		bookStore.setStoreName("Store Name");
		
		service.addBookStore(bookStore);
	}

}
