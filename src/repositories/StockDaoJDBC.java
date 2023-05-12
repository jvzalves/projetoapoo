package repositories;
import interfaces.StockRepository;
import util.DB;
public class StockDaoJDBC {
	
	public static  StockRepository createStock() {
		return new StockMySQL(DB.getConnection());
	}

}
