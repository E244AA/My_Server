import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args){

        ExecutorService executeIt = Executors.newFixedThreadPool(1000);

        try {
            ServerSocket ss = new ServerSocket(80);

            while(!ss.isClosed()){
                Socket client = ss.accept();
                executeIt.execute(new MonoThreadServer(client));
            }
            ss.close();
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
