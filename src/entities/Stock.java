package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stock {
	

	private Integer id;
	private Integer quantity;
	private Integer productId;
	
	private Product product;
	private List <Product> products = new ArrayList<>();
	
	public Stock() {
	}


	public Stock(Integer id, Integer quantity, Integer productId, Product product, List<Product> products) {
		this.id = id;
		this.quantity = quantity;
		this.productId = productId;
		this.product = product;
		this.products = products;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
	    return "Id do produto no estoque: " + id + " | Quantidade do produto no estoque: " + quantity;
	}
}