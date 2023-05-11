package repositories;

import interfaces.CustomerRepository;

public class CustomerDaoJDBC {

	public static CustomerRepository createCustomer() {

		return new CustomerMySQL();

	}

}
