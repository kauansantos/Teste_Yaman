package utils.evidencia;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

import com.itextpdf.text.pdf.PdfWriter;

import util.selenium.*;
import utils.exceptions.ExceptionUtils;

public class EvidenciaIterator {

	/**
	 * Este_metodo_tem a funcao_de_preparar e_salvar a_evidencia_em_um
	 * local_indicado
	 */
	public static void setPathToSaveEvidence(String path) {
		try {
			String date = LocalDate.now().toString();
			String time = LocalTime.now().toString();

			date = DateTimeUtils.formataData(date, "").replace("/", "");
			String hora = time.substring(0, 2);
			String minutos = time.substring(3, 5);
			String segundos = time.substring(6, 8);

			time = hora + minutos + segundos;

			File diretorio = new File(path);
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}

			Evidencia.setNomeEvidencia(
					Evidencia.getNomeCt() + "_" + System.getProperty("ambiente.teste") + "_" + date + time + ".pdf");

			Evidencia.setCaminho(path);

			PdfWriter.getInstance(Evidencia.getDocument(),
					new FileOutputStream(Evidencia.getCaminho() + Evidencia.getNome()));
		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
	}

	/**
	 * Este_metodo deve_ser_chamado para_finalizar o report, ao_fim da_execucao
	 */
	public static void finishEvidencia(boolean falha) {

		Evidencia.getDocument().close();
		String novoNome = Evidencia.getNome().replace(".pdf", "");

		if (falha == true) {
			novoNome = novoNome + "__FAILED.pdf";
		} else {
			novoNome = novoNome + "__PASSED.pdf";
		}

		new File(Evidencia.getCaminho() + Evidencia.getNome()).renameTo(new File(Evidencia.getCaminho() + novoNome));
		Evidencia.setNomeEvidencia(novoNome);
	}

}
