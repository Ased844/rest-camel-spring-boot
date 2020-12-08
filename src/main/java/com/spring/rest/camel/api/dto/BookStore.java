package com.spring.rest.camel.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookStore {
	
	private int id;
	private String name;
	private String author;
	private double price;
	private String storeName;
}
