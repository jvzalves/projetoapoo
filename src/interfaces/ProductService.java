package interfaces;

import java.util.List;

import entities.Product;

public interface ProductService {
	List<Product>showAvailableProducts();
	void buyProduct(Product product);
	void checkOut();
}
