package com.example.demo.test.socket.rmi;

import com.example.demo.test.socket.rmi.IHelloDao;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerRmi {
    public static void main(String[] args) {
        try {
            IHelloDao remote = new HelloDaoImpl();
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://127.0.0.1/Hello", remote);
            System.out.println("服务启动成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
