package entities;
import java.text.NumberFormat;

public class Product {
	
	private Integer id;
	private String name;
	private Integer quantity;
	private Double price;
	private Integer clientId;
	private static int count = 0;
	private static NumberFormat fmt = NumberFormat.getCurrencyInstance();
	private Customer customer;

	
	public Product() {
		this.id = 0;

	}

	public Product(int id, String name, Integer quantity, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public static String doubleToString(Double value) {
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

	public static int getCount() {
		return count;
	}

	public void setCount(int count) {
		Product.count = count;
	}

	public static NumberFormat getFmt() {
		return fmt;
	}

	public static void setFmt(NumberFormat fmt) {
		Product.fmt = fmt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
		return "ID: " + id + " | Produto: " + name + " | Preço: " + Product.doubleToString(price);
	}

}