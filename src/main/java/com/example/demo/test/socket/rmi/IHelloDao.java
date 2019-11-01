package com.example.demo.test.socket.rmi;

import com.example.demo.dao.IDeptDao;
import com.example.demo.model.Dept;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface IHelloDao extends Remote {
    String sayHello(String msg) throws RemoteException;
}
