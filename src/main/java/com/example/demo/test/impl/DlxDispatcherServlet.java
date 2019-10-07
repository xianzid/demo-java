package com.example.demo.test.impl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 模拟Spring.DispatcherServlet
 */
public class DlxDispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //1。加载配置文件

        //2。根据配置文件扫描所有相关的类

        //3。初始化类实例，放入IOC容器（Map<String,Object>）中

        //4。自动依赖注入

        //5。HanlerMapper初始化映射


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        //6。等待请求
    }
}
