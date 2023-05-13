package services;
import java.util.List;
import java.util.Scanner;

import entities.Product;
import interfaces.ProductService;
import repositories.ProductMySQL;

public class Menu {
	
	private static Scanner sc = new Scanner(System.in);
	static ProductMySQL productMySQL = new ProductMySQL(null);
	static ProductService productService = new Shopping(productMySQL);
    Shopping shopping = new Shopping(null);
    
	public static List<Product> menu() {

			System.out.println("--------------------------------------------");
			System.out.println("-------Bem vindo(a) ao Mercado Free---------");
			System.out.println("--------------------------------------------");
			System.out.println("-------Selecione uma operação:--------------");
			System.out.println("--------------------------------------------");
			System.out.println("| 1<---  Ver Produtos da Loja    |");
			System.out.println("| 2<---  Comprar                 |");
		    System.out.println("| 3<---  Sair                    |");
		    
			int opc = sc.nextInt();

			switch (opc) {
			case 1:
				productService.showAvailableProducts();
				break;
			case 2:
				productService.buyProduct(null);
				break;
			case 3:
				System.out.println("Volte sempre!");
				System.exit(0);
				break;
			default:
				System.out.println("Por favor, escolha uma opção válida");
			case 4:
				System.out.println("Volte sempre!");
				System.exit(0);
				break;
			}
			return null;
		}
	}
