package com.springHibernate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springHibernate.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query(value = "select u from Order u where u.order_user.id = ?1")
	List<Order> findAllByUser(Integer id);
}
