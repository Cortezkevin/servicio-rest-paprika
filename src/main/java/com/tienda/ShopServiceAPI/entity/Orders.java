package com.tienda.ShopServiceAPI.entity;

import com.tienda.ShopServiceAPI.security.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {
	
	@Id
	private String id_order;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_user) references user(id_user)"))
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_payment", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_payment) references payment(id_payment)"))
	private Payment payment;
	
	@Column(name = "user")
	private String username;
	
	@Column(name = "order_date")
	private String order_date;
	
	@Column(name = "total")
	private Double total;
	
	@Column(name = "state")
	private String state;

	@OneToMany(mappedBy = "orders")
	private Collection<OrderDetails> itemsOrderDetails = new ArrayList<>();
	
	public Orders() {
		// TODO Auto-generated constructor stub
	}

	public Orders(String id_order, User user, Payment payment, String username, String order_date, Double total, String state) {
		this.id_order = id_order;
		this.user = user;
		this.payment = payment;
		this.username = username;
		this.order_date = order_date;
		this.total = total;
		this.state = state;
	}

	public String getId_order() {
		return id_order;
	}

	public void setId_order(String id_order) {
		this.id_order = id_order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Collection<OrderDetails> getItemsOrderDetails() {
		return itemsOrderDetails;
	}

	public void setItemsOrderDetails(Collection<OrderDetails> itemsOrderDetails) {
		this.itemsOrderDetails = itemsOrderDetails;
	}
}
