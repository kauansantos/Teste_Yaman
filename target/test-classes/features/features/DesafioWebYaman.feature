#language: pt
#coding: utf-8
#Author: Kauan Domingos dos Santos

Funcionalidade: Teste Yaman incluindo produtos no carrinho no site Shoestock

  @Yaman
  Cenario: Incluindo produtos no carrinho
    Dado que eu acesse o site Shoestock
    Quando preencher na barra de busca o produto Sapato Social Couro Democrata Tompson
    E clicar na lupa para realizar a busca
    Entao devera ser apresentado resultado de busca
    Quando clicar no Sapato Social Couro Democrata Tompson
    E selecionar o tamanho 41
    E clicar em comprar
    Entao devera apresentar a pagina Meu Carrinho com o sapato que selecionei