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

	public Product(int id, String name, Double price, Integer quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public static String doubletoSToString(Double value) {
		return fmt.format(value);
	}

	public static NumberFormat getFmt() {
		return fmt;
	}


	public static int getCount() {
		return count;
	}

	public void setCount(int count) {
		Product.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public static void setFmt(NumberFormat fmt) {
		Product.fmt = fmt;
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

	public void setId(Integer id) {
		this.id = id;
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
		return id == other.id;
	}

	public String toString() {
		return "ID: " + id + " | Produto: " + name + " | Preço: " + Product.doubletoSToString(price);
	}

}