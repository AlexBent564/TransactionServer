/*
TransactionManager.java
-has transaction manager workers
-open up one connection per transaction, stays open until a close transaction is received. 
-Read/Write requests handled by TransactionWorker
*/

public class TransactionManager
{
    // Constructor
    public TransactionManager()
    {

    }

    // Run function for creating worker threads
    public void run()
    {
        TransactionManagerWorker worker = new TransactionManagerWorker();
        worker.start();
    }
}