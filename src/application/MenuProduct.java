package application;

import entities.Product;
import repositories.ProductMySQL;

public class MenuProduct {

	public static void main(String[] args) {
		ProductMySQL productMysql = new ProductMySQL(null);
	    Product product = productMysql.findById(1);
	    System.out.println("------Testando o findById------");
	    if (product != null) {
			System.out.println("Produto encontrado:");
			System.out.println(product);
		} else {
			System.out.println("Produto não encontrado!");
		}
	    
	    /*System.out.println("------Testando o insert------");
	    Product product2 = new Product(8, "Tênis Nike", 400.0, 5);
	    product2.setClientId(1); */
	    
	    
	    System.out.println("------ Testando o update ------");
	    Product productToUpdate = new Product(8, "Novo Tênis Nike", 10, 500.0);
	    productMysql.update(productToUpdate);
	    System.out.println("Produto atualizado: " + productToUpdate);
	}
	}


