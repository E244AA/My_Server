import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebCore {

    public static int PORT = 8888;

    private static ServerSocket SERVER_SOCKET;

    private ExecutorService executeIt = Executors.newFixedThreadPool(1000);

    public void setPORT(int PORT) {
        WebCore.PORT = PORT;
    }

    public void startServer() throws java.io.IOException{
        SERVER_SOCKET = new ServerSocket(PORT);

        while(!SERVER_SOCKET.isClosed()){
            Socket client = SERVER_SOCKET.accept();
            executeIt.execute(new MultiThread(client));
        }

    }

    public void shutdownServer() throws IOException{
        SERVER_SOCKET.close();
        executeIt.shutdownNow();
    }

    public void restartServer() throws IOException{
        shutdownServer();
        startServer();
    }

}

class MultiThread implements Runnable{

    private Socket sock;
    private DataInputStream in;
    private DataOutputStream out;

    public MultiThread(Socket client) throws IOException{
        sock = client;
        in = new DataInputStream(sock.getInputStream());
        out = new DataOutputStream(sock.getOutputStream());
    }

    @Override
    public void run() {

        System.out.println(sock.getInetAddress() + " is connected");

        while(!sock.isClosed()){
               // Doing something
        }

        try {
            in.close();
            out.close();
            sock.close();
        }catch (IOException e){

        }

    }
}
