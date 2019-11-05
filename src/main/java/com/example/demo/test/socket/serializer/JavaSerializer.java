package com.example.demo.test.socket.serializer;

import java.io.*;

public class JavaSerializer implements ISerializer {
    @Override
    public <T> byte[] serializer(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(byteArrayOutputStream, objectOutputStream);
        }
        return null;
    }


    @Override
    public <T> T doSerializer(byte[] data, Class<T> classType) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(byteArrayInputStream, objectInputStream);
        }
        return null;
    }

    private void close(Closeable... closeStreams) {
        for (Closeable closeStream : closeStreams) {
            if (closeStream != null) {
                try {
                    closeStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
