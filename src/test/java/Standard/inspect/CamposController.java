package Standard.inspect;

import Standard.inspect.Inspecionador;
import Standard.utils.others.SeleniumUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

//-------MAPEIA A TELA DE EDIÇÃO DE EMPRESA------//
public class CamposController {

    private int QT_CamposValidados;
    private int QTBancoVazio;
    private int QTTelaVazia;
    private int QT_CamposDivergentes;
    private int QTXpathBroken;

    private List<String> Nm_Campo_Validado = new ArrayList<>();
    private List<String> Nm_Campo_TelaVazia = new ArrayList<>();
    private List<String> Nm_Campo_BDVazio = new ArrayList<>();
    private List<String> Nm_Campo_Divergente = new ArrayList<>();
    private List<String> Nm_Campo_Quebrado = new ArrayList<>();
    private List<String> Nm_Xpath_Campo = new ArrayList<>();

    /**
     * VERIFICA OS CAMPOS CONFORME O NOME
     *
     * @param banco   : O dado que é esperado ser apresentado em tela
     * @param nmCampo : Nome do campo (xpath). O xpath deve ser 'public static
     *                String' na classe {@code CamposReposit}
     * @author Thiago de Moraes
     **/
    public void verCampo(String banco, String xpathCampo, String nmCampo) {
        WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
        try {
            if (SeleniumUtils.isWebElement(xpathCampo)) {

                if (banco == null || "".equals(banco)) {
                    Nm_Campo_BDVazio.add("Campo: " + nmCampo.toUpperCase());
                    QTBancoVazio++;
                    if (cod.getAttribute("value") == null || " ".equals(cod.getAttribute("value").trim())
                            || "".equals(cod.getAttribute("value").trim())) {
                        Inspecionador.TipoTeste("sucesso",
                                "O campo " + nmCampo.toUpperCase() + " está vazio em tela e no banco de dados: OK ", "meio");
                        Nm_Campo_Validado.add("Campo: " + nmCampo.toUpperCase());
                        QT_CamposValidados++;
                    } else {
                        Inspecionador.TipoTeste("falha", "O campo '" + nmCampo.toUpperCase()
                                        + "' está vazio no Banco de Dados, mas em tela: " + cod.getAttribute("value"),
                                "final");
                        QTBancoVazio++;
                        Nm_Campo_Divergente.add("\n Campo: " + nmCampo.toUpperCase() + " Banco: " + banco + " Tela: "
                                + cod.getAttribute("value"));
                        QT_CamposDivergentes++;
                    }
                } else {
                    if (cod.getAttribute("value") == null || " ".equals(cod.getAttribute("value").trim())
                            || "".equals(cod.getAttribute("value").trim())) {
                        Inspecionador.TipoTeste("falha", "O campo '" + nmCampo.toUpperCase() + "' está preenchido no Banco de Dados: " + banco + " mas está vazio em tela",
                                "meio");
                        Nm_Campo_TelaVazia.add("Campo: " + nmCampo.toUpperCase());
                        QTTelaVazia++;
                        Nm_Campo_Divergente.add("\n Campo: " + nmCampo.toUpperCase() + " Banco: " + banco + " Tela: "
                                + cod.getAttribute("value"));
                        QT_CamposDivergentes++;

                    } else if (cod.getAttribute("value").trim().equals(banco) || cod.getText().equals(banco)) {
                        Inspecionador.TipoTeste("sucesso", "O campo '" + nmCampo.toUpperCase() + "' foi validado",
                                "meio");

                        Nm_Campo_Validado.add("Campo: " + nmCampo.toUpperCase());
                        QT_CamposValidados++;
                    } else {
                        Inspecionador.TipoTeste("falha", "O campo '" + nmCampo.toUpperCase() + "' não confere", "meio");
                        Nm_Campo_Divergente.add("\n Campo: " + nmCampo.toUpperCase() + " Banco: " + banco + " Tela: "
                                + cod.getAttribute("value"));
                        QT_CamposDivergentes++;
                    }
                }
            } else {
                Nm_Xpath_Campo.add("\n Campo " + nmCampo.toUpperCase() + "xpath: " + xpathCampo);
                QTXpathBroken++;
                Inspecionador.TipoTeste("falha", "O campo '" + nmCampo.toUpperCase() + "' não confere", "meio");

            }
        } catch (Exception e) {
            String erro = "Não foi possivel validar o campo: " + nmCampo.toUpperCase();
            Nm_Campo_Quebrado.add("Campo: " + nmCampo.toUpperCase());
            Nm_Campo_Divergente.add("\n Campo: " + nmCampo.toUpperCase() + " Banco: " + banco + " Tela: "
                    + cod.getAttribute("value"));
            QT_CamposDivergentes++;
            Inspecionador.TipoTeste("falha", erro + " " + e, "meio");
        }
    }

    /*public void calculaVazios() {

        String MsgBancoVazio = "Existe(m) " + QTBancoVazio + " campo(s) não preenchido(s) no Banco de Dados: " + Nm_Campo_Vazio;
        String MsgTelaVazia = "Existe(m) " + QTTelaVazia + " campo(s) divergente(s) do Banco de Dados: " + Nm_Campo_Divergente;
        String MsgCamposQuebrados = "Existe(m) " + QT_CamposDivergentes + " campo(s) com dados quebrado(s) na tela: " + Nm_Campo_Quebrado;

        String MsgTodosCasos = "Foram identificados diversos erros";
        String MsgBancoVazio_TelaPossui = "Foram identificados " + QTBancoVazio + " vazios do Banco de Dados" + "\n e " + QTTelaVazia + " preenchidos na Tela";
        String MsgTelaVazia_BancoPossui = "Foram identificados " + QTBancoVazio + " vazios do Banco de Dados" + "\n e " + QTTelaVazia + " preenchidos na Tela";

        if (QTBancoVazio > 0 && QTTelaVazia > 0 && QT_CamposDivergentes > 0) {
            Inspecionador.TipoTeste("falha", MsgTodosCasos, "final");

        } else if (QTBancoVazio > 0 && QTTelaVazia > 0 && QT_CamposDivergentes == 0) {
            Inspecionador.TipoTeste("falha", MsgBancoVazio + "\n" + MsgTelaVazia, "final");

        } else if (QTBancoVazio > 0 && QT_CamposDivergentes > 0) {
            Inspecionador.TipoTeste("falha", MsgBancoVazio + "\n" + MsgCamposQuebrados, "final");

        } else if (QT_CamposDivergentes > 0 && QTTelaVazia > 0) {
            Inspecionador.TipoTeste("falha", MsgCamposQuebrados + "\n" + MsgTelaVazia, "final");

        } else if (QTBancoVazio == 0) {
            if (QTTelaVazia == 0) {
                if (QT_CamposDivergentes == 0) {
                    Inspecionador.TipoTeste("sucesso", "Todos os campos foram validados com sucesso", "meio");
                } else {
                    Inspecionador.TipoTeste("falha", MsgCamposQuebrados, "final");
                }
            } else {
                Inspecionador.TipoTeste("falha", MsgTelaVazia, "final");
            }
        } else {
            Inspecionador.TipoTeste("falha", MsgBancoVazio, "final");
        }
    }*/

    public void CalculosCampos() {

        //Mensagens do Log
        String CalcDivergentes = "Existe(m) " + QT_CamposDivergentes + " Divergente(s) entre Banco de Dados e Tela ";

        String CalcQuebrados = "Existe(m) " + QTXpathBroken + " campo(s) com dados quebrado(s) na tela ";

        String CalcBDVazio = "Existe(m) " + QTBancoVazio + " Vazio(s) no Banco de Dados ";

        String CalcTelaVazio = "Existe(m) " + QTTelaVazia + " Vazio(s) na Tela ";

        String CalcValidados = "Existe(m) " + QT_CamposValidados + " campo(s) validado(s) com sucesso ";


        if (Nm_Campo_BDVazio.size() == Nm_Campo_TelaVazia.size() && QTBancoVazio != 0) {
            if (Nm_Campo_BDVazio.equals(Nm_Campo_TelaVazia)) {
                Inspecionador.getMessage(CalcTelaVazio + "\n e " + CalcBDVazio +
                        "\n Campo(s) vazio(s) de Tela:" + Nm_Campo_TelaVazia +
                        "\n Campo(s) vazio(s) de Banco de Dados:" + Nm_Campo_BDVazio);
            }
            if (QTXpathBroken > 0) {
                Inspecionador.getMessage(CalcQuebrados + "\n Campo(s) Quebrado(s): " + Nm_Campo_Quebrado);
                Inspecionador.getMessage("Xpath dos campos quebrados: " + Nm_Xpath_Campo);
            }
            if (QTBancoVazio > 0) {
                Inspecionador.getMessage(CalcBDVazio + "\n Campo(s) " + Nm_Campo_BDVazio);
            }
            if (QTTelaVazia > 0) {
                Inspecionador.getMessage(CalcTelaVazio + "\n Campo(s) " + Nm_Campo_TelaVazia);
            }
            if (QT_CamposDivergentes > 0) {
                Inspecionador.getMessage(CalcDivergentes + "\n Campo(s) Divergente(s): " + Nm_Campo_Divergente);
            }
            if (QT_CamposValidados > 0) {
                Inspecionador.getMessage(CalcValidados + " Sendo: " + Nm_Campo_Validado);
            }
        }
    }

    public void CampoClick(String xpathCampo, String nmCampo) {
        try {
            SeleniumUtils.isWebElement(xpathCampo);
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
           // Inspecionador.TipoTeste("sucesso",
                  //  "Realizada a ação de clicar no campo '" + nmCampo.toUpperCase() + "'", "meio");
            cod.click();
        } catch (Exception e) {
            String erro = "Não foi possível clicar no campo: " + nmCampo.toUpperCase();
            //Inspecionador.TipoTeste("falha", erro + " " + e, "meio");
        }
    }

    /**
     * @param dados      os dados a serem inseridos no campo desejado
     * @param xpathCampo xpath do campo
     * @param nmCampo    nome para a {@link Standard.utils.evidencia.Evidencia} do campo
     * @author Thiago de Moraes;
     */
    public void CampoSend(String dados, String xpathCampo, String nmCampo) {
        try {
            SeleniumUtils.isWebElement(xpathCampo);
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
            if (cod.getAttribute("value") == null || " ".equals(cod.getAttribute("value").trim())
                    || "".equals(cod.getAttribute("value").trim())) {
                Inspecionador.TipoTeste("sucesso",
                        "Realizada a ação de inserir dados no campo '" + nmCampo.toUpperCase() + "' ", "meio");
                cod.sendKeys(dados);
            } else {
                Inspecionador.TipoTeste("falha",
                        "O campo '" + nmCampo.toUpperCase() + "' não pode ser editado, já está preenchido", "final");
            }
        } catch (Exception e) {
            String erro = "Não foi possivel inserir dados no campo: " + nmCampo.toUpperCase();
            Inspecionador.TipoTeste("falha", erro + " " + e, "meio");
        }
    }
}
