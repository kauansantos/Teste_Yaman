package utils.databases;

import java.sql.Connection;
import java.sql.DriverManager;

import utils.exceptions.ExceptionUtils;
import util.selenium.*;
public class ConnectionDatabaseFactory {
	
	/**
	 * Este_metodo_retorna_uma_conexao_de_um_banco_de_dados_informado e_de_acordo com_um_ambiente
	 */
	public static Connection getConnection(String database, String ambiente) {
		
		ambiente = ambiente.trim();
		
		String serverName   = Config.getProperty(database + ".servername."   + ambiente);
		String portNumber   = Config.getProperty(database + ".portnumber."   + ambiente);
		String servico      = Config.getProperty(database + ".servico."      + ambiente);
		String userName     = Config.getProperty(database + ".username."     + ambiente);
		String password     = Config.getProperty(database + ".password."     + ambiente);
		String classForName = Config.getProperty(database + ".classforname." + ambiente);
		String url          = Config.getProperty(database + ".url."          + ambiente);

		url = String.format(url, serverName, portNumber, servico);
		
		Connection connection = null;
		
		try {
			Class.forName(classForName);
			connection = DriverManager.getConnection(url, userName, password);
		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
		
		return connection;
	}
	
}
