package com.example.demo.test.jdbc;

import com.example.demo.tools.CloseUtils;

import java.sql.*;

public class JdbcTest {
    private static String URL="jdbc:mysql://localhost:3306/lhb";

    private static Connection connection = null;
    private static Statement statement = null;

    public static void main(String[] args) {
        initPreConnect();
    }

    private static void test1(){
        try {
            initDBConnect();
            insertInfo();
            queryUserName();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭DB连接");
            CloseUtils.closeAuto(statement, connection);
        }
    }

    private static void insertInfo() throws SQLException {
        statement.execute("insert into tbl_user(id, username, password, usertype, nickname, regtime) values ('100001', 'Tom','11111', '1' , '', "+System.currentTimeMillis() +")");
    }

    private static void queryUserName() {
        ResultSet rs = null;
        try {
            //3。执行SQL
            rs = statement.executeQuery("select * from tbl_user");
            System.out.println("查询完成");
            //4。结果处理
            while (rs.next()) {
                String username = rs.getString("username");
                System.out.println("UserName: " + username);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            CloseUtils.closeAuto(rs);
        }
    }

    /**
     *
     * @throws ClassNotFoundException || SQLException
     * 抛出处理，在外层捕获并确保能关闭
     */
    private static void initDBConnect() throws ClassNotFoundException, SQLException {
        //1。加载驱动包：手动加载相应数据库的驱动包
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2。建立连接：通过驱动与对应（URL（jdbc:数据库类型://IP:端口号/数据库名（不同数据库会有差别））、用户名、密码）数据库连接
        //          URL可定义出来，做实时更新（分布式思想）
        //setAutoCommit值默认是true自动提交，关闭需要手动设置
        connection = DriverManager.getConnection( URL , "root", "123456");
        //此处可建立预连接，增加参数后再提交
        statement = connection.createStatement();
    }

    private static void initPreConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root" , "123456");
            PreparedStatement statement = connection.prepareStatement("update tbl_user set username = ? where id = ?");
            statement.setString(1, "Lim");
            statement.setLong(2, 100001);
            int rs = statement.executeUpdate();
            System.out.printf("update is %s", (-1 != rs) ? "successful" : "failed");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
