package com.example.demo.test.socket.serializer.clone;

import java.io.*;

public class PlanWork implements Serializable, Cloneable {
    private String workName;
    private Email email;

    //加transient关键字会不对该属性做序列化
    private transient String decode;

    //手动对属性decode做序列化
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(decode);
    }

    //手动对属性decode做反序列化
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        decode = (String) objectInputStream.readObject();
    }

    public PlanWork(String workName) {
        this.workName = workName;
    }

    public PlanWork(String workName, Email email) {
        this.workName = workName;
        this.email = email;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getDecode() {
        return decode;
    }

    public void setDecode(String decode) {
        this.decode = decode;
    }

    /**
     * 浅克隆
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected PlanWork clone() throws CloneNotSupportedException {
        return (PlanWork)super.clone();
    }

    /**
     * 深克隆，修改引用对象中数据不会影响被克隆对象
     * 利用序列化和反序列化来实现
     * @return
     * @throws IOException
     */
    public PlanWork deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        return (PlanWork) objectInputStream.readObject();
    }

    @Override
    public String toString() {
        return "PlanWork{" +
                "workName='" + workName + '\'' +
                ", email=" + email +
                ", decode='" + decode + '\'' +
                '}';
    }
}
