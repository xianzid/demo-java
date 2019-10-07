package com.example.demo.test.socket.bio.server;

import com.example.demo.test.socket.commons.ServerInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单版服务器
 */
class ServerHandler{
    ServerSocket server;
    public ServerHandler() throws IOException{
        server = new ServerSocket(ServerInfo.PORT);
        this.clientConnect();
    }

    private void clientConnect() throws IOException{
        boolean serverFlag = true;
        while (serverFlag){
            Socket client = server.accept();
            Thread clientThread = new Thread(()->{
                try {
                    client.getInputStream();
                }catch (IOException e){

                }
            });
            clientThread.start();
            client.close();
        }
    }
}

public class Server {

    public static void main(String[] args) throws IOException {

        new ServerHandler();
    }
}
