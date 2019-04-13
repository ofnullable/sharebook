package org.slam.utils;

public class TransactionUtils {

    public static int isSuccess(int... results) {
        for (int i : results) {
            if (i <= 0) return -1;
        }
        return 1;
    }

}
