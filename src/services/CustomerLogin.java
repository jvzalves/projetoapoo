package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Customer;
import util.DB;

public class CustomerLogin implements interfaces.CustomerService {
	private Connection conn;
	
	@Override
	public void login(Customer customer) {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        conn = DB.getConnection();
	        st = conn.prepareStatement("SELECT nome, email FROM cliente WHERE nome = ? AND email = ?");
	        st.setString(1, customer.getName());
	        st.setString(2, customer.getEmail());
	        rs = st.executeQuery();
	        if (rs.next()) {
	            String name = rs.getString("nome");
	            String email = rs.getString("email");
	            customer.setName(name);
	            customer.setEmail(email);
	       
	        } else {
	            customer.setName(null); // Define o nome do cliente como null se o login falhar
	            System.out.println("Nome ou email inválidos!");
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao fazer login: " + e.getMessage());
	    } finally {
	        DB.closeResultSet(rs);
	        DB.closeStatement(st);
	    }
	}
}