package com.example.BookStore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.BookStore.domain.Book;
import com.example.BookStore.domain.BookRepository;
import com.example.BookStore.domain.CategoryRepository;
import com.example.BookStore.domain.User;
import com.example.BookStore.domain.UserRepository;



@Controller
public class BookStoreApplicationController {
	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository drepository; 
	
	@Autowired
	private UserRepository urepository; 
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("books", repository.findAll());
		return "index";
	}
	
	@RequestMapping(value = {"/", "/home", "/productlist"}, method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		model.addAttribute("currentUsername", SecurityContextHolder.getContext().getAuthentication().getName());
		return "productlist";
	}
	
	@RequestMapping(value = {"/userlist"}, method = RequestMethod.GET)
	public String userList(Model model) {
		model.addAttribute("users", urepository.findAll());
		return "userlist";
	}

    
    @RequestMapping(value="/login")
	public String login() {
		return "login";
	}    
	
	// REST
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    

	// REST
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    }       
	
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	Book testBook = new Book();
    	testBook.setSeller(urepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
    	model.addAttribute("book", testBook);
    	model.addAttribute("categories", drepository.findAll());
        return "addBook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:productlist";
    }    
    
    // Delete student
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Long productId, Model model) {
    	Book specificBook = repository.findById(productId).orElse(null);
    	String sellerName = specificBook.getSellerUsername();
    	if (sellerName.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
    		repository.deleteById(productId);
    	}
    	System.out.println(sellerName + SecurityContextHolder.getContext().getAuthentication().getName());
        return "redirect:../productlist";
    }     
    
    // Edit book
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("book", repository.findById(id));
    	model.addAttribute("categories", drepository.findAll());
        return "editBook";
    }    
    
    // View user profile
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable("id") Long id, Model model) {
    	urepository.findById(id).ifPresent(o -> model.addAttribute("user", o));
        return "profile";
    } 
}
