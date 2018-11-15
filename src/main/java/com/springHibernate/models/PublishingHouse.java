package com.springHibernate.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publishing_house")
public class PublishingHouse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String name;

	@NotNull
	@Size(max = 100)
	private String owner;

	@OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL)
	private List<Book> books;

	@Override
	public String toString() {
		return "Id= " + id + " name= " + name + " owner = " + owner + " books = " + books.size();
	}
}
