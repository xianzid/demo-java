package com.example.demo.test.jdbc;

import com.example.demo.tools.CloseUtils;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class JdbcTest {
    private static String URL="jdbc:mysql://localhost:3306/lhb";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL,"root","123456");
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from tbl_user");
            System.out.println("查询完成");
            while (rs.next()) {
                String username = rs.getString("username");
                System.out.println("UserName: " + username);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            CloseUtils.closeAuto(rs,statement,connection);
        }
    }

}
