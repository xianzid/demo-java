package com.example.demo.test.socket.tcp.obj;

import com.example.demo.test.socket.commons.ServerInfo;
import com.example.demo.test.socket.tcp.obj.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            //创建服务器
            ServerSocket serverSocket = new ServerSocket(ServerInfo.PORT);
            System.out.println("～～～服务器已启动～～～");
            while (true){
                //阻塞等待客户端信息
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
