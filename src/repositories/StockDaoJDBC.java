package repositories;
import interfaces.StockRepository;
public class StockDaoJDBC {
	
	public static  StockRepository createStock() {
		return new StockMySQL();
	}

}
