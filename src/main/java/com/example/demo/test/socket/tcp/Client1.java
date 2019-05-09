package com.example.demo.test.socket.tcp;

import com.example.demo.model.User;
import com.example.demo.model.UserActionBean;
import com.example.demo.tools.CloseUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            //客户端启动
            socket = new Socket("LocalHost",8880);
            System.out.println("～～～客户端已启动～～～");

            //用户注册
            UserActionBean userActionBean = new UserActionBean(0);
            userActionBean.setUser(new User("Tom","111111"));
            System.out.println("用户注册");
            new ObjectOutputStream(socket.getOutputStream()).writeObject(userActionBean);

            //服务端返回信息
            userActionBean = (UserActionBean)new ObjectInputStream(socket.getInputStream()).readObject();
            System.out.println(userActionBean.getReturnMsg());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeAuto(socket);
        }

    }
}
