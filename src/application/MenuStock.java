package application;

import java.util.Scanner;

import entities.Stock;
import repositories.StockMySQL;

public class MenuStock {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StockMySQL stockMySQL = new StockMySQL(null);
		System.out.println("---testando findById---");
		Stock stock = stockMySQL.findById(2);
		if (stock != null) {
			System.out.println("Estoque encontrado:");
			System.out.println(stock);
		} else {
			System.out.println("Estoque não encontrado.");
		}
		sc.close();
	}
}