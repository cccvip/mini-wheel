/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.status;


import com.sun.istack.internal.Nullable;
import mini.spring.jdbc.tx.manager.PlatformTransactionManager;

/**
 * TransactionInfo.
 *
 * @author Carl, 2023-08-24 15:53
 */
public class TransactionInfo {

    @Nullable
    private final PlatformTransactionManager transactionManager;

    private final String joinpointIdentification;

    @Nullable
    private TransactionStatus transactionStatus;

    @Nullable
    private TransactionInfo oldTransactionInfo;

    public TransactionInfo(PlatformTransactionManager transactionManager, String joinpointIdentification) {
        this.transactionManager = transactionManager;
        this.joinpointIdentification = joinpointIdentification;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public String getJoinpointIdentification() {
        return joinpointIdentification;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionInfo getOldTransactionInfo() {
        return oldTransactionInfo;
    }

    public void setOldTransactionInfo(TransactionInfo oldTransactionInfo) {
        this.oldTransactionInfo = oldTransactionInfo;
    }
}
