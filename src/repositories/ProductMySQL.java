package repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
				Product product = new Product(idProduct, nameProduct, quantityProduct, priceProduct);
				return product;
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
	public void insert(Product product) {
	    PreparedStatement st = null;
	    try {
	        conn = DB.getConnection();
	        st = conn.prepareStatement("INSERT INTO produto" 
	                                    + "(id, nome, quantidade, preco, cliente_id) "
	                                    + "VALUES (?, ?, ?, ?, ?)",
	                                    Statement.RETURN_GENERATED_KEYS);
	        st.setInt(1, product.getId()); 
	        st.setString(2, product.getName()); 
	        st.setDouble(3, product.getPrice()); 
	        st.setInt(4, product.getQuantity());
	        
	        Integer clienteId = product.getClientId();
	        if (clienteId != null) {
	            st.setInt(5, clienteId);
	        } else {
	            st.setNull(5, java.sql.Types.INTEGER);
	        }
	        
	        int rowsAffected = st.executeUpdate();
	      
	        if (rowsAffected > 0) {
	            ResultSet rs = st.getGeneratedKeys();
	            if (rs.next()) {
	                int id = rs.getInt(1);
	                product.setCount(id);
	            }
	        }
	        
	        System.out.println("Produto inserido com sucesso!");
	        
	    } catch (SQLException e) {
	        System.out.println("Erro ao inserir produto: " + e.getMessage());
	    } finally {
	        DB.closeStatement(st);
	    
	    }
	}
	@Override
	public void update(Product product) {
	    PreparedStatement st = null;
	    try {
	        conn = DB.getConnection();
	        st = conn.prepareStatement(
	            "UPDATE produto "
	            + "SET nome = ?, quantidade = ?, preco = ? "
	            + "WHERE id = ?");
	        
	        st.setString(1, product.getName());
	        st.setInt(2, product.getQuantity());
	        st.setDouble(3, product.getPrice());
	        st.setInt(4, product.getId());
	        
	        int rowsAffected = st.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Produto atualizado com sucesso!");
	        } else {
	            System.out.println("Nenhum produto foi atualizado.");
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar produto: " + e.getMessage());
	    } finally {
	        DB.closeStatement(st);
	        DB.closeConnection();
	    }
	}
	
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("delete from produto where id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}

		catch (SQLException e) {

			System.out.println("Erro ao deletar produto: " + e.getMessage());

		} finally {
			DB.closeStatement(st);
		}
	}
	@Override
	public List<Product> findAll() {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    List<Product> productList = new ArrayList<>();

	    try {
	        conn = DB.getConnection();
	        st = conn.prepareStatement("SELECT id, nome, quantidade, preco FROM produto");
	                    
	        rs = st.executeQuery();

			while (rs.next()) {
				Integer idProduct = rs.getInt("id");
				String nameProduct = rs.getString("nome");
				Integer quantityProduct = rs.getInt("quantidade");
				Double priceProduct = rs.getDouble("preco");
				
				Product product = new Product(idProduct, nameProduct, quantityProduct, priceProduct);
				productList.add(product);
			}
			
			return productList;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}
	}
}