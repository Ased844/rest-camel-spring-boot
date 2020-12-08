package com.spring.rest.camel.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.rest.camel.api.dto.Book;
import com.spring.rest.camel.api.dto.BookStore;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CamelRestApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void when_booksIsCalled_then_booksAreReturned() {
		ResponseEntity<Book[]> response = restTemplate.getForEntity("/books", Book[].class);
		Book[] books = response.getBody();

		assertTrue((response.getStatusCode()).is2xxSuccessful());
		assertTrue(books.length == 4);
	}

	@Test
	public void given_book_when_postbookIsCalled_then_bookWithStoreNameIsCreated() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    Book newBook = new Book();
	    newBook.setId(5);
	    newBook.setName("Ased");
	    newBook.setAuthor("Me");
	    newBook.setPrice(30);
		    
		restTemplate.postForObject("/books", newBook, Book.class);
		
		ResponseEntity<BookStore[]> response = restTemplate.getForEntity("/books-store", BookStore[].class);
		BookStore[] bookList = response.getBody();
		BookStore storeName = bookList[0];
		
		assertTrue((response.getStatusCode()).is2xxSuccessful());
		assertTrue(bookList.length == 1);
		assertEquals("Store Name", storeName.getStoreName());
	}
}
