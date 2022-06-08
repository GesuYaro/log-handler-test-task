package parser;

import java.util.Date;
import java.util.Objects;

public class LogMessage {

    private Date date;
    private String threadName;
    private ProcessType processType;
    private int processId;
    private ProcessState processState;

    public LogMessage() {
    }

    public LogMessage(Date date, String threadName, ProcessType processType, int processId, ProcessState processState) {
        this.date = date;
        this.threadName = threadName;
        this.processType = processType;
        this.processId = processId;
        this.processState = processState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public ProcessState getProcessState() {
        return processState;
    }

    public void setProcessState(ProcessState processState) {
        this.processState = processState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogMessage that = (LogMessage) o;
        return getProcessId() == that.getProcessId() && Objects.equals(getDate(), that.getDate()) && Objects.equals(getThreadName(), that.getThreadName()) && Objects.equals(getProcessType(), that.getProcessType()) && getProcessState() == that.getProcessState();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getThreadName(), getProcessType(), getProcessId(), getProcessState());
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "date=" + date +
                ", threadName='" + threadName + '\'' +
                ", processName='" + (processType != null ? processType.toString() : "null") + '\'' +
                ", processId=" + processId +
                ", processResult=" + (processState != null ? processState.toString() : "null") +
                '}';
    }
}
