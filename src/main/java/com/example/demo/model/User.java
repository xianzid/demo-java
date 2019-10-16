package com.example.demo.model;

import com.example.demo.properties.UserType;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

@Entity
public class User implements Serializable {
    private static final long seriaVersionUID = 1l;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String password;

    @Column
    private String nickName;

    @Column(nullable = false)
    private String regTime;

    @Enumerated
    @Column
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Transient
    private UserType userTypeStr;

    private BigDecimal account;

    public User(){}

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.nickName = userName;
        //TODO 待优化，这样的写法有性能问题
        regTime = getRegTimeStr();
    }

    public User(String userName, String password, String nickName){
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
    }

    public String getRegTimeStr(){
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        return dateFormat.format(date);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserTypeStr() {
        return userTypeStr;
    }

    public void setUserTypeStr(UserType userTypeStr) {
        this.userTypeStr = userTypeStr;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }
}
