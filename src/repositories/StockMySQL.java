package repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	}

	@Override
	public void update(Stock stock) {
	
		
	}

	@Override
	public void deleteById(Integer id) {
	
		
	}
	
	@Override
	public List<Product> findAll() {
	
		return null;
	}

}
