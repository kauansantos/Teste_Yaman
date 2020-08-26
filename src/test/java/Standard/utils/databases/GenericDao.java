package Standard.utils.databases;

import Standard.utils.exceptions.ExceptionUtils;
import Standard.utils.readers.SearchFile;
import Standard.utils.readers.TxtReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GenericDao {

    private static Connection _Connection(String database, String ambiente) {
        return ConnectionDatabaseFactory.getConnection(database, ambiente);
    }

    private static String _Query(String sqlName) {
        String relativePath = SearchFile.getAbsolutePath(sqlName);
        return TxtReader.fileInLine(relativePath);
    }

    private static PreparedStatement _ConnectionQuery(String database, String ambiente, String sqlName) throws SQLException {
        Connection connection = _Connection(database, ambiente);
        String query = _Query(sqlName);
        return connection.prepareStatement(query);
    }

    private static PreparedStatement _QueryCall(String database, String ambiente, String sqlName) throws SQLException {
        Connection connection = _Connection(database, ambiente);
        String query = _Query(sqlName);
        return connection.prepareCall(query);
    }

    public static JsonArray select(String database, String ambiente, String sqlName, String... values) {

        JsonArray tabela = new JsonArray();

        try {
            PreparedStatement preparedStatement = _ConnectionQuery(database, ambiente, sqlName);
            //PreparedStatement preparedStatement = _QueryCall(database, ambiente, sqlName);
            for (int i = 1; i <= values.length; i++) {
                preparedStatement.setString(i, values[i - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int qtdColunas = resultSet.getMetaData().getColumnCount();

                JsonObject linha = new JsonObject();
                for (int i = 1; i <= qtdColunas; i++) {
                    String chave = resultSet.getMetaData().getColumnName(i);
                    String valor = resultSet.getString(i);
                    linha.addProperty(chave, valor);
                }

                tabela.add(linha);
            }
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
        // System.out.println("Tabela = " + tabela);
        return tabela;
    }

    public static void create(String database, String ambiente, String sqlName) {

        if (database.toLowerCase().trim().equals("oracle")) {
            throw new RuntimeException("Este método não pode ser utilizado para a base de dados do cliente");
        } else {
            try {
                String queryCreate = _Query(sqlName);
                Statement stmt = _Connection(database, ambiente).createStatement();

                stmt.executeUpdate(queryCreate);
            } catch (Exception exception) {
                ExceptionUtils.throwException(exception);
            }
        }
    }

    public static void insert(String database, String ambiente, String sqlName, JsonArray massa) {

        if (database.toLowerCase().trim().equals("oracle")) {
            throw new RuntimeException("Este método não pode ser utilizado para a base de dados do cliente");
        } else {
            try {

                String queryInsert = _Query(sqlName);
                Statement stmt = _Connection(database, ambiente).createStatement();

                for (JsonElement aux : massa) {
                    JsonObject object = aux.getAsJsonObject();

                    Set<String> keys = object.keySet();
                    List<String> colunas = new LinkedList<>();

                    for (String key : keys) {
                        colunas.add(object.get(key).getAsString());
                    }

                    stmt.executeUpdate(String.format(queryInsert, colunas.toArray()));

                }
            } catch (Exception exception) {
                ExceptionUtils.throwException(exception);
            }
        }
    }

    public static void drop(String database, String ambiente, String sqlName, String... values) {

        if ("oracle".equals(database.toLowerCase().trim())) {
            //if (database.toLowerCase().trim().equals("oracle")) {
            throw new RuntimeException("Este método não pode ser utilizado para a base de dados do cliente");
        } else {
            try {

                String queryDrop = _Query(sqlName);
                Statement stmt = _Connection(database, ambiente).createStatement();

                stmt.executeUpdate(queryDrop);

            } catch (Exception exception) {
                ExceptionUtils.throwException(exception);
            }
        }
    }

    public static void update(String database, String ambiente, String sqlName, String... values) {
        try {
            Connection conn = ConnectionDatabaseFactory.getConnection(database, ambiente);
            String relativePathInsert = SearchFile.getAbsolutePath(sqlName);
            String queryUpdate = TxtReader.fileInLine(relativePathInsert);
            PreparedStatement preparedStatement = conn.prepareStatement(queryUpdate);

            for (int i = 1; i <= values.length; i++) {
                preparedStatement.setString(i, values[i - 1]);
            }

            preparedStatement.executeUpdate(queryUpdate);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void delete(String database, String ambiente, String sqlName, String... values) {
        try {
            Connection conn = _Connection(database, ambiente);
            String queryDelete = _Query(sqlName);

            PreparedStatement preparedStatement = conn.prepareStatement(queryDelete);

            for (int i = 1; i <= values.length; i++) {
                preparedStatement.setString(i, values[i - 1]);
            }

            preparedStatement.executeUpdate(queryDelete);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

}
