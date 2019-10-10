package com.example.demo.test.socket.tcp.obj;

import com.example.demo.model.UserActionBean;
import com.example.demo.tools.CloseUtils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;

    ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        UserActionBean user;
        try {
            /*
            与客户端的交互
            1。客户端登陆1/注册0/修改信息2/上传文件3（User，operationType）
            2。服务端回复（注册1成功+成功信息0失败+失败信息，登陆1成功+欢迎信息0失败+失败信息。。。）
             */
            user = (UserActionBean)(new ObjectInputStream(socket.getInputStream()).readObject());
            int operationType = user.getOperationType();
            switch (operationType){
                case 0:
                    //注册
                    user.setReturnType(1);
                    user.setReturnMsg("注册成功，请登陆！");
                    break;
                case 1:
                    //登陆
                    user.setReturnType(1);
                    user.setReturnMsg("欢迎您：" + user.getUserName());
                    break;
                case 2:
                    //修改
                    user.setReturnType(1);
                    user.setReturnMsg("修改成功！");
                    break;
                case 3:
                    //上传
                    break;
                default:
                    //其他
                    user.setReturnType(0);
                    user.setReturnMsg("错误指令！");
                    break;
            }

            new ObjectOutputStream(socket.getOutputStream()).writeObject(user);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeAuto(socket);
        }
    }
}
