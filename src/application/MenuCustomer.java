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


/*Customer customer = customerMySQL.findById(3);
System.out.println("Entre com o id do Cliente a ser deletado:");
int id = sc.nextInt();
customerMySQL.deleteById(id);
System.out.println("Cliente deletado deletado!");
sc.close();	*/

/*customerMySQL.findById(7);	
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
