package Standard.utils.databases;

public class LimparBaseLocal {

	public void limparBaseLocal() {

		String database = "h2";
		String ambiente = "local";
		String sqlName = "queryH2DeletarTabelaCrm";

		GenericDao.drop(database, ambiente, sqlName);
	}
}
