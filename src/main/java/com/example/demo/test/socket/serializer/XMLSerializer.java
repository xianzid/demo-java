package com.example.demo.test.socket.serializer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XML序列化
 * 使用开源框架xstream
 */
public class XMLSerializer implements ISerializer {

    XStream xStream = new XStream(new DomDriver());

    @Override
    public <T> byte[] serializer(T obj) {
        return xStream.toXML(obj).getBytes();
    }

    @Override
    public <T> T doSerializer(byte[] data, Class<T> classType) {
        return (T) xStream.fromXML(new String(data));
    }
}
