package com.example.BookStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.BookStore.domain.Book;
import com.example.BookStore.domain.BookRepository;
import com.example.BookStore.domain.Category;
import com.example.BookStore.domain.CategoryRepository;



@SpringBootApplication
public class BookStoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository drepository) {
	return (args) -> {
		log.info("save a couple of books");
		drepository.save(new Category("Political books"));
		
		
		///long isbn, String title, String author, Integer year, Integer price, Category category
		
		repository.save(new Book(12345, "The Fascist Manifesto", "Benito Mussolini", 1900, 13, drepository.findByName("Political books").get(0)));
	
	};
	}

}
