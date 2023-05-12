package application;

import java.util.Scanner;

import entities.Customer;
import services.CustomerLogin;

public class MenuCustomer {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// Solicita as informações do cliente
		// Obs:Colocar os mesmos nomes e emails que estão no MySQL
		System.out.println("----- Entre com os dados para realizar seu Login -----");
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Nome: ");
		String name = sc.nextLine();

		// Cria um objeto Customer com as informações fornecidas
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setName(name);

		// Chama o método login para fazer login do cliente
		CustomerLogin customerLogin = new CustomerLogin();
		customerLogin.login(customer);

		// Verifica se o login foi bem sucedido
		if (customer.getName() != null) {
			System.out.println("Login bem sucedido!");
		
		} else {
			System.out.println("Login falhou. Verifique seu nome e email e tente novamente.");
		}

	}

}