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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_order;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_user) references user(id_user)"))
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_payment", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_payment) references payment(id_payment)"))
	private Payment payment;
	
	@Column(name = "order_date")
	private String order_date;
	
	@Column(name = "discount")
	private Double discount;
	
	@Column(name = "total")
	private Double total;
	
	@Column(name = "state")
	private String state;

	@OneToMany(mappedBy = "orders")
	private Collection<OrderDetails> itemsOrderDetails = new ArrayList<>();
	
	public Orders() {
		// TODO Auto-generated constructor stub
	}

	public Orders(Integer id_order, User user, Payment payment, String order_date, Double discount, Double total,
			String state, Collection<OrderDetails> itemsOrderDetails) {
		super();
		this.id_order = id_order;
		this.user = user;
		this.payment = payment;
		this.order_date = order_date;
		this.discount = discount;
		this.total = total;
		this.state = state;
		this.itemsOrderDetails = itemsOrderDetails;
	}

	public Integer getId_order() {
		return id_order;
	}

	public User getUser() {
		return user;
	}

	public Payment getPayment() {
		return payment;
	}

	public String getOrder_date() {
		return order_date;
	}

	public Double getDiscount() {
		return discount;
	}

	public Double getTotal() {
		return total;
	}

	public String getState() {
		return state;
	}

	public Collection<OrderDetails> getItemsOrderDetails() {
		return itemsOrderDetails;
	}

	public void setId_order(Integer id_order) {
		this.id_order = id_order;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setItemsOrderDetails(Collection<OrderDetails> itemsOrderDetails) {
		this.itemsOrderDetails = itemsOrderDetails;
	}
	
}
