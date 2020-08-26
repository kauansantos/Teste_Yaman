package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pageObject.DesafioYamanPage;

public class DesafioYamanStep {


    private DesafioYamanPage yamanPage = new DesafioYamanPage();

    @Dado("que eu acesse o site Shoestock")
    public void queEuAcesseOSiteShoestock() {

        yamanPage.acesseUrl();

    }

    @Quando("preencher na barra de busca o produto Sapato Social Couro Democrata Tompson")
    public void preencherNaBarraDeBuscaOProdutoSapatoSocialCouroDemocrataTompson() {

        yamanPage.PreenchendoProdutoNaBarraDeBusca();

    }

    @Quando("clicar na lupa para realizar a busca")
    public void clicarNaLupaParaRealizarABusca() {

        yamanPage.ClicandoNaLupaDePesquisa();

    }

    @Entao("devera ser apresentado resultado de busca")
    public void deveraSerApresentadoResultadoDeBusca() {

        yamanPage.ApresentandoResultadosDeBusca();

    }

    @Quando("clicar no Sapato Social Couro Democrata Tompson")
    public void clicarNoSapatoSocialCouroDemocrataTompson() {

        yamanPage.ClicandoNoSapato();

    }

    @Quando("selecionar o tamanho {int}")
    public void selecionarOTamanho(Integer int1) {

        yamanPage.SelecionandoTamanho();

    }

    @Quando("clicar em comprar")
    public void clicarEmComprar() {

        yamanPage.ClicandoEmComprar();

    }

    @Entao("devera apresentar a pagina Meu Carrinho com o sapato que selecionei")
    public void deveraApresentarAPaginaMeuCarrinhoComOSapatoQueSelecionei() {

        yamanPage.ExibindoPagMeuCarrinhoESapatoSelecionado();

    }


}
