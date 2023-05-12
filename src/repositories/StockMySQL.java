package repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import entities.Product;
import entities.Stock;
import interfaces.StockRepository;
import util.DB;
import util.DbException;

public class StockMySQL implements StockRepository {
	
	private Connection conn;
	
	public  StockMySQL (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Stock findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			  st = conn.prepareStatement("SELECT id, quantidade, produto_id " +
                      "FROM estoque " +
                      "WHERE id = ?");
			  
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Integer idStock = rs.getInt("id");
				Integer quantityInStock = rs.getInt("quantidade");
				Integer productIdInStock = rs.getInt("produto_id");

				Stock stock = new Stock(idStock, quantityInStock, productIdInStock, null, null);
				return stock;
			}

			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.getConnection();
		}

	}

	@Override
	public void insert(Stock stock) {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        conn = DB.getConnection();
	        st = conn.prepareStatement("INSERT INTO estoque" +
	                                   "(id, quantidade, produto_id)" +
	                                   "VALUES (?, ?, ?)",
	                                   Statement.RETURN_GENERATED_KEYS);

	        st.setInt(1, stock.getId());
	        st.setInt(2, stock.getQuantity());
	        st.setInt(3, stock.getProductId());

	        int rowsAffected = st.executeUpdate();

	        if (rowsAffected > 0) {
	            rs = st.getGeneratedKeys();
	            if (rs.next()) {
	                int id = rs.getInt(1);
	                stock.setId(id);
	            }
	        }
	        System.out.println("Estoque alterado com sucesso!");

	    } catch (SQLException e) {
	        System.out.println("Erro ao alterar estoque: " + e.getMessage());
	    } finally {
	        DB.closeResultSet(rs);
	        DB.closeStatement(st);
	    }
	}
	
	@Override
	public void update(Stock stock) {
	    PreparedStatement st = null;
	    try {
	        conn = DB.getConnection();
	        st = conn.prepareStatement("UPDATE estoque SET quantidade = ?, produto_id = ? WHERE id = ?");
	        st.setInt(1, stock.getQuantity());
	        st.setInt(2, stock.getProductId());
	        st.setInt(3, stock.getId());
	        st.executeUpdate();
	        
	        System.out.println("Estoque atualizado com sucesso!");
	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar estoque: " + e.getMessage());
	    } finally {
	        DB.closeStatement(st);
	    }
	}

	@Override
	public void deleteById(Integer id) {
	
		
	}
	
	@Override
	public List<Product> findAll() {
	
		return null;
	}

}
