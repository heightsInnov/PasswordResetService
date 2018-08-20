package com.union.bof.utilities;


import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.Logger;

public class LoggingUtil {
	private static Logger logger = null;

	public static void logDebugInfo(String debugInfo, Logger _loggerIntsance)
        {
		logger = _loggerIntsance;
		logger.debug(debugInfo);
	}
	
	public static void logInfo(String info, Logger _loggerIntsance){
		logger = _loggerIntsance;
		logger.info(info);
	}
	
	public static void logError(String error, Logger _loggerIntsance){
		logger = _loggerIntsance;
		logger.error(error);
	}
	
	public static void logWarning(String warn, Logger _loggerIntsance){
		logger = _loggerIntsance;
		logger.warn(warn);
	}

        public static void logError(Exception ex, Logger _loggerIntsance){
		logger = _loggerIntsance;
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String stacktrace = sw.toString();
		logger.error(stacktrace);
	}

       public static void logThrowable(String message, Exception t, Logger _loggerIntsance){
		logger = _loggerIntsance;
        logger.info(message, t);
		
	}
}
