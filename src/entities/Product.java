package entities;

import java.text.NumberFormat;
import java.util.Objects;


public class Product {
	private static NumberFormat fmt = NumberFormat.getCurrencyInstance();
	private static int count = 0;
	private Integer id;
	private String name;
	private Integer quantity;
	private Double price;
	private Integer clientId;

	private Customer customer;
	private Stock stock;

	public Product() {
	this.id = 0;
	}
	public Product(Integer id, String name, Integer quantity, Double price, Integer clientId, Customer customer,
			Stock stock) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.clientId = clientId;
		this.customer = customer;
		this.stock = stock;
	}

	public static String doubletoSToString(Double value) {
		return fmt.format(value);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public static NumberFormat getFmt() {
		return fmt;
	}

	public static void setFmt(NumberFormat fmt) {
		Product.fmt = fmt;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Product.count = count;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ID: " + id + " | Produto: " + name + " | Preço: " + Product.doubletoSToString(price);
	}
}