package main;

import loghandler.JAALogHandler;
import loghandler.LogHandler;
import loghandler.LogInfo;
import parser.JAALogParser;
import util.LogInfoPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        if (args != null && args.length > 0) {
            File file = new File(args[0]);
            try {
                Scanner scanner = new Scanner(file);
                LogHandler logHandler = new JAALogHandler(new JAALogParser());
                LogInfo logInfo = logHandler.handle(scanner);
                LogInfoPrinter.print(logInfo);
            } catch (FileNotFoundException e) {
                System.err.println("File not found");
            }
        }

    }

}
