package com.example.demo.test.socket.serializer;

import java.io.*;

/**
 * 序列化： 对象-->文件
 * 反序列化:文件-->对象
 */
public class FileSerializer implements ISerializer {
    @Override
    public <T> byte[] serializer(T obj) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File("student"));
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(fileOutputStream, objectOutputStream);
        }
        return null;
    }

    @Override
    public <T> T doSerializer(byte[] data, Class<T> classType) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("student"));
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(fileInputStream, objectInputStream);
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
