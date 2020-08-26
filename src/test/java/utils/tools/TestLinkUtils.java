package utils.tools;

import java.io.File;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIResults;
import utils.evidencia.Evidencia;
import utils.exceptions.ExceptionUtils;
import util.selenium.Config;

public class TestLinkUtils {

	private static String devkey;
	private static String url;

	/**
	 * Este_metodo_envia o status_para o test link. </br>
	 * </br><b>_devkey: </b>Chave_gerada por_usuario no_testlink
	 * </br><b>_url:    </b>URL do servidor_onde esta_instalado o_testlink
	 * </br><b>project:</b>Nome_do projeto_de_testes no_testlink
	 * </br><b>build:  </b>Nome_da build plan_criada no_testlink
	 * @param testPlan_Nome do_plano de_testes
	 * @param ct Nome_do caso_de_testes
	 * @param note Comentario_a_ser_inserido junto_ao status
	 * @param result TestLinkAPIResults.TEST_FAILED_ou TestLinkAPIResults.TEST_PASSED
	 */
	
	public static void setTestResult(String project, String build, String testPlan, String ct, String note,
			String result) {
		try {
			devkey = Config.getProperty("testlink.devkey");
			url = Config.getProperty("testlink.url");

			TestLinkAPIClient client = new TestLinkAPIClient(devkey, url);
			client.reportTestCaseResult(project, testPlan, ct, build, note, result);

			TestLinkAPIResults resultado = client.getLastExecutionResult(project, testPlan, ct);
			String executionId = resultado.getValueByName(0, "id").toString();

			uploadEvidence(executionId);

		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
	}

	/**
	 * Este_metodo_realiza o upload de_evidencia no_testlink.
	 * @param executionId Numero_id gerado_a cada_execucao. Pode_ser_vizualizado no
	 * test_exec step no_testlink
	 */
	public static void uploadEvidence(String executionId) {
		try {
			devkey = Config.getProperty("testlink.devkey");
			url = Config.getProperty("testlink.url");

			URL testlinkURL = new URL(url);
			TestLinkAPI api = new TestLinkAPI(testlinkURL, devkey);

			File attachmentFile = new File(Evidencia.getCaminho() + Evidencia.getNome());

			byte[] byteArray = FileUtils.readFileToByteArray(attachmentFile);
			String fileContent = new String(Base64.encodeBase64Chunked(byteArray));

			api.uploadExecutionAttachment(Integer.parseInt(executionId), "Evid�ncia de teste",
					"Durante a execu��o os prints foram colocados no documento .pdf em anexo", Evidencia.getNome(),
					"document/pdf", fileContent);
		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
	}

}
