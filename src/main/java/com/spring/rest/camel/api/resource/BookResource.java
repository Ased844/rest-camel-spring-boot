package com.spring.rest.camel.api.resource;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.spring.rest.camel.api.dto.Book;
import com.spring.rest.camel.api.dto.BookStore;
import com.spring.rest.camel.api.processor.BookProcessor;
import com.spring.rest.camel.api.service.BooksService;

@Component
public class BookResource extends RouteBuilder {
	
	@Value("${component}")
	private String component;
	
	@Value("${hostname}")
	private String hostName;
	
	@Value("${server.port}")
	private String port;
	
	@Autowired
	private BooksService service;

	@BeanInject
	private BookProcessor processor;

	@Override
	public void configure() throws Exception {
		restConfiguration()
			.component(component)
			.port(port)
			.host(hostName)
			.bindingMode(RestBindingMode.json);

		rest()
			.get("/books")
			.produces(MediaType.APPLICATION_JSON_VALUE)
			.route()
			.setBody(() -> service.getBooks())
			.endRest();
		
		rest()
			.get("/books-store")
			.produces(MediaType.APPLICATION_JSON_VALUE)
			.route()
			.setBody(() -> service.getBookStore())
			.endRest();

		rest()
			.post("/books")
			.consumes(MediaType.APPLICATION_JSON_VALUE)
			.type(Book.class)
			.outType(BookStore.class)
			.to("direct:rest-in")
			.route()
			.process(processor)
			.to("direct:rest-out")
			.endRest();
		
		from("direct:rest-in")
			.marshal().json(JsonLibrary.Jackson)
			.to("json-validator:producer-schema.json")
			.to("direct:marshal");
	
		from("direct:rest-out")
			.marshal().json(JsonLibrary.Jackson)
			.to("json-validator:consumer-schema.json")
			.to("direct:marshal");

		from("direct:marshal")
			.unmarshal().json(JsonLibrary.Jackson);
		}

}
