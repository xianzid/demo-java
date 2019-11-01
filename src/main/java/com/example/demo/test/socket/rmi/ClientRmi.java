package com.example.demo.test.socket.rmi;

import com.example.demo.test.socket.rmi.IHelloDao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientRmi {
    public static void main(String[] args) {
        try {
            IHelloDao remote = (IHelloDao) Naming.lookup("rmi://127.0.0.1/Hello");
            System.out.println(remote.sayHello("Bob"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
