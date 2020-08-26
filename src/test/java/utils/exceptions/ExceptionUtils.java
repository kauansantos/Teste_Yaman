package utils.exceptions;

import org.apache.log4j.Logger;

public class ExceptionUtils {
	
	public static final Logger logger = Logger.getLogger(ExceptionUtils.class);
	
	public static void throwException(Exception exception) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(exception.getMessage() + "\n");
		for(StackTraceElement trace : exception.getStackTrace()) {
			stringBuilder.append(trace.toString() + "\n");
		}
		
		logger.error(stringBuilder.toString());
		throw new ValidateException(stringBuilder.toString());
	}
	
}
