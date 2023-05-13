package entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Customer {
	
	private Integer id;
	private String name;
	private String email;
	
	private List <Product> products = new ArrayList<>();
	
	public Customer() {
		
	}
	
	public Customer(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}


	public Customer(Integer id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Id do Cliente: " + id + " | " + "Nome: " + name + " | " + "Email: " + email;
	}
}	