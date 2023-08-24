/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.manager;


import mini.spring.jdbc.tx.TransactionException;
import mini.spring.jdbc.tx.status.SimpleTransactionStatus;
import mini.spring.jdbc.tx.status.TransactionStatus;

/**
 * PlatformTransactionManager.
 *
 * @author Carl, 2023-08-24 15:16
 */
public abstract class AbstractPlatformTransactionManager implements PlatformTransactionManager {

    private TransactionStatus transactionStatus;

    @Override
    public TransactionStatus getTransaction() throws TransactionException {
        Object transaction = doGetTransaction();
//        // Check definition settings for new transaction.
//        if (def.getTimeout() < TransactionDefinition.TIMEOUT_DEFAULT) {
//            throw new InvalidTimeoutException("Invalid transaction timeout", def.getTimeout());
//        }
//
//        // No existing transaction found -> check propagation behavior to find out how to proceed.
//        if (def.getPropagationBehavior() == TransactionDefinition.PROPAGATION_MANDATORY) {
//            throw new IllegalTransactionStateException(
//                    "No existing transaction found for transaction marked with propagation 'mandatory'");
//        }
//        else if (def.getPropagationBehavior() == TransactionDefinition.PROPAGATION_REQUIRED ||
//                def.getPropagationBehavior() == TransactionDefinition.PROPAGATION_REQUIRES_NEW ||
//                def.getPropagationBehavior() == TransactionDefinition.PROPAGATION_NESTED) {
//            SuspendedResourcesHolder suspendedResources = suspend(null);
//            if (debugEnabled) {
//                logger.debug("Creating new transaction with name [" + def.getName() + "]: " + def);
//            }
//            try {
//                return startTransaction(def, transaction, debugEnabled, suspendedResources);
//            }
//            catch (RuntimeException | Error ex) {
//                resume(null, suspendedResources);
//                throw ex;
//            }
//        }
//        else {
//            // Create "empty" transaction: no actual transaction, but potentially synchronization.
//            if (def.getIsolationLevel() != TransactionDefinition.ISOLATION_DEFAULT && logger.isWarnEnabled()) {
//                logger.warn("Custom isolation level specified but no actual transaction initiated; " +
//                        "isolation level will effectively be ignored: " + def);
//            }
//            boolean newSynchronization = (getTransactionSynchronization() == SYNCHRONIZATION_ALWAYS);
//            return prepareTransactionStatus(def, null, true, newSynchronization, debugEnabled, null);
//        }
        return transactionStatus;
    }

    @Override
    public void commit(TransactionStatus transactionStatus) throws TransactionException {
        processCommit((SimpleTransactionStatus) transactionStatus);
    }

    private void processCommit(SimpleTransactionStatus transactionStatus) {
        try {
            if (transactionStatus.isNewTransaction()) {
                doCommit(transactionStatus);
            }
        } catch (Exception e) {

        } finally {

        }
    }

    @Override
    public void rollback(TransactionStatus t) throws TransactionException {


    }

    protected abstract void doCommit(SimpleTransactionStatus status) throws TransactionException;


    protected abstract Object doGetTransaction() throws TransactionException;

}
