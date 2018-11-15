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
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Size(max = 100)
	@Column(unique = true)
	private String name;

	@Size(max = 100)
	@Column
	private String lastName;

	@OneToMany(mappedBy = "order_user", cascade = CascadeType.ALL)
	private List<Order> order;

	@Override
	public String toString() {
		return "Id= " + id + " name= " + name + " lastname = " + lastName + " orders = " + order.size();
	}
}
