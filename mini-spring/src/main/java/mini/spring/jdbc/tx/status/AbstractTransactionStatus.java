/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.status;


/**
 * AbstractTransactionStatus.
 * 记录当前事务的状态
 *
 * @author Carl, 2023-08-24 15:31
 */
public abstract class AbstractTransactionStatus implements TransactionStatus {

    private boolean rollbackOnly = false;

    private boolean completed = false;

    public boolean isRollbackOnly() {
        return rollbackOnly;
    }

    public void setRollbackOnly(boolean rollbackOnly) {
        this.rollbackOnly = rollbackOnly;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
