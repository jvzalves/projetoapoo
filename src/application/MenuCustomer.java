package application;

import java.util.List;
import java.util.Scanner;

import entities.Customer;
import repositories.CustomerMySQL;

public class MenuCustomer {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		CustomerMySQL customerMySQL = new CustomerMySQL(null);
		List<Customer> customerList = customerMySQL.findAll();
		for (Customer customer1 : customerList) {
			System.err.println(customer1);

		}
		
		sc.close();
		
	}

}



