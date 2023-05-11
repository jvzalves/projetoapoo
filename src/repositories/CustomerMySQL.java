package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.Customer;
import interfaces.CustomerRepository;
import util.DB;
import util.DbException;

public class CustomerMySQL implements CustomerRepository {
      private Connection conn;
      
      public CustomerMySQL(Connection conn) {
    	  this.conn = conn;
      }

	@Override
	public Customer findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT id, nome, email FROM cliente WHERE id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Integer idCustomer = rs.getInt("id");
				String nameCustomer = rs.getString("nome");
				String emailCustomer = rs.getString("email");

				Customer customer = new Customer(idCustomer, nameCustomer, emailCustomer);
				return customer;

			}

			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}

	}

	@Override
	public void insert(Customer cuctomer) {
	
		
	}

	@Override
	public void update(Customer cuctomer) {
	
		
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public List<Customer> findAll() {
		return null;
	}

}
