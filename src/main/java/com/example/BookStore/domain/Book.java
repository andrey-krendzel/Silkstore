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
	  
	  // id = productCode, let's operate from this assumption

	  
	//private long productCode; 
	private String title;
	private Integer price;
	private Integer quantity; 
	
	// Join seller to user
	@ManyToOne
    @JoinColumn(name = "username")
    @JsonManagedReference
    private User seller;
	
	private String sellerUsername; 
	

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonManagedReference
    private Category category;
	
	public Book (String title, User seller, Integer price, Integer quantity, Category category) {
		
		this.title = title;
		this.seller = seller;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.setSellerUsername(seller.getUsername());
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
	public User getSeller() {
		return seller;
	}
	
	public String getSellerUsername() {
		return seller.getUsername();
	}
	
	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
		return "Product [ title=" + title + ", seller=" + seller.getUsername() + ", price=" + price
				+ ", quantity =" + quantity + ", category=" + category + "]";
		else
			return "Product [ title=" + title + ", seller=" + seller.getUsername() + "price=" + price
					+ ", quantity =" + quantity + "]";
	}

	public void setSellerUsername(String sellerUsername) {
		this.sellerUsername = sellerUsername;
	}
	
	
}
