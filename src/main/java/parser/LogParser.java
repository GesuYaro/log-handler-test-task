package parser;

import java.text.ParseException;

public interface LogParser {

    LogMessage parseToLogMessage(String str) throws ParseException;

}
