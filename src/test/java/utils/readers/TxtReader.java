package utils.readers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import utils.exceptions.ExceptionUtils;

public class TxtReader {
	
	/**
	 * Este_metodo_retorna_uma_lista_de strings_de_um arquivo .txt
	 */
	public static List<String> fileInLines(String pathFile) {
		
		Path path = Paths.get(pathFile);

		List<String> linhasArquivo = null;
		
		try {
			linhasArquivo = Files.readAllLines(path);
		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
		
		return linhasArquivo;	
	}
	
	/**
	 * Este_metodo retorna_uma string com_todos caracteres_de um_arquivo_.txt
	 */
	public static String fileInLine(String pathFile) {
		
		List<String> linhasArquivo = fileInLines(pathFile);
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (String linha : linhasArquivo) {
			stringBuilder.append(linha + " ");
		}
		
		return stringBuilder.toString();
	} 
	
}
