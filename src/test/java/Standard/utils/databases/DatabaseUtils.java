package Standard.utils.databases;

import Standard.utils.exceptions.ValidateException;
import Standard.utils.services.RestTest;
import com.google.gson.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DatabaseUtils {

    private static final Logger logger = Logger.getLogger(DatabaseUtils.class);

    public static JsonObject getUserRandon(JsonArray array) {
        int posicaoAleatoria = new Random().nextInt(array.size());
        return array.get(posicaoAleatoria).getAsJsonObject();
    }

    public static JsonArray getMassaFiltrada(JsonArray array, String numeroPortal, String nomeFileSql) {
        JsonArray massaDoPortal = new JsonArray();
        try {
            for (JsonElement element : array) {
                JsonObject object = element.getAsJsonObject();
                String cdMarca = object.get("CD_MARCA").getAsString();
                if (cdMarca.equals(numeroPortal)) {
                    if (nomeFileSql.equals("PRIMEIRO ACESSO")
                            && object.get("PASSWORD").getAsString().equals("PRIMEIRO ACESSO")) {
                        massaDoPortal.add(object);
                    }
                    if (!nomeFileSql.equals("PRIMEIRO ACESSO")
                            && !object.get("PASSWORD").getAsString().equals("PRIMEIRO ACESSO")) {
                        massaDoPortal.add(object);
                    }
                }
            }
        } catch (Exception e) {
            return array;
        }
        return massaDoPortal;
    }

    public String useH2Embedded(String database, String ambiente, String nomeFileSql) {
        if (database.equals("h2")) {
            if (nomeFileSql.contains("Cadastrado")) {
                nomeFileSql = "queryH2GetUserCadastrado";
            }
            if (nomeFileSql.contains("SemCadastro")) {
                nomeFileSql = "queryH2GetUserSemCadastro";
            }
        }
        return nomeFileSql;
    }

    private static JsonObject getMassa(String database, String ambiente, String nomeFileSql, String numeroPortal) {

        JsonArray array = new JsonArray();
        JsonObject object = new JsonObject();

        array = GenericDao.select(database, ambiente, nomeFileSql, numeroPortal);

        if (array.size() > 0) {
            if (ambiente.equals("it2")) {
                object = array.get(0).getAsJsonObject();
            } else {
                for (int i = 0; i <= array.size(); i++) {
                    logger.info("Tentativa " + i + " de " + array.size());

                    String cdAssociado = array.get(i).getAsJsonObject().get("CD_ASSOCIADO").getAsString();
                    if (RestTest.isCrm(cdAssociado, nomeFileSql)) {
                        object = array.get(i).getAsJsonObject();
                        break;
                    } else if (i == array.size() - 2) {
                        throw new ValidateException("Nenhuma massa CRM encontrada");
                    }
                }
            }
        } else {
            throw new ValidateException("Nenhuma massa ORACLE encontrada");
        }

        return object;
    }

    private static JsonObject getMassaPlano(String database, String ambiente, String nomeFileSql, String empresa, String plano, String rede) {

        JsonArray array = new JsonArray();
        JsonObject object = new JsonObject();

        array = GenericDao.select(database, ambiente, nomeFileSql, empresa, plano, rede);
        System.out.println(array);
        if (array.size() > 0) {
            System.out.println(array.size());
            if (ambiente.equals("it2")) {
                object = array.get(0).getAsJsonObject();
            } else {
                for (int i = 0; i <= array.size(); i++) {
                    logger.info("Tentativa " + i + " de " + array.size());

                    String cdAssociado = array.get(i).getAsJsonObject().get("CD_ASSOCIADO").getAsString();
                    if (RestTest.isCrm(cdAssociado, nomeFileSql)) {
                        object = array.get(i).getAsJsonObject();
                        break;
                    } else if (i == array.size() - 2) {
                        throw new ValidateException("Nenhuma massa CRM encontrada");
                    }
                }
            }
        } else {
            throw new ValidateException("Nenhuma massa ORACLE encontrada");
        }

        return object;
    }

    private static void printUserEscolhido(JsonObject user) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(user);
    }

    private static Map<String, String> getUser(String banco, String ambiente, String nomeFileSql, String numeroPortal) {

        JsonObject firstObject = getMassa(banco, ambiente, nomeFileSql, numeroPortal);

        Set<String> colunas = firstObject.keySet();

        Map<String, String> resultado = new HashMap<String, String>();
        for (String coluna : colunas) {
            try {
                resultado.put(coluna, firstObject.get(coluna).getAsString().trim());
            } catch (Exception e) {
                resultado.put(coluna, "");
            }
        }

        printUserEscolhido(firstObject);

        return resultado;
    }

    public static Map<String, String> getPlano(String banco, String ambiente, String nomeFileSql, String empresa, String plano, String rede) {

        JsonObject firstObject = getMassaPlano(banco, ambiente, nomeFileSql, empresa, plano, rede);

        Set<String> colunas = firstObject.keySet();

        Map<String, String> resultado = new HashMap<String, String>();
        for (String coluna : colunas) {
            try {
                resultado.put(coluna, firstObject.get(coluna).getAsString().trim());
            } catch (Exception e) {
                resultado.put(coluna, "");
            }
        }

        printUserEscolhido(firstObject);

        return resultado;
    }

    public static Map<String, String> getRedeCredenciada(String empresa, String plano, String rede, String database) {
        return getPlano(database, System.getProperty("ambiente.teste"), "queryCrmRedeCredenciada", empresa, plano, rede);
    }

    public static Map<String, String> getEmpresaPlano(String numeroPortal, String database) {
        return getUser(database, System.getProperty("ambiente.teste"), "queryCrmEmpresaPlano", numeroPortal);
    }

    public static Map<String, String> getUserSemCadastro(String numeroPortal, String database) {
        return getUser(database, "local", "queryGetMassaUserSemCadastro", numeroPortal);
    }

    public static Map<String, String> getUserCadastrado(String numeroPortal, String database) {
        return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserCadastrado", numeroPortal);
    }

    public static Map<String, String> getUserDentista(String numeroPortal, String database) {
        return getUser(database, System.getProperty("ambiente.teste"), "queryCrmDentistaCadastro", numeroPortal);
    }

    public static Map<String, String> getUserCadastradoProd(String numeroPortal, String database) {
        return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserCadastradoProd", numeroPortal);
    }

    public static Map<String, String> getUserPrimeiroAcesso(String numeroPortal, String database) {
        return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserSemCadastro", numeroPortal);
    }

    public static Map<String, String> getUserComDependentes(String numeroPortal, String database) {
        return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserComDependentes", numeroPortal);
    }

    public static Map<String, String> getUserTitular(String numeroPortal, String database) {
        return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserTitular", numeroPortal);
    }

    public static Map<String, String> getUserComExtrato(String numeroPortal, String database) {
        return getUser(database, System.getProperty("ambiente.teste"), "queryCrmUserComExtrato", numeroPortal);
    }

}