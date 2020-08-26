package utils.verificador;

import org.openqa.selenium.WebElement;
import utils.evidencia.Evidencia;
import utils.evidencia.EvidenciaIterator;
import utils.exceptions.ValidateException;

public class Inspecionardor {

    /**
     *
     * Verificar e evidenciar o tipo de teste executado.
     *
     * @author Thiago de Moraes
     *
     *
     * @param validar
     *            = "sucesso" ou "erro" - Se for sucesso o teste continuará,
     *            caso contrario será finalizado como falha;
     * @param mensagem
     *            = Informação que deverá ser apresentada na
     *            {@linkplain Evidencia} do teste;
     * @param passo
     *            = "inicial", "meio" ou "final" - Para o "inicial" e o "meio",
     *            o teste continurá e a evidencia será feita. Para "final" o
     *            teste será concluido visando o campo "validar";
     * @param campo
     *            = WebElement a ser printado na {@link Evidencia};
     *
     */

    public static void TipoTeste(String validar, String mensagem, String passo, WebElement... campo) {
        // MENSAGEM DE TESTE DAS CLASSES E MÉTODOS
        String nomesClasses = "CLASSE: " + ResultsLogger.classeNome() + " ==> " + "MÉTODO: "
                + ResultsLogger.metodoNome();

        // INFORMATIVO A SER APRESENTADO
        String informativo = validar.toUpperCase() + " no passo: " + mensagem + ". ";

        String testErro = "Verificar: " + nomesClasses + "\n Ocorreu " + informativo;

        // FALHA PARA PASSO NÃO ENCONTRADO. REVISAR CÓDIGO
        String verificar = "Verificar teste no passo: " + passo
                + " - Não faz parte da verificação - Finalizando teste como 'Falhado'";

        final boolean b1 = "inicial".equals(passo) || "meio".equals(passo) || "final".equals(passo);
        switch (validar) {
            case "sucesso":

                if (campo != null) {

                    if ("inicial".equals(passo) || "meio".equals(passo)) {
                        ResultsLogger.isFuncional(informativo, 0);
                        Evidencia.print(mensagem, campo);

                    } else if ("final".equals(passo)) {
                        ResultsLogger.isFuncional(informativo, 0);
                        Evidencia.addPrintPassed(mensagem, campo);
                        EvidenciaIterator.finishEvidencia(false);

                    } else {
                        ResultsLogger.isNotFuncional(informativo, 0);
                        Evidencia.addPrintFailed(verificar + " - " + nomesClasses);
                        ResultsLogger.getMessage(verificar);
                        EvidenciaIterator.finishEvidencia(true);
                    }
                } else {
                    if ("inicial".equals(passo) || "meio".equals(passo)) {
                        ResultsLogger.isFuncional(informativo, 0);
                        Evidencia.print(mensagem);

                    } else if ("final".equals(passo)) {
                        ResultsLogger.isFuncional(informativo, 0);
                        Evidencia.addPrintPassed(mensagem);
                        EvidenciaIterator.finishEvidencia(false);
                    }
                }
                break;

            case "erro":
                if (b1) {
                    Evidencia.addPrintFailed(testErro);
                    ResultsLogger.isNotFuncional(informativo, 0);
                    EvidenciaIterator.finishEvidencia(true);
                    throw new ValidateException(testErro);
                }
                break;
            case "falha":
                if (b1) {
                    Evidencia.addPrintFailed(informativo);
                    ResultsLogger.isNotFuncional(informativo, 0);
                    throw new ValidateException(mensagem);
                }
                break;

            default:
                Evidencia.addPrintFailed(informativo);
                ResultsLogger.isNotFuncional(verificar, 0);
                EvidenciaIterator.finishEvidencia(true);
                throw new ValidateException(mensagem);
        }
    }


}

