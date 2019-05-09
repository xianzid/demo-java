package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserResponsitory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserResponsitoryTest {

    @Autowired
    private UserResponsitory userResponsitory;

    @Test
    public void test(){
        userResponsitory.save(new User("Rina","111"));
        Assert.assertEquals(1, userResponsitory.findAll().size());
        Assert.assertEquals("Rina", userResponsitory.findByUserName("Rina").getNickName());
        userResponsitory.delete(userResponsitory.findByUserName("Rina"));
    }

}
