package loghandler;

import parser.LogMessage;
import parser.LogParser;
import parser.ProcessState;
import parser.ProcessType;

import java.text.ParseException;
import java.util.*;

public class JAALogHandler implements LogHandler {

    private LogParser logParser;
    private Map<String, ArrayDeque<LogMessage>> threadMap;
    private Map<String, LastStates> lastStatesMap;

    public JAALogHandler(LogParser logParser) {
        this.logParser = logParser;
    }

    @Override
    public LogInfo handle(Scanner scanner) {
        long lineNumber = 0;
        threadMap = new HashMap<>();
        lastStatesMap = new HashMap<>();
        LogInfo logInfo = new LogInfo();
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;
                LogMessage log = logParser.parseToLogMessage(line);
                handleLogMessage(log, logInfo);
            }
        } catch (ParseException | IllegalArgumentException e) {
                System.err.println("Problem in line " + lineNumber + ". " + e.getMessage());
        }
        return logInfo;
    }

    private void handleLogMessage(LogMessage logMessage, LogInfo logInfo) {
        String threadName = logMessage.getThreadName();
        if (!threadMap.containsKey(threadName)) {
            threadMap.put(threadName, new ArrayDeque<>());
            lastStatesMap.put(threadName, new LastStates());
        }
        if (checkLogQueue(logMessage)) {
            handleTime(logMessage, logInfo);
        } else {
            throw new IllegalArgumentException("Incorrect log queue");
        }
    }

    private void handleTime(LogMessage logMessage, LogInfo logInfo) {
        if (logMessage.getProcessState().equals(ProcessState.STARTED)) {
            threadMap.get(logMessage.getThreadName()).addLast(logMessage);
        } else {
            LogMessage prevMessage = threadMap.get(logMessage.getThreadName()).pollLast();
            long time = logMessage.getDate().getTime() - prevMessage.getDate().getTime();
            switch (logMessage.getProcessType()) {
                case OPERATION:
                    logInfo.getOperationInfo().incCount();
                    if (logMessage.getProcessState().equals(ProcessState.FINISHED)) {
                        logInfo.getOperationInfo().incSuccessCount(time);
                        logInfo.getBalancesInfo().incSuccessCount(lastStatesMap.get(logMessage.getThreadName()).getBalancesTime());
                        logInfo.getAuthorizationInfo().incSuccessCount(lastStatesMap.get(logMessage.getThreadName()).getAuthorizationTime());
                        logInfo.getAuthenticationInfo().incSuccessCount(lastStatesMap.get(logMessage.getThreadName()).getAuthenticationTime());
                    } else {
                        logInfo.getOperationInfo().incFailedCount(time);
                        logInfo.getBalancesInfo().incFailedCount(lastStatesMap.get(logMessage.getThreadName()).getBalancesTime());
                        logInfo.getAuthorizationInfo().incFailedCount(lastStatesMap.get(logMessage.getThreadName()).getAuthorizationTime());
                        logInfo.getAuthenticationInfo().incFailedCount(lastStatesMap.get(logMessage.getThreadName()).getAuthenticationTime());
                    }
                    break;

                case BALANCES:
                    logInfo.getBalancesInfo().incCount();
                    lastStatesMap.get(logMessage.getThreadName()).setBalancesTime(time);
                    break;

                case AUTHORIZATION:
                    logInfo.getAuthorizationInfo().incCount();
                    lastStatesMap.get(logMessage.getThreadName()).setAuthorizationTime(time);
                    break;

                case AUTHENTICATION:
                    logInfo.getAuthenticationInfo().incCount();
                    lastStatesMap.get(logMessage.getThreadName()).setAuthenticationTime(time);
                    break;
            }
        }
    }

    private boolean checkLogQueue(LogMessage logMessage) {
        if (threadMap.get(logMessage.getThreadName()).isEmpty()) {
            return logMessage.getProcessType().equals(ProcessType.OPERATION) // new operation in empty stack
                    && logMessage.getProcessState().equals(ProcessState.STARTED);
        } else {
            LogMessage prevMessage = threadMap.get(logMessage.getThreadName()).peekLast();
            if (prevMessage.getProcessState().equals(ProcessState.STARTED)) {
                if (prevMessage.getProcessType().equals(ProcessType.OPERATION)) {
                    if (logMessage.getProcessType().equals(ProcessType.OPERATION)) {
                        return !logMessage.getProcessState().equals(ProcessState.STARTED);
                    } else {
                        return logMessage.getProcessState().equals(ProcessState.STARTED);
                    }
                } else {
                    return logMessage.getProcessType().equals(prevMessage.getProcessType())
                            && !logMessage.getProcessState().equals(ProcessState.STARTED);
                }
            } else {
                if (prevMessage.getProcessType().equals(ProcessType.OPERATION)) {
                    return logMessage.getProcessState().equals(ProcessState.STARTED)
                            && logMessage.getProcessType().equals(ProcessType.OPERATION);
                } else if (logMessage.getProcessType().equals(ProcessType.OPERATION)) {
                    return !logMessage.getProcessState().equals(ProcessState.STARTED);
                } else {
                    return logMessage.getProcessState().equals(ProcessState.STARTED);
                }
            }
        }
    }

    private static class LastStates {

        private long operationTime;
        private long authorizationTime;
        private long authenticationTime;
        private long balancesTime;

        public long getOperationTime() {
            return operationTime;
        }

        public void setOperationTime(long operationTime) {
            this.operationTime = operationTime;
        }

        public long getAuthorizationTime() {
            return authorizationTime;
        }

        public void setAuthorizationTime(long authorizationTime) {
            this.authorizationTime = authorizationTime;
        }

        public long getAuthenticationTime() {
            return authenticationTime;
        }

        public void setAuthenticationTime(long authenticationTime) {
            this.authenticationTime = authenticationTime;
        }

        public long getBalancesTime() {
            return balancesTime;
        }

        public void setBalancesTime(long balancesTime) {
            this.balancesTime = balancesTime;
        }
    }

}
