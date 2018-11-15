package com.springHibernate.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springHibernate.models.dto.BookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String name;

	@NotNull
	@Size(max = 100)
	@Column
	private String author;

	@ManyToOne
	@JoinColumn(name = "ph_id", nullable = false)
	private PublishingHouse publishingHouse;

	@OneToMany(mappedBy = "order_book",cascade = CascadeType.ALL)
	private List<Order> order;

	@NotNull
	@Column
	private Double price;
	
	@Override
	public String toString(){
		return "Id= "+id+" name= "+name+" author = "+author+" phublishing house = ("+publishingHouse.toString()+" ) price = "+price;	
	}
	
	public BookDto toBookDto() {
		BookDto bookDto = null;
		if (name != "" && author != "" && publishingHouse != null) {
			bookDto = new BookDto();
			bookDto.setId(this.getId());
			bookDto.setName(this.getName());
			bookDto.setAuthor(this.getAuthor());
			bookDto.setPh(this.getPublishingHouse());
			bookDto.setPrice(this.getPrice());
		}
		return bookDto;
	}
}
