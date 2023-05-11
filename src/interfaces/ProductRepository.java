package interfaces;

import java.util.List;

import entities.Product;

public interface ProductRepository  {
	List<Product> findAll();
	void insert(Product product);
	void update(Product product);
	void deleteById(Integer id);
	Product findById(Integer id);

}