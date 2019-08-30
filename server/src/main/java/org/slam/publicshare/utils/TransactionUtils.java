package org.slam.publicshare.utils;

public class TransactionUtils {

    /**
     * @param results count of updated result
     * @return if success all of updates, return 1 else return 0
     */
    public static int isSuccess(int... results) {
        for (int i : results) {
            if (i <= 0) return -1;
        }
        return 1;
    }

}
