package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author lbh
 * @create 2022-05-04 21:48
 */
public class JDBCDemo2_DriverManger {
    public static void main(String[] args) throws Exception {
        //1.注册驱动
//        String driver="com.mysql.cj.jdbc.Driver";
//        Class.forName(driver);

        //2.驱动连接:如果连接的是本机的mysql并且端口是默认的3306可以简化书写
        String url="jdbc:mysql:///db2";
        String username="root";
        String password="lvbinghao";
        Connection conn = DriverManager.getConnection(url, username, password);
        //3.定义sql
        String sql="update account set money=2000 where id=1";

        //4.获取执行的sql的对象Statement
        Statement stmt = conn.createStatement();

        //5.执行sql
        int count=stmt.executeUpdate(sql);//受影响的行数

        //6.处理结果
        System.out.println(count);

        //7.释放资源
        stmt.close();
        conn.close();
    }
}
