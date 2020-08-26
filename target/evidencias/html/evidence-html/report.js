$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/features/Login.feature");
formatter.feature({
  "name": "Login no Novo Portal Beneficarios",
  "description": "",
  "keyword": "Funcionalidade",
  "tags": [
    {
      "name": "@Regressivo"
    }
  ]
});
formatter.scenario({
  "name": "Login com Sucesso",
  "description": "",
  "keyword": "Cenario",
  "tags": [
    {
      "name": "@Regressivo"
    },
    {
      "name": "@TestSuc"
    },
    {
      "name": "@hml"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "que eu acesse o portal beneficiarios",
  "keyword": "Dado "
});
formatter.match({
  "location": "LoginStep.queEuAcesseOPortalBeneficiarios()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "clicar em acessar minha conta",
  "keyword": "Quando "
});
formatter.match({
  "location": "LoginStep.clicarEmAcessarMinhaConta()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "preencher cpf ou carteirinha",
  "keyword": "E "
});
formatter.match({
  "location": "LoginStep.preencherCpfOuCarteirinha()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "preencher senha",
  "keyword": "E "
});
formatter.match({
  "location": "LoginStep.preencherSenha()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "clicar em entrar",
  "keyword": "E "
});
formatter.match({
  "location": "LoginStep.clicarEmEntrar()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "devera apresentar o modal de teleorientacao",
  "keyword": "Entao "
});
formatter.match({
  "location": "LoginStep.deveraApresentarOModalDeTeleorientacao()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "o protocolo de acesso",
  "keyword": "E "
});
formatter.match({
  "location": "LoginStep.oProtocoloDeAcesso()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});