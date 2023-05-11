package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	public void insert(Customer customer) {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        conn = DB.getConnection();
	        st = conn.prepareStatement("INSERT INTO cliente" +
	                                   "(id, nome, email)" +
	                                   "VALUES (?, ?, ?)",
	                                   Statement.RETURN_GENERATED_KEYS);

	        st.setInt(1, customer.getId());
	        st.setString(2, customer.getName());
	        st.setString(3, customer.getEmail());

	        int rowsAffected = st.executeUpdate();

	        if (rowsAffected > 0) {
	            rs = st.getGeneratedKeys();
	            if (rs.next()) {
	                int id = rs.getInt(1);
	                customer.setId(id);
	            }
	        }
	        System.out.println("Cliente inserido com sucesso!");

	    } catch (SQLException e) {
	        System.out.println("Erro ao inserir cliente: " + e.getMessage());
	    } finally {
	        DB.closeResultSet(rs);
	        DB.closeStatement(st);
	    }
	}

	@Override
	public void update(Customer customer) {
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("UPDATE cliente SET nome = ?, email = ? WHERE id = ?");
			st.setString(1, customer.getName());
			st.setString(2, customer.getEmail());
			st.setInt(3, customer.getId());
			st.executeUpdate();
			
			System.out.println("Cliente atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar cliente! " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("DELETE FROM cliente WHERE id = ?");

			st.setInt(1, id);
			st.executeUpdate();
		}

		catch (SQLException e) {
			System.out.println("Erro ao deletar cliente: " + e.getMessage());

		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public List<Customer> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Customer> customerList = new ArrayList<>();

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT id, nome, email FROM cliente");
			rs = st.executeQuery();

			while (rs.next()) {
				Integer idCustomer = rs.getInt("id");
				String nameCustomer = rs.getString("nome");
				String emailCustomer = rs.getString("email");
				Customer customer = new Customer(idCustomer, nameCustomer, emailCustomer);
				customerList.add(customer);
			}
			return customerList;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}