
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transaction.comm.Message;
import transaction.comm.MessageTypes;

// Transaction Server Proxy class
//Transaction client hits proxy, then the proxy server manages workers.
//represents transaction, which spins threads.
public class TServerProxy implements MessageTypes
{
  String host = null;
  int port;

  private Socket dbConnection = null;
  private ObjectOutputStream writeToNet = null;
  private ObjectInputStream readFromNet = null;
  private Integer transID = 0;

  TServerProxy(String host, int port) throws Exception
  {
    this.host = host;
    this.port = port;
  }

  public int openTransaction()
  {
    try {
      // open new ServerSocket object with port number '8080'
      ServerSocket server = new ServerSocket(8080);

      // counter variable for tracking the number of client connections
      int counter = 0;

      // confirm server has started
      System.out.println("Server started...");

      // echo server runs until manually terminated by server user
      while (true) {
          // increment counter to account for new client connection
          counter++;

          // server accepts the client connection request
          Socket serverClient = server.accept();

          // control message for when a client connects
          System.out.println("Client No: " + counter + " connected");

          // create a new thread for new client; pass in client socket and counter number
          EchoThread newEchoThread = new EchoThread(serverClient, counter);

          // start newly created thread
          newEchoThread.start();
      }
  } catch (Exception ex) {
      System.out.println("An Exception");
  }
  }

  public void closeTransaction()
  {

  }

  public int read(int accountNumber)
  {
    Message readMessage = new Message(READ_REQUEST, accountNumber);
    Integer balance = null;

    try
    {
      writeToNet.writeObject(readMessage);
      balance = (Integer) readFromNet.readObject();
    }
    catch (Exception ex)
    {
      System.out.println("[TServerProxy.read] Error occured." );
      ex.printStackTrace();
    }

    return balance;

  }

  public int write(int accountNumber, int amount)
  {
    Message writeMessage = new Message( WRITE_REQUEST, accountNumber );
    Integer balance = null;

    try 
    {
      // TODO
    } 
    catch (Exception ex) 
    {
      System.out.println( "[TServerProxy.write] Error occured." );
      ex.printStackTrace();
    }

    return balance;
  }

}
