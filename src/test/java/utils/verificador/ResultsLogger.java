package utils.verificador;

import org.apache.log4j.Logger;

/**
 *
 * Evidenciar no console o tipo de teste executado.
 *
 * @author Thiago de Moraes
 **/
public class ResultsLogger {

    public static final Logger logger = Logger.getLogger("");

    public static Logger getLogger() {
        return logger;
    }

    public static String getMessage(String message) {
        logger.info(message);
        return message;
    }

    public static int isFuncional(String mensagem, int stat) {
        String nomesClasses = "CLASSE: " + classeNome() + " ==> " + "MÉTODO: " + metodoNome();
        if (stat == 0) {
            logger.info(nomesClasses);
            logger.info(mensagem);
        } else {
            logger.info(nomesClasses);
            logger.info(mensagem + ": Status " + stat);
        }
        return stat;
    }

    public static int isNotFuncional(String mensagem, int stat) {
        String nomesClasses = " CLASSE: " + classeNome() + " " + "MÉTODO: " + metodoNome();
        if (stat == 0) {
            logger.error(nomesClasses);
            logger.error(mensagem);
        } else {
            logger.error(nomesClasses);
            logger.error(mensagem + ": Status " + stat);
        }
        return stat;
    }

    public static String classeNome() {
        return Thread.currentThread().getStackTrace()[4].getClassName();
    }

    public static String metodoNome() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }

    public static int noReturn204(String nomeTeste, int stat) {
        logger.info("Não houve retorno de " + nomeTeste + ": Status " + stat + " No Content");
        return stat;
    }

    public static int erro400(String nomeTeste, int stat) {
        logger.info("Parametros errados para " + nomeTeste + ": Status " + stat + " Bad Request");
        return stat;
    }

    public static int erro500(String nomeTeste, int stat) {
        logger.info("Retorno de erro do Servidor de " + nomeTeste + ": Status " + stat + " Internal Server Error");
        return stat;
    }

    public static int errorNOK(String nomeTeste, int stat) {
        logger.info("Ocorreu um erro não esperado, " + nomeTeste + " não está em funcionamento: Status " + stat);
        return stat;
    }

    public static int errorNull(String nomeTeste, int stat) {
        logger.info("Não houve retorno de" + nomeTeste + ": Status" + stat + " NULL");
        return stat;
    }

    public static int erroVazio(String nomeTeste, int stat) {
        logger.info("Não houve retorno de" + nomeTeste + ": Status" + stat + " Campos Vazios");
        return stat;
    }

    public static void errorINIT(String nomeTeste) {
        logger.info("Não foi possivel iniciar " + nomeTeste);
    }
}
