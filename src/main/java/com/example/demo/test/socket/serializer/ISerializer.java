package com.example.demo.test.socket.serializer;

public interface ISerializer {
    <T> byte[] serializer(T obj);
    <T> T doSerializer(byte[] data, Class<T> classType);
}
