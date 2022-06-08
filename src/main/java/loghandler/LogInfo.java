package loghandler;

import java.util.Objects;

public class LogInfo {

    private OperationInfo operationInfo;
    private OperationInfo authenticationInfo;
    private OperationInfo authorizationInfo;
    private OperationInfo balancesInfo;

    public LogInfo(OperationInfo operationInfo, OperationInfo authenticationInfo, OperationInfo authorizationInfo, OperationInfo balancesInfo) {
        this.operationInfo = operationInfo;
        this.authenticationInfo = authenticationInfo;
        this.authorizationInfo = authorizationInfo;
        this.balancesInfo = balancesInfo;
    }

    public LogInfo() {
        this.operationInfo = new OperationInfo();
        this.authenticationInfo = new OperationInfo();
        this.authorizationInfo = new OperationInfo();
        this.balancesInfo = new OperationInfo();
    }

    public OperationInfo getOperationInfo() {
        return operationInfo;
    }

    public void setOperationInfo(OperationInfo operationInfo) {
        this.operationInfo = operationInfo;
    }

    public OperationInfo getAuthenticationInfo() {
        return authenticationInfo;
    }

    public void setAuthenticationInfo(OperationInfo authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
    }

    public OperationInfo getAuthorizationInfo() {
        return authorizationInfo;
    }

    public void setAuthorizationInfo(OperationInfo authorizationInfo) {
        this.authorizationInfo = authorizationInfo;
    }

    public OperationInfo getBalancesInfo() {
        return balancesInfo;
    }

    public void setBalancesInfo(OperationInfo balancesInfo) {
        this.balancesInfo = balancesInfo;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "operationInfo=" + operationInfo +
                ", authenticationInfo=" + authenticationInfo +
                ", authorizationInfo=" + authorizationInfo +
                ", balancesInfo=" + balancesInfo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogInfo logInfo = (LogInfo) o;
        return Objects.equals(getOperationInfo(), logInfo.getOperationInfo()) && Objects.equals(getAuthenticationInfo(), logInfo.getAuthenticationInfo()) && Objects.equals(getAuthorizationInfo(), logInfo.getAuthorizationInfo()) && Objects.equals(getBalancesInfo(), logInfo.getBalancesInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperationInfo(), getAuthenticationInfo(), getAuthorizationInfo(), getBalancesInfo());
    }
}
