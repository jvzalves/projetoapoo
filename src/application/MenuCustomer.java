package application;

import entities.Customer;
import repositories.CustomerMySQL;

public class MenuCustomer {

	public static void main(String[] args) {
		CustomerMySQL customerMySQL = new CustomerMySQL(null);
		Customer customer = customerMySQL.findById(3);
		if (customer != null) {
			System.out.println("Cliente encontrado:");
			System.out.println(customer);
		} else {
			System.out.println("Cliente não encontrado!");
		}
		

	}

}
