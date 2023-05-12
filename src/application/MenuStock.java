package application;

import java.util.List;
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
		System.out.println("---testando finAll---");
		List<Stock> list = stockMySQL.findAll();
		for (Stock stock : list) {
			System.out.println(stock);
		}

		sc.close();

	}

}