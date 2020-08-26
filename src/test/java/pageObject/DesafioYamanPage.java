package pageObject;

import factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import repositorios.RepDesafioYaman;
import util.selenium.CssIterator;
import util.selenium.audiUtil;

public class DesafioYamanPage {

    private audiUtil util = new audiUtil();
    private RepDesafioYaman ry = new RepDesafioYaman();
    private WebElement driver;
    private int time = 4500;

    public void acesseUrl() {

        WebDriverFactory.getCurrentRunningDriver().get(ry.url);
        util.wait(2000);
        boolean testUrl;
        testUrl = util.testElements(ry.XpathLogoShoestock);
        if (testUrl) {

            CssIterator.markWebElement(WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathLogoShoestock)));
            util.wait(3000);
            CssIterator.markOffWebElement(WebDriverFactory.getCurrentRunningDriver(), WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathLogoShoestock)));

        } else {

            util.Close();

        }

    }


    public void PreenchendoProdutoNaBarraDeBusca(){


        // MODAL DE DESCONTO INSERIDO DIA 26/08
        WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathFecharModalDeDesconto)).click();

        boolean BuscandoProduto;
        BuscandoProduto = util.testElements(ry.XpathBarraDePesquisa);
        if (BuscandoProduto) {

            util.wait(2000);
            WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathBarraDePesquisa)).click();
            WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathBarraDePesquisa)).sendKeys(ry.NomeSapato);
            CssIterator.markWebElement(WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathBarraDePesquisa)));
            util.wait(2000);
            CssIterator.markOffWebElement(WebDriverFactory.getCurrentRunningDriver(), WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathBarraDePesquisa)));

        } else {

            util.Close();

        }
    }

    public void ClicandoNaLupaDePesquisa(){

        boolean ClicandoNaLupa;
        ClicandoNaLupa = util.testElements(ry.XpathLupaDePesquisa);
        if (ClicandoNaLupa) {

            util.wait(2000);
            CssIterator.markWebElement(WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathLupaDePesquisa)));
            CssIterator.markOffWebElement(WebDriverFactory.getCurrentRunningDriver(), WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathLupaDePesquisa)));
            WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathLupaDePesquisa)).click();

        } else {

            util.Close();

        }
    }

    public void ApresentandoResultadosDeBusca(){

        boolean ResultadoDeBusca;
        ResultadoDeBusca = util.testElements(ry.XpathResultadoDeBusca);
        if (ResultadoDeBusca) {

            util.wait(2000);
            CssIterator.markWebElement(WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathResultadoDeBusca)));
            util.wait(2000);
            CssIterator.markOffWebElement(WebDriverFactory.getCurrentRunningDriver(), WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathResultadoDeBusca)));

        } else {

            util.Close();

        }
    }

    public void ClicandoNoSapato(){

        boolean ClicandoNoSapato;
        ClicandoNoSapato = util.testElements(ry.XpathNomeSapato);
        if (ClicandoNoSapato) {

            util.wait(2000);
            util.scroolPositivoCurto();
            CssIterator.markWebElement(WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathNomeSapato)));
            util.wait(2000);
            CssIterator.markOffWebElement(WebDriverFactory.getCurrentRunningDriver(), WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathLupaDePesquisa)));
            WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathNomeSapato)).click();
            util.wait(3000);

        } else {

            util.Close();

        }
    }

    public void SelecionandoTamanho(){

        boolean SelecionandoTamanho;
        SelecionandoTamanho = util.testElements(ry.XpathTamanhoDoSapato);
        if (SelecionandoTamanho) {

            util.wait(2000);
            CssIterator.markWebElement(WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathTamanhoDoSapato)));
            util.wait(2000);
            CssIterator.markOffWebElement(WebDriverFactory.getCurrentRunningDriver(), WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathTamanhoDoSapato)));
            WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathTamanhoDoSapato)).click();
            util.wait(3000);

        } else {

            util.Close();
        }
    }

    public void ClicandoEmComprar(){

        boolean ClicandoEmComprar;
        ClicandoEmComprar = util.testElements(ry.XpathBotaoComprar);
        if (ClicandoEmComprar) {

            util.wait(2000);
            CssIterator.markWebElement(WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathBotaoComprar)));
            util.wait(2000);
            CssIterator.markOffWebElement(WebDriverFactory.getCurrentRunningDriver(), WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathBotaoComprar)));
            WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathBotaoComprar)).click();
            util.wait(3000);

        } else {

            util.Close();
        }
    }

    public void ExibindoPagMeuCarrinhoESapatoSelecionado(){

        boolean MeuCarrinho;
        MeuCarrinho = util.testElements(ry.XpathProdutoNoCarrinho);
        if (MeuCarrinho) {

            CssIterator.markWebElement(WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathTituloMeuCarrinho)));
            util.wait(2000);
            CssIterator.markOffWebElement(WebDriverFactory.getCurrentRunningDriver(), WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathTituloMeuCarrinho)));
            CssIterator.markWebElement(WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathProdutoNoCarrinho)));
            util.wait(2000);
            CssIterator.markOffWebElement(WebDriverFactory.getCurrentRunningDriver(), WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(ry.XpathProdutoNoCarrinho)));
            util.wait(3000);

        } else {

            util.Close();
        }
    }


}
