package com.example.demo.test.socket.bio.client;

import com.example.demo.test.socket.commons.ServerInfo;
import com.example.demo.test.socket.util.InputUtil;
import com.example.demo.tools.CloseUtils;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端的处理器
 */
class EchoClientHandle implements AutoCloseable {
    private Socket client;

    public EchoClientHandle() throws Exception {
        //处理器初始化客户端并连接到服务器
        this.client = new Socket(ServerInfo.ECHO_SERVER_HOST, ServerInfo.PORT);
        System.out.println("已经成功的连接到了服务器端，可以进行消息的发送处理。");
        //数据交互
        this.accessServer();
    }

    /**
     * 数据通信
     * @throws Exception
     */
    private void accessServer() throws Exception {
        //接收器，用native方法跟踪接收服务端发来的信息
        Scanner scan = new Scanner(this.client.getInputStream()) ;  // 服务器端的输出为客户端的输入
        PrintStream out = new PrintStream(this.client.getOutputStream()) ; // 向服务器端发送内容
        scan.useDelimiter("\n") ;
        boolean flag = true ;
        while(flag) {
            String data = InputUtil.getString("请输入要发送的数据信息：") ;
            out.println(data); // 先把内容发送到服务器端上
            if ("exit".equalsIgnoreCase(data)) {
                flag = false ; // 结束循环s
            }
            //接收服务端发来的信息（记录读取位置position，内存不够要申请，读取放入内存），并打印出来
            if (scan.hasNext()) {
                System.out.println(scan.next());
            }
        }
    }

    @Override
    public void close() throws Exception {
        this.client.close();
    }
}

/**
 * 客户端
 */
public class EchoClient {
    public static void main(String[] args) {
        EchoClientHandle echo = null;
        try {
            echo = new EchoClientHandle();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeAuto(echo);
        }
    }
}
