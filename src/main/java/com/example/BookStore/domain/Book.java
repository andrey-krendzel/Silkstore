package com.example.BookStore.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Book {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;

	  
	private long isbn; 
	private String title;
	private String author;
	private Integer year;
	private Integer price;
	

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonManagedReference
    private Category category;
	
	public Book (long isbn, String title, String author, Integer year, Integer price, Category category) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
		this.category = category;
	}
	
	public Book() {
		
	}
	
	
	 public long     getId()       { return id;     }
	 
	  public void setId       (long   id     )    { this.id     = id;     }
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

	
	@Override
	public String toString() {
		if (this.category != null)
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", year=" + year + ", price=" + price
				+ ", category=" + category + "]";
		else
			return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", year=" + year + ", price=" + price
					+ "]";
	}
	
	
}
