package interfaces;

import java.util.List;

import entities.Product;

public interface ProductService {
	List<Product>showAvailableProduct();
	void buyProduct(Product product);
	void checkOut();
}
