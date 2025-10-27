import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.LogRecord;

public class Log {
    // Formato personalizado para el logger
    static class LogFormatter extends java.util.logging.Formatter {
        @Override
        public String format(LogRecord record) {
            return record.getMessage() + "\n"; // Solo el mensaje
        }
    }

    private static Map<String, Logger> loggers = new HashMap<>();

    public static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            Logger logger = Logger.getLogger(name);

            // Asegurarse de que el directorio 'logs' exista
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            try {
                // Definir la ruta completa para el archivo de log
                FileHandler fileHandler = new FileHandler("logs/"+name+".log", false);
                fileHandler.setFormatter(new LogFormatter());
                logger.addHandler(fileHandler);

                // Manejador de consola
                ConsoleHandler consoleHandler = new ConsoleHandler();
                consoleHandler.setFormatter(new LogFormatter());
                logger.addHandler(consoleHandler);

                logger.setLevel(java.util.logging.Level.ALL);

            } catch (IOException e) {
                logger.severe("Error al configurar el FileHandler: " + e.getMessage());
            }

            loggers.put(name, logger);
        }
        return loggers.get(name);
    }

    public static void closeLoggers() {
        for (Logger logger : loggers.values()) {
            for (var handler : logger.getHandlers()) {
                handler.close();
            }
        }
        loggers.clear();
    }
}
