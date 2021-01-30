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
import com.example.BookStore.domain.User;
import com.example.BookStore.domain.UserRepository;




@SpringBootApplication
public class BookStoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository drepository, UserRepository userRepository) {
	return (args) -> {
		log.info("save a couple of books");
		drepository.save(new Category("Political books"));
		
		
		///long isbn, String title, String author, Integer year, Integer price, Category category
		
		repository.save(new Book(12345, "The Fascist Manifesto", "Benito Mussolini", 1900, 13, drepository.findByName("Political books").get(0)));
	
		// Create users: admin/admin user/user
		User user1 = new User("user", "$2y$12$PwYCv9Nfm4vRShh1h1RDZe1fxlocWz3EHPxWN.uYKN8d5NG6zXIjW", "USER",  "user@user.com");
		User user2 = new User("admin", "$2y$12$F0sFhooRHkDu5tkvgyS/7OHoeykyTbY6TObn5xIc3euDD6mSGbJde", "ADMIN", "admin@admin.com");
		User user3 = new User("user2", "$2y$12$PwYCv9Nfm4vRShh1h1RDZe1fxlocWz3EHPxWN.uYKN8d5NG6zXIjW", "USER", "user2@user.com");
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
	};
	}

}
