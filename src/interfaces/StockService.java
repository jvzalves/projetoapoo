package interfaces;

import java.util.List;

import entities.Product;

public interface StockService {
	void addProduct(Product product);

	void removeProductInStock(Product Produc);

	public List<Product> showAvailableProducts();

	public void upadteStock();

}
