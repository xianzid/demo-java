package com.example.demo.model;

public class UserActionBean extends User{
    private int operationType;
    private int returnType;
    private String returnMsg;

    public UserActionBean(int operationType){
        this.operationType = operationType;
    }

    public UserActionBean(int returnType, String returnMsg){
        this.returnType = returnType;
        this.returnMsg = returnMsg;
    }

    public void setUser(User user){
        this.setUserName(user.getUserName());
        this.setPassword(user.getPassword());
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

}
