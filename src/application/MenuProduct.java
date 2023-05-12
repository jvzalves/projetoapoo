package application;

import entities.Product;
import repositories.ProductMySQL;

public class MenuProduct {

	public static void main(String[] args) {
		ProductMySQL productMysql = new ProductMySQL(null);
	    Product product = productMysql.findById(1);
	    if (product != null) {
			System.out.println("Produto encontrado:");
			System.out.println(product);
		} else {
			System.out.println("Produto não encontrado!");
		}

	}

}
