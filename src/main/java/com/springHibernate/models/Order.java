package com.springHibernate.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.springHibernate.models.dto.OrderDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book order_book;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User order_user;

	@Column
	private Integer count;

	@Column
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date order_date = new Date();

	@Override
	public String toString() {
		return "Id= " + id + " book_id= " + order_book.getId() + " user_id = " + order_user.getId() + " date = "
				+ order_date;
	}

	public OrderDto toOrderDto() {
		OrderDto orderDto = null;
		if (order_book != null && order_user != null && count != null) {
			orderDto = new OrderDto();
			orderDto.setId(this.getId());
			orderDto.setBook_id(this.getOrder_book().getId());
			orderDto.setBook_name(this.getOrder_book().getName());
			orderDto.setPrice(this.getOrder_book().getPrice());
			orderDto.setTotal(this.getOrder_book().getPrice() * this.getCount());
			orderDto.setUser_id(this.getOrder_user().getId());
			orderDto.setUser_name(this.getOrder_user().getName());
			orderDto.setCount(this.getCount());
			orderDto.setDate(this.getOrder_date());
		}
		return orderDto;
	}
}
