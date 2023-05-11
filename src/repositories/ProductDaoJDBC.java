package repositories;

import interfaces.ProductRepository;

public class ProductDaoJDBC {

	public static ProductRepository createProducts() {

		return new ProductMySQL();
	}
}
