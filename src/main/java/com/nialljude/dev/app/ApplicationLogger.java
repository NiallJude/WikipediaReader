package com.nialljude.dev.app;

import java.io.IOException;
import java.util.logging.*;

public class ApplicationLogger {


    // Logging variables.
    private static Logger logger = Logger.getLogger(ApplicationLogger.class.getName());
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    private final String loggerPath = "logging.txt";

    /**
     *
     * @return The Logger
     */
    public void setup() {
        // get the global logger to configure it
        logger = Logger.getLogger(Main.class.getName());

        // suppress the logging output to the console
        // output only to Logging.txt
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        // Log at Information level
        logger.setLevel(Level.INFO);

        try {
            fileTxt = new FileHandler(loggerPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
        logger.info("Logger set up at "+loggerPath);
    }

    public Logger getLogger() {
        return logger;
    }
}
