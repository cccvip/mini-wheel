/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.status;


import com.sun.istack.internal.Nullable;

/**
 * SimpleTransactionStatus.
 * 返回当前事务状态信息
 *
 * @author Carl, 2023-08-24 15:34
 */
public class SimpleTransactionStatus extends AbstractTransactionStatus {

    @Nullable
    private final Object transaction;

    private final boolean newTransaction;

    public SimpleTransactionStatus(Object transaction, boolean newTransaction) {
        this.transaction = transaction;
        this.newTransaction = newTransaction;
    }

    public boolean isNewTransaction() {
        return newTransaction;
    }


    public Object getTransaction() {
        return transaction;
    }
}
