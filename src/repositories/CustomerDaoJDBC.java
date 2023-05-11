package repositories;

import interfaces.CustomerRepository;
import util.DB;

public class CustomerDaoJDBC {

	public static CustomerRepository createCustomer() {
		return new CustomerMySQL(DB.getConnection());

	}

}
