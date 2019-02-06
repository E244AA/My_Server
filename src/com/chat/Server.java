package com.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static int PORT = 8888;
    private static ServerSocket SERVER_SOCKET;
    private ExecutorService executeIt = Executors.newFixedThreadPool(1000);

    public void setPORT(int PORT) {
        Server.PORT = PORT;
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
    private InetAddress ClientInetAddress;

    public MultiThread(Socket client) throws IOException{
        sock = client;
        in = new DataInputStream(sock.getInputStream());
        out = new DataOutputStream(sock.getOutputStream());
        ClientInetAddress = sock.getInetAddress();
    }

    @Override
    public void run() {

        System.out.println(ClientInetAddress + " is connected");

        while(!sock.isClosed()){

            try {

                String message = in.readUTF();
                System.out.println(sock.getInetAddress().getCanonicalHostName() + " : " + message);
                out.writeUTF("Good : " + message);

            } catch (IOException e) {
                try {
                    sock.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }

        System.out.println(ClientInetAddress + " is disconnected");

        try {
            in.close();
            out.close();
            sock.close();
        }catch (IOException e){

        }

    }
}
