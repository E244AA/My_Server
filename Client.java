import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost",80);
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            while(!client.isClosed()){
                String s = r.readLine();
                out.writeUTF(s);
                System.out.println(in.readUTF());
            }

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
