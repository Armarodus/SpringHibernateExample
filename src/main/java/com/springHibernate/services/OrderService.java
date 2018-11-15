package com.springHibernate.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springHibernate.dao.OrderRepository;
import com.springHibernate.interfaces.SaveInterface;
import com.springHibernate.models.Order;
import com.springHibernate.models.dto.OrderDto;

@Service
public class OrderService implements SaveInterface {

	private static final String XLSX_TEMPLATE = "Order_modified_template.xlsx";
	private static final String XLSX_OUTPUT = "order_output.xlsx";
	private static final String CONTEXT_XLSX_VAR = "orders";
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private Double sum;

	@Autowired
	private OrderRepository orderRepository;

	public void save(Order order) {
		log.info("message {}", order.toString());
		orderRepository.save(order);
	}

	public void remove(Integer id) {
		orderRepository.deleteById(id);
	}

	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	public Order getById(Integer id) {
		return orderRepository.findById(id).get();
	}

	public List<OrderDto> getAllModified() {
		List<OrderDto> ordersModified = new ArrayList<>();
		List<Order> orders = orderRepository.findAll();
		orders.forEach(order -> {
			ordersModified.add(order.toOrderDto());
		});
		return ordersModified;
	}

	public Double getOrdersSumTotal() {
		sum = 0.0;
		List<Order> orders = orderRepository.findAll();

		orders.forEach(order -> {
			sum = sum + order.getOrder_book().getPrice() * order.getCount();
		});
		return sum;
	}

	public Double getOrdersSumTotalByUser(Integer user_id) {
		sum = 0.0;
		List<Order> orders = orderRepository.findAllByUser(user_id);
		orders.forEach(order -> {
			sum = sum + order.getOrder_book().getPrice() * order.getCount();
		});
		return sum;
	}

	public List<OrderDto> getUserOrders(Integer user_id) {
		return this.getAllModified().stream().filter(order -> order.getUser_id().equals(user_id))
				.collect(Collectors.toList());
	}

	public void saveToExcelBookReport() {
		List<OrderDto> orders = this.getAllModified();
		try {
			InputStream is = new FileInputStream(new File(XLSX_TEMPLATE));

			OutputStream os = new FileOutputStream(XLSX_OUTPUT);
			Context context = new Context();
			context.putVar(CONTEXT_XLSX_VAR, orders);
			JxlsHelper.getInstance().processTemplate(is, os, context);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
