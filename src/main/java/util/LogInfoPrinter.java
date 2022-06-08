package util;

import loghandler.LogInfo;

public class LogInfoPrinter {

    public static void print(LogInfo logInfo) {
        System.out.println("Successful operation count: " + logInfo.getOperationInfo().getSuccessCount());
        System.out.println("Average operation successful processing time: " + logInfo.getOperationInfo().getSuccessAvgTime() + " ms\n");
        System.out.println("Failed operation count: " + logInfo.getOperationInfo().getFailedCount());
        System.out.println("Average operation failed processing time: " + logInfo.getOperationInfo().getFailedAvgTime() + " ms\n");
        System.out.println("Average Authentication time (for successful operations): " + logInfo.getAuthenticationInfo().getSuccessAvgTime() + " ms");
        System.out.println("Average Authentication time (for failed operations): " + logInfo.getAuthenticationInfo().getFailedAvgTime() + "ms\n");
        System.out.println("Average Authorization time (for successful operations): " + logInfo.getAuthorizationInfo().getSuccessAvgTime() + " ms");
        System.out.println("Average Authorization time (for failed operations): " + logInfo.getAuthorizationInfo().getFailedAvgTime() + " ms\n");
        System.out.println("Average Balance modification time (for successful operations): " + logInfo.getBalancesInfo().getSuccessAvgTime() + " ms");
        System.out.println("Average Balance modification time (for failed operations): " + logInfo.getBalancesInfo().getFailedAvgTime() + " ms");
    }

}
