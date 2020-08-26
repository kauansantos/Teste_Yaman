package utils.databases;

public class LimparBaseLocal {
	
	public void limparBaseLocal() {
		
		String database = "xxxxxxxx";
		String ambiente = "xxxxxxx";
		String sqlName = "xxxxxxx";
		
		GenericDao.drop(database, ambiente, sqlName);
		
	}
	
}
