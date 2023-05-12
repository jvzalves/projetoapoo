package interfaces;

import java.util.List;

import entities.Product;
import entities.Stock;

public interface StockRepository  {
	List<Product> findAll();
	Stock findById(Integer id);
	void insert(Stock stock);
	void update(Stock stock);
	void deleteById(Integer id);	

}