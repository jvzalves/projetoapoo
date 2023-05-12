package application;

import java.util.Scanner;

import entities.Stock;
import repositories.StockMySQL;

public class MenuStock {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);

	    StockMySQL stockMySQL = new StockMySQL(null);
	    System.out.println("---testando findById---");
	    Stock stock1 = stockMySQL.findById(2);
	    if (stock1 != null) {
	        System.out.println("Estoque encontrado:");
	        System.out.println(stock1);
	    } else {
	        System.out.println("Estoque não encontrado.");
	    }
	
	    System.out.println("------ Testando o deleteById ------");
	    System.out.println("Entre com o id do estoque a ser deletado:");
	    int id = sc.nextInt();
	    stockMySQL.deleteById(id);
	    sc.close();
	   
	}

}