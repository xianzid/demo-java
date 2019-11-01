package com.example.demo.test.socket.rmi;

import com.example.demo.test.socket.rmi.IHelloDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloDaoImpl extends UnicastRemoteObject implements IHelloDao {
    protected HelloDaoImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String msg) throws RemoteException {
        return "你好, " + msg;
    }
}
