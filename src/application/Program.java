package application;
import java.util.List;
import java.util.Scanner;
import entities.Customer;
import entities.Product;
import interfaces.ProductService;
import repositories.CustomerMySQL;
import repositories.ProductMySQL;
import services.CustomerLogin;
import services.Shopping;

public class Program {
	private static Scanner sc = new Scanner(System.in);
	static ProductMySQL productMySQL = new ProductMySQL(null);
	static ProductService productService = new Shopping(productMySQL);
	CustomerMySQL customerMySQL = new CustomerMySQL(null);
	Customer customer;

	public static void main(String[] args) {
		boolean isRegistered = false;

		while (!isRegistered) {
			System.out.println("Você já possui cadastro na loja? ");
			System.out.println("(s) para realizar login ou (n) para efetuar cadastro na loja");
			char resp = sc.next().charAt(0);
			sc.nextLine(); // Limpa o buffer do scanner

			if (resp == 'n') {
				System.out.println("----- Cadastro na Loja -----");
				System.out.print("Email: ");
				String email = sc.nextLine();
				System.out.print("Nome: ");
				String name = sc.nextLine();

				Customer customer = new Customer();
				customer.setEmail(email);
				customer.setName(name);

				CustomerMySQL customerMySQL = new CustomerMySQL(null);

				// Verifica se o cliente já existe no banco de dados
				if (isCustomerExists(customerMySQL, email, name)) {
					System.out.println("Esse nome ou email já são existentes. Tente novamente!");
					System.out.println();
				} else {
					customerMySQL.insert(customer); // Salva os dados do  novo cliente no banco de dados
					isRegistered = true;
				}

			} else if (resp == 's') {
				System.out.println("----- Entre com os dados para realizar seu Login -----");
				System.out.print("Email: ");
				String email = sc.nextLine();
				System.out.print("Nome: ");
				String name = sc.nextLine();

				Customer customer = new Customer();
				customer.setEmail(email);
				customer.setName(name);

				CustomerLogin customerLogin = new CustomerLogin();
				customerLogin.login(customer);

				if (customer.getName() != null) {
					System.out.println("Login bem sucedido!");
					menu();
					isRegistered = true;
				} else {
					System.out.println("Login falhou. Verifique seu nome e email e tente novamente.");
					System.out.println();
				}
			} else {

				isRegistered = true;
			}
		}
	}

	private static boolean isCustomerExists(CustomerMySQL customerMySQL, String email, String name) {
		List<Customer> existingCustomers = customerMySQL.findAll();
		for (Customer customer : existingCustomers) {
			if (customer.getEmail().equals(email) || customer.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static void menu() {
		System.out.println("--------------------------------------------");
		System.out.println("-------Bem vindo(a) a Loja------------------");
		System.out.println("--------------------------------------------");
		System.out.println("-------Selecione uma operação:--------------");
		System.out.println("--------------------------------------------");
		System.out.println("| 1<---  Ver Produtos da Loja    |");
		System.out.println("| 2<---  Comprar                 |");
		System.out.println("| 3<---  Sair                    |");

		int opc = sc.nextInt();
		sc.nextLine();

		switch (opc) {
		case 1:
			List<Product> availableProducts = productService.showAvailableProduct();
			if (availableProducts != null) {
				for (Product product : availableProducts) {
					System.out.println(product);
				}
			}
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
			break;
		}

		menu();
	}
}
