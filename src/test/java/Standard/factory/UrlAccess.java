package Standard.factory;

import Standard.inspect.Inspecionador;
import Standard.status.HttpStatus;
import Standard.utils.readers.Config;

import java.util.Arrays;

public class UrlAccess {

    private HttpStatus erros = new HttpStatus();

    // ACESSA URL DEPENDENDO DO CENÁRIO

    /**
     * @param portal portal para o acesso, verificar a nomenclatura
     * @param teste  teste a ser feito, Exemplo: Login, Busca, Cadastro, Cancelamento etc...
     * @author Thiago de Moraes
     */
    public void setURL(String portal, String... teste) {
        try {
            if (teste == null) {
                Inspecionador.TipoTeste("sucesso", "Iniciando acesso ao " + portal.toUpperCase(), "inicial");
            } else {
                Inspecionador.TipoTeste("sucesso", "Iniciando acesso ao " + portal.toUpperCase() + " para teste de " + Arrays.toString(teste), "inicial");
            }

            String ambiente = System.getProperty("ambiente.teste");
            String url = Config.getProperty("url.portal." + portal.toLowerCase() + "." + ambiente);
            WebDriverFactory.getCurrentRunningDriver().get(url);
            erros.errorPage("inicial");
            Inspecionador.TipoTeste("sucesso", "Acessado a URL: " + url, "inicial");
        } catch (Exception e) {
            String erro = "Não foi possivel acessar o portal";
            Inspecionador.TipoTeste("erro", erro + "\n " + e, "inicial");

        }
    }
}
