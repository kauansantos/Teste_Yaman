package utils.evidencia;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import util.selenium.InfraUtils;
import util.selenium.DateTimeUtils;
import util.selenium.*;

public class Cover {
	public static void insertCover() {
		insertLogon(Config.getProperty("path.upload.logon"));
		//insertObjective();
		Evidencia.addText("Data atual: " + DateTimeUtils.insertDateNow());
		Evidencia.addText("Hora atual: " + DateTimeUtils.insertTimeNow() + " - hora do Brasil");
		Evidencia.addText("Endereco IP: " + InfraUtils.getIpClient());
		Evidencia.addText("Usuario local ou de rede: " + InfraUtils.getWindowsUser());
		//Evidencia.addText("Nome do computador de execucao: " + InfraUtils.getPcName());
		Evidencia.addNewPage();
	}

	/*
	public static void insertObjective() {
		RepBene repositorio = new RepBene();
		String text = "Evidencia dos testes automatizados\n\n" + Evidencia.getNomeCt().toUpperCase().replace("_", "OdontoPrev");
		Evidencia.addFormatedText(text, FontFactory.HELVETICA, 20, Font.BOLDITALIC, BaseColor.BLUE, 40, 200,
				Element.ALIGN_CENTER);
	}*/

	public static void insertLogon(String logonPath) {
		Evidencia.addExternalImage(logonPath, Element.ALIGN_JUSTIFIED, 300f, 150f);
	}

}
