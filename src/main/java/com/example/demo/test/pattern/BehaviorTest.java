package com.example.demo.test.pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class BehaviorTest {
    public static void main(String[] args) {

    }

}

/**
 * 责任链模式
 */
class chainTest implements javax.servlet.Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (true)
            return;
        //before
        filterChain.doFilter(servletRequest, servletResponse);//执行下一个节点
        //after
    }
}
