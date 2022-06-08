package loghandler;

import java.util.Objects;

public class OperationInfo {

    private int count = 0;
    private int successCount = 0;
    private int failedCount = 0;
    private long successAvgTime = 0;
    private long failedAvgTime = 0;

    public OperationInfo(int count, int successCount, int failedCount, long successAvgTime, long failedAvgTime) {
        this.count = count;
        this.successCount = successCount;
        this.failedCount = failedCount;
        this.successAvgTime = successAvgTime;
        this.failedAvgTime = failedAvgTime;
    }

    public OperationInfo() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incCount() {
        this.count++;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public void incSuccessCount(long time) {
        successAvgTime = (successAvgTime * successCount + time) / (successCount + 1);
        this.successCount++;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public void incFailedCount(long time) {
        failedAvgTime = (failedAvgTime * failedCount + time) / (failedCount + 1);
        this.failedCount++;
    }

    public long getSuccessAvgTime() {
        return successAvgTime;
    }

    public void setSuccessAvgTime(long successAvgTime) {
        this.successAvgTime = successAvgTime;
    }

    public long getFailedAvgTime() {
        return failedAvgTime;
    }

    public void setFailedAvgTime(long failedAvgTime) {
        this.failedAvgTime = failedAvgTime;
    }

    @Override
    public String toString() {
        return "OperationInfo{" +
                "count=" + count +
                ", successCount=" + successCount +
                ", failedCount=" + failedCount +
                ", successAvgTime=" + successAvgTime +
                ", failedAvgTime=" + failedAvgTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationInfo that = (OperationInfo) o;
        return getCount() == that.getCount() && getSuccessCount() == that.getSuccessCount() && getFailedCount() == that.getFailedCount() && getSuccessAvgTime() == that.getSuccessAvgTime() && getFailedAvgTime() == that.getFailedAvgTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCount(), getSuccessCount(), getFailedCount(), getSuccessAvgTime(), getFailedAvgTime());
    }

}
