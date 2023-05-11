package interfaces;

import java.util.List;

import entities.Customer;

public interface CustomerRepository  {
	Customer findById(Integer id);
	void insert(Customer cuctomer);
	void update(Customer cuctomer);
	void deleteById(Integer id);
	List<Customer> findAll();

}