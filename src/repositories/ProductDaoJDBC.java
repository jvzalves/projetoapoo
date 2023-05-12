package repositories;

import interfaces.ProductRepository;
import util.DB;

public class ProductDaoJDBC {

	public static ProductRepository createProducts() {

		return new ProductMySQL(DB.getConnection());
	}
}
