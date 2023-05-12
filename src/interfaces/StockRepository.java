package interfaces;

import java.util.List;

import entities.Stock;

public interface StockRepository  {
	List<Stock> findAll();
	Stock findById(Integer id);
	void insert(Stock stock);
	void update(Stock stock);
	void deleteById(Integer id);	

}