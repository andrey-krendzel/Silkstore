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
public class StoreApplication {
	private static final Logger log = LoggerFactory.getLogger(StoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository drepository, UserRepository userRepository) {
	return (args) -> {
		
		//Add categories
		drepository.save(new Category("Books"));
		drepository.save(new Category("Gym & Fitness"));
		drepository.save(new Category("Technology"));
		drepository.save(new Category("Banned items"));
		drepository.save(new Category("Miscellaneous"));
		// User: String username, String passwordHash, String role, String email
		User user1 = new User("user", "$2y$12$PwYCv9Nfm4vRShh1h1RDZe1fxlocWz3EHPxWN.uYKN8d5NG6zXIjW", "USER",  "user@user.com");
		User user2 = new User("admin", "$2y$12$F0sFhooRHkDu5tkvgyS/7OHoeykyTbY6TObn5xIc3euDD6mSGbJde", "ADMIN", "admin@admin.com");
	
		userRepository.save(user1);
		userRepository.save(user2);
		
		
		///Book: String title, String seller, Integer price, Integer quantity, Category category
		// Add standard items
		
		repository.save(new Book("The Fascist Manifesto", user1, 20, 2, drepository.findByName("Books").get(0)));
		repository.save(new Book("20kg Dumbell", user1, 100, 1, drepository.findByName("Gym & Fitness").get(0)));
		repository.save(new Book("Mint Cigarettes", user1, 15, 1, drepository.findByName("Banned items").get(0)));
	

		
	};
	}

}
