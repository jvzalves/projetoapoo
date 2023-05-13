package application;
import java.util.List;
import entities.Product;
import interfaces.ProductService;
import repositories.ProductMySQL;
import services.Menu;
import services.Shopping;

public class MenuProduct  {

	public static void main(String[] args) {
		Menu.menu();

		ProductMySQL productMySQL = new ProductMySQL(null);
		ProductService productService = new Shopping(productMySQL);

		List<Product> availableProducts = productService.showAvailableProducts();
		if (availableProducts != null) {
			for (Product product : availableProducts) {
				System.out.println(product);
			}
		}

		Menu.menu();
	}

}
