package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.Product;
import interfaces.ProductRepository;
import util.DB;
import util.DbException;

public class ProductMySQL implements ProductRepository {
	
	private Connection conn;

	public ProductMySQL(Connection conn) {
		this.conn = conn;
	}
	
	

	@Override
	public Product findById(Integer id) {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        conn = DB.getConnection();
	        st = conn.prepareStatement("SELECT id, nome, quantidade, preco "
	                + "FROM produto "
	                + "WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Integer idProduct = rs.getInt("id");
				String nameProduct = rs.getString("nome");
				Integer quantityProduct = rs.getInt("quantidade");
				Double priceProduct = rs.getDouble("preco");
				Product product = new Product(idProduct, nameProduct, quantityProduct, priceProduct, null, null, null);
				return product;
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
	public void insert(Product product) {
	
		
	}

	@Override
	public void update(Product product) {
	
		
	}

	@Override
	public void deleteById(Integer id) {
		
		
	}
	
	@Override
	public List<Product> findAll() {
	
		return null;
	}

}
