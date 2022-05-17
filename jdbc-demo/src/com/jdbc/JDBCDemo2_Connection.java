package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author lbh
 * @create 2022-05-04 21:48
 */
public class JDBCDemo2_Connection {
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
        String sql1="update account set money=3000 where id=1";
        String sql2="update account set money=3000 where id=2";
        //4.获取执行的sql的对象Statement
        Statement stmt = conn.createStatement();

        try {
            //开启事务
            conn.setAutoCommit(false);
            //5.执行sql
            int count1=stmt.executeUpdate(sql1);//受影响的行数

            int i=3/0;
            //6.处理结果
            System.out.println(count1);

            int count2=stmt.executeUpdate(sql2);//受影响的行数
            //6.处理结果
            System.out.println(count2);

            //提交事务
            conn.commit();
        } catch (Exception e) {
            //回滚事务
            conn.rollback();
            e.printStackTrace();
        }

        //7.释放资源
        stmt.close();
        conn.close();
    }
}
