package utils.databases;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import utils.exceptions.ValidateException;
import utils.services.RestTest;

public class DatabaseUtils {
	
	public static final Logger logger = Logger.getLogger(DatabaseUtils.class);
	
	/**
	 * Este_metodo_retorna_um jsonObject_dentro_de_um jsonArray
	 */
	public static JsonObject getUserRandon(JsonArray array) {
		Integer posicaoAleatoria = new Random().nextInt(array.size());
		return array.get(posicaoAleatoria).getAsJsonObject();
	}
	
	/**
	 * Este_metodo_faz o_filtro de_objetos_de_acordo_com o_ambiente
	 */
	public static JsonArray getMassaFiltrada(JsonArray array, String numeroPortal, String nomeFileSql) {
		JsonArray massaDoPortal = new JsonArray();
		try {
			for(JsonElement element : array) {
				JsonObject object = element.getAsJsonObject();
				String cdMarca = object.get("CD_MARCA").getAsString();
				if(cdMarca.equals(numeroPortal)) {
					if(nomeFileSql.equals("PRIMEIRO ACESSO") && object.get("PASSWORD").getAsString().equals("PRIMEIRO ACESSO")) {
						massaDoPortal.add(object);
					} 
					if(!nomeFileSql.equals("PRIMEIRO ACESSO") && !object.get("PASSWORD").getAsString().equals("PRIMEIRO ACESSO")) {
						massaDoPortal.add(object);
					}
				}
			}
		} catch(Exception e) {
			return array;
		}
		return massaDoPortal;
	}
	
	/**
	 * Este_metodo_usa queries para_usar massa_do_banco de_dados embarcado
	 */
	public String useH2Embedded(String database, String ambiente, String nomeFileSql) {
		if (database.equals("h2")) {
			ambiente = "local";
			if (nomeFileSql.contains("Cadastrado")) {
				nomeFileSql = "queryH2GetUserCadastrado";
			}			
			if (nomeFileSql.contains("SemCadastro")) {
				nomeFileSql = "queryH2GetUserSemCadastro";
			}
		}
		return nomeFileSql;
	}
	
	/**
	 * Este_metodo realiza_um select na_base de_dados e retorna_um_json array_contendo_json_objetos_abstraindo o_resultset de_uma_tabela
	 */
	public static JsonObject getMassa(String database, String ambiente, String nomeFileSql, String numeroPortal) {
		
		JsonArray array = new JsonArray();
		JsonObject object = new JsonObject();
		
		array = GenericDao.select(database, ambiente, nomeFileSql, numeroPortal);
		
		if(array.size() > 0) {
			if(ambiente.equals("it3")) {
				object = array.get(0).getAsJsonObject();
			} else {
				for (int i = 0; i <= array.size(); i++) {
					logger.info("Tentativa " + Integer.toString(i) + " de " + Integer.toString(array.size()));
					
					String cdAssociado = array.get(i).getAsJsonObject().get("CD_ASSOCIADO").getAsString();
					if(RestTest.isCrm(cdAssociado, nomeFileSql)) {
						object = array.get(i).getAsJsonObject();
						break;
					} else 	if(i == array.size() - 2) {
						throw new ValidateException("Nenhuma massa CRM encontrada");
					}
				}
			}
		} else {
			throw new ValidateException("Nenhuma massa ORACLE encontrada");
		}
			
		return object;
	}
	
	/**
	 * Imprime_na_evidencia o_usuario_escolhido do_banco de_dados
	 */
	@SuppressWarnings("unused")
	public static void printUserEscolhido(JsonObject user) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(user);
	}
	
	/**
	 * Este_metodo_retorna_um map_contendo a_abstracao de_uma linha_de resultset_de uma_tabela
	 */
	public static Map<String, String> getUser(String banco, String ambiente, String nomeFileSql, String numeroPortal) {

		JsonObject firstObject = getMassa(banco, ambiente, nomeFileSql, numeroPortal);
		
		Set<String> colunas = firstObject.keySet();

		Map<String, String> resultado = new HashMap<String, String>();
		for (String coluna : colunas) {
			try {
				resultado.put(coluna, firstObject.get(coluna).getAsString().trim());
			} catch(Exception e) {
				resultado.put(coluna, "");
			}
		}
		
		printUserEscolhido(firstObject);
		
		return resultado;
	}

	/**
	 * Este_metodo_retorna um_map_contendo_um_usuario_que nao_possui_cadastro no portal do_cliente
	 */
	public static Map<String, String> getUserSemCadastro(String numeroPortal, String database) {
		return getUser(database, "local", "queryGetMassaUserSemCadastro", numeroPortal);
	}
	
	/**
	 * Este_método_retorna_um map contendo_um usuário_que possuí_cadastro no portal do_cliente
	 * Escolhe_o select_de_acordo_com o_ambiente (Teste_Prod)
	 */
	public static Map<String, String> getUserCadastrado(String numeroPortal, String database) {
	return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserCadastrado", numeroPortal);
	}

	/**
	 * Este_método retorna_um map contendo_um usuário_que possuí_cadastro no portal do_cliente
	 * Escolhe_o select de_acordo_com o ambiente_Prod
	 */
	public static Map<String, String> getUserCadastradoProd(String numeroPortal, String database) {
		return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserCadastradoProd", numeroPortal);
		}
		

	/**
	 * Este_metodo retorna_um map contendo_um_usuario esta_para primeiro_acesso
	 */
	public static Map<String, String> getUserPrimeiroAcesso(String numeroPortal, String database) {
		return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserSemCadastro", numeroPortal);
	}
	
	/**
	 * Este_metodo_retorna_um map_contendo um_usuario que_possui_dependentes
	 */
	public static Map<String, String> getUserComDependentes(String numeroPortal, String database) {
		return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserComDependentes", numeroPortal);
	}
	
	/**
	 * Este_metodo_retorna_um map_contendo_um usuario_que_possui_dependentes
	 */
	public static Map<String, String> getUserTitular(String numeroPortal, String database) {
		return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserTitular", numeroPortal);
	}

	/**
	 * Este_metodo_retorna_um map_contendo_um_usuario_que possui_extrato de_atendimento
	 */
	public static Map<String, String> getUserComExtrato(String numeroPortal, String database) {
		return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserComExtrato", numeroPortal);
	}
		
}