package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import application.Program;
import entities.Product;
import interfaces.ProductRepository;
import util.DB;
import util.DbException;

public class Shopping implements interfaces.ProductService {
	private static final Map<Product, Integer> cart = new HashMap<>();
	private static Scanner sc = new Scanner(System.in);
	private static List<Product> products = new ArrayList<>();
	private ProductRepository productRepository;
	private static String paymentMethodString;

	public Shopping(ProductRepository productsRepository) {
		this.productRepository = productsRepository;
	}

	@Override
	public List<Product> showAvailableProduct() {
		return productRepository.findAll();
	}

	@SuppressWarnings({ "resource", "unused" })
	@Override
	public void buyProduct(Product product) {

		boolean productsListDisplayed = true; // variável para não retonar a lista repetida dos produtos
		Scanner scanner = new Scanner(System.in);
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Connection conn = DB.getConnection();
			if (!productsListDisplayed) {
				st = conn.prepareStatement("SELECT id, nome, preco, quantidade FROM produto WHERE quantidade > 0");
				rs = st.executeQuery();
				while (rs.next()) {
					Integer idProduct = rs.getInt("id");
					String nameProduct = rs.getString("nome");
					Double priceProduct = rs.getDouble("preco");
					Integer quantityProduct = rs.getInt("quantidade");
					System.out.println(idProduct + ", " + nameProduct + ", R$ " + priceProduct + ", Quantidade: "
							+ quantityProduct);
				}
				productsListDisplayed = false;
			}

			System.out.print("Digite o id do produto que deseja comprar:");
			Integer id = scanner.nextInt();

			PreparedStatement preparedStatement = st = conn.prepareStatement("SELECT id, nome, preco, quantidade FROM produto WHERE id = ? AND quantidade > 0");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {

				Integer idProduct = rs.getInt("id");
				String nameProduct = rs.getString("nome");
				Integer quantityProduct = rs.getInt("quantidade");
				Double priceProduct = rs.getDouble("preco");
				
				products.add(new Product(idProduct, nameProduct, quantityProduct, priceProduct));
				Product product1 = products.get(products.size() - 1);
				int quantity = cart.getOrDefault(product1, 0);
				cart.put(product1, quantity + 1);
				
				System.out.println(product1.getName() + " adicionado ao Carrinho! ");
				System.out.println();
				System.out.println("Produto(s) no carrinho:");
				System.out.println();

				for (Product p : products) {
					if (p != null) {
						System.out.println("Id: " + p.getId() + "| " + "Produto: " + p.getName() + "| " + "Preço: " +  Product.doubleToString(p.getPrice()));
								
					}
				}

				System.out.println("\nGostaria de adicionar um novo produto? \n ");
				System.out.println("'s' para sim ou 'n' para finalizar compra.\n");
				char resp = sc.nextLine().trim().toLowerCase().charAt(0);
				if (resp == 's') { // Se o usuário deseja adicionar mais produtos, chama novamente o método de adição de produtos ao carrinho
									
					buyProduct(product);

				} else {
					System.out.println("Escolha o método de pagamento: ");
					System.out.println("1 - Boleto");
					System.out.println("2 - Débito");
					System.out.println("3 - Crédito");
					System.out.println("4 - Pix");

					int paymentMethod = -1;

					while (paymentMethod < 1 || paymentMethod > 4) { // Loop para garantir que o método de pagamento inserido seja válido
																	
						try {
							paymentMethod = Integer.parseInt(sc.nextLine().trim());
						} catch (NumberFormatException e) {
							System.out.println("Método de pagamento inválido. Digite novamente.");
						}

						if (paymentMethod < 1 || paymentMethod > 4) {
							System.out.println("Método de pagamento inválido. Digite novamente.");
						}
					}

					paymentMethodString = "";
					switch (paymentMethod) {
					case 1:
						paymentMethodString = "Boleto";
						System.out.println("Pagamento via Boleto selecionado.");
						break;
					case 2:
						paymentMethodString = "Débito";
						System.out.println("Pagamento via Bébito selecionado.");
						break;
					case 3:
						paymentMethodString = "Crédito";
						System.out.println("Pagamento via Crédito selecionado.");
						break;
					case 4:
						paymentMethodString = "Pix";
						System.out.println("Pagamento via Pix selecionado.");
						break;
					}
					System.out.println();
					checkOut();
					Program.menu();
				}
				
				    st = conn.prepareStatement("UPDATE produto SET quantidade = quantidade - 1 WHERE id = ?");
				    st.setInt(1, id);
				    int rowsAffected = st.executeUpdate();
				    if (rowsAffected == 0) {
				        throw new DbException("Erro na compra do produto. Nenhum produto foi atualizado.");
				    }
				System.out.println("Produto comprado com sucesso.");
				System.out.println();
				System.out.println("Produto escolhido: " + "Id: " + idProduct + ", " + nameProduct + ", " + "Preço: "
						+ Product.doubleToString(priceProduct));
				cart.clear(); // Limpa o carrinho após o checkout ser concluído
				products.clear();// Limpa o mapa de produtos após o checkout ser concluído
				System.out.println("Volte sempre!");
				Program.menu();

			} else {
				throw new DbException("Produto não encontrado ou sem quantidade disponível.");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);

		}
	}

	@Override
	public void checkOut() {
	    double totalValue = 0.0;
	    Connection conn = null;
	    PreparedStatement st = null;

	    try {
	        conn = DB.getConnection();
	        st = conn.prepareStatement("UPDATE produto SET quantidade = quantidade - ? WHERE id = ?");

	        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
	            Product product = entry.getKey();
	            if (product != null) {
	                int quantity = entry.getValue();
	                st.setInt(1, quantity);
	                st.setInt(2, product.getId());
	                st.addBatch();
	                totalValue += product.getPrice() * quantity;
	            }
	        }

	        int[] rowsAffected = st.executeBatch();
	        for (int rows : rowsAffected) {
	            if (rows == 0) {
	                throw new DbException("Erro na compra de produtos. Nenhum produto foi atualizado.");
	            }
	        }

	        System.out.println("Produto(s): ");
	        Map<Integer, Integer> productQuantities = new HashMap<>();
	        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
	            Product product = entry.getKey();
	            int quantity = entry.getValue();
	            int productId = product.getId();
	            int totalQuantity = productQuantities.getOrDefault(productId, 0) + quantity;
	            productQuantities.put(productId, totalQuantity);
	        }

	        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
	            int productId = entry.getKey();
	            int totalQuantity = entry.getValue();
	            for (Product product : products) {
	                if (product.getId() == productId) {
	                    System.out.println("Id: " + product.getId() + "| Nome: " + product.getName() + "| Preço: " + Product.doubleToString(product.getPrice()) + "| Quantidade: " + totalQuantity);
	                    System.out.println("-----------------------------------------------------------");
	                    break;
	                }
	            }
	        }

	        System.out.println("Valor total da sua compra: " + Product.doubleToString(totalValue) + "  \nMétodo de Pagamento: " + paymentMethodString);

	        // Limpar carrinho e produtos
	        cart.clear();
	        products.clear();

	        System.out.println("\nAgradecemos pela preferência!\n");
	        Program.menu();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}