package application;
import java.util.List;
import java.util.Scanner;
import entities.Product;
import interfaces.ProductService;
import repositories.ProductMySQL;
import services.Shopping;

public class MenuProduct  {
	private static Scanner sc = new Scanner(System.in);
	static ProductMySQL productMySQL = new ProductMySQL(null);
	static ProductService productService = new Shopping(productMySQL);
    Shopping shopping = new Shopping(null);

	public static void main(String[] args) {
		menu();

		ProductMySQL productMySQL = new ProductMySQL(null);
		ProductService productService = new Shopping(productMySQL);

		List<Product> availableProducts = productService.showAvailableProduct();
		if (availableProducts != null) {
			for (Product product : availableProducts) {
				System.out.println(product);
			}
		}

		menu();
	}
	
	public static List<Product> menu() {

		System.out.println("--------------------------------------------");
		System.out.println("-------Bem vindo(a) a Loja------------------");
		System.out.println("--------------------------------------------");
		System.out.println("-------Selecione uma operação:--------------");
		System.out.println("--------------------------------------------");
		System.out.println("| 1<---  Ver Produtos da Loja    |");
		System.out.println("| 2<---  Comprar                 |");
	    System.out.println("| 3<---  Sair                    |");
	    
		int opc = sc.nextInt();

		switch (opc) {
		case 1:
			productService.showAvailableProduct();
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
			menu();
		}
		return null;
	}
}
