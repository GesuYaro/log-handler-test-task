package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JAALogParser implements LogParser {

    @Override
    public LogMessage parseToLogMessage(String str) throws ParseException {
        String[] rawParts = str.split("[\\[\\]]", 3);
        if (rawParts.length < 3) {
            throw new IllegalArgumentException("Wrong format of string");
        }
        LogMessage logMessage = new LogMessage();
        logMessage.setDate(parseDate(rawParts[0]));
        logMessage.setThreadName(rawParts[1]);
        parseMessage(rawParts[2], logMessage);

        return logMessage;
    }

    private Date parseDate(String str) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return format.parse(str);
    }

    private ProcessType parseProcessType(String str) {
        str = str.trim().toUpperCase();
        return ProcessType.valueOf(str);
    }

    private ProcessState parseProcessResult(String str) {
        str = str.trim().toUpperCase();
        return ProcessState.valueOf(str);
    }

    private int parseProcessId(String str) {
        return Integer.parseInt(str);
    }

    private void parseMessage(String message, LogMessage logMessage) {
        String[] rawMessageParts = message.trim().split(" ");
        if (rawMessageParts.length < 2) {
            throw new IllegalArgumentException("Wrong format of message");
        }
        logMessage.setProcessType(parseProcessType(rawMessageParts[0]));
        logMessage.setProcessState(parseProcessResult(rawMessageParts[rawMessageParts.length - 1].replace(".", "")));

        if (logMessage.getProcessType().equals(ProcessType.OPERATION)) {
            logMessage.setProcessId(getIdFromMessage(message));
        }

    }

    private int getIdFromMessage(String message) {
        String[] partsWithId = message.split("[\\[\\]]", 3);
        Pattern pattern = Pattern.compile("\\[id=(\\d+?)]");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return parseProcessId(matcher.group(1));
        } else {
            throw new IllegalArgumentException("Wrong format of message. Can't find process id");
        }
    }

}
