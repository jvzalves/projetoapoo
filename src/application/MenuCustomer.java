package application;

import entities.Customer;
import repositories.CustomerMySQL;

public class MenuCustomer {

	public static void main(String[] args) {
		CustomerMySQL customerMySQL = new CustomerMySQL(null);
		Customer customer = customerMySQL.findById(3);
		customerMySQL.findById(7);	
		customer.setId(8);
		customerMySQL.update(customer);
		
		/*Customer customer2 = new Customer(8, "João", "jv@gmail.com");
		customerMySQL.insert(customer2);
		System.out.println("Novo pedido: " + customer2.getId());*/
		
		
		/*if (customer != null) {
			System.out.println("Cliente encontrado:");
			System.out.println(customer);
		} else {
			System.out.println("Cliente não encontrado!");
		}*/
		

	}

}
