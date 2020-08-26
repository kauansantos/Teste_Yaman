package repositorios;

import org.openqa.selenium.support.FindBy;

public class RepDesafioYaman {

    @FindBy
    public String url = "http://www.shoestock.com.br";

    @FindBy
    public static String XpathLogoShoestock = "//h1[@class='logo image']";

    @FindBy
    public static String XpathBarraDePesquisa = "//input[@id='search-input']";

    @FindBy
    public static String NomeSapato = "Sapato Social Couro Democrata Tompson";

    @FindBy
    public static String XpathLupaDePesquisa = "//button[@type='submit'][contains(.,'Buscar')]";

    @FindBy
    public static String XpathResultadoDeBusca = "//h1[@class='search-query'][contains(.,'Resultados de busca para \"Sapato Social Couro Democrata Tompson\"')]";

    @FindBy
    public static String XpathNomeSapato = "(//span[contains(.,'Sapato Social Couro Democrata Tompson')])[1]";

    @FindBy
    public static String XpathTamanhoDoSapato = "(//a[@class='product-item'][contains(.,'41')])[1]";

    @FindBy
    public static String XpathBotaoComprar = "//button[@id='buy-button-now']";

    @FindBy
    public static String XpathTituloMeuCarrinho = "//h1[@class='cart__title'][contains(.,'Meu carrinho')]";

    @FindBy
    public static String XpathProdutoNoCarrinho = "//h3[@qa-auto='product-name'][contains(.,'Sapato Social Couro Democrata Tompson')]";

    @FindBy
    public static String XpathFecharModalDeDesconto = "//*[@id=\"bg-sombra-floater\"]/div/div[2]/span";

}
