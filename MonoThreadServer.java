import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoThreadServer implements Runnable{

    private static Socket sock;

    public MonoThreadServer(Socket client){
        sock = client;
    }

    @Override
    public void run() {

        try {
            System.out.println(sock.getInetAddress() + " is connected");

            DataInputStream in = new DataInputStream(sock.getInputStream());
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());

            while(!sock.isClosed()){
                String s = in.readUTF();
                out.writeUTF(Algol.xor(s,'a'));
            }
            in.close();
            out.close();
            sock.close();
        } catch (IOException e) {
            System.out.println(sock.getInetAddress() + " is disconnected");
        }


    }
}
