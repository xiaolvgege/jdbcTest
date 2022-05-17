package com.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author lbh
 * @create 2022-05-04 21:48
 */
public class JDBCDemo4_Statement {
    //执行DQL查询语句
    @Test
    public void testDML() throws Exception {
        //1.注册驱动
        String driver = "com.mysql.cj.jdbc.Driver";

        Class.forName(driver);

        //2.驱动连接
        String url = "jdbc:mysql://192.168.28.203/db2";
        String username = "root";
        String password = "lvbinghao";
        Connection conn = DriverManager.getConnection(url, username, password);
        //3.定义sql
        String sql = "update account set money=2000 where id=1";

        //4.获取执行的sql的对象Statement
        Statement stmt = conn.createStatement();

        //5.执行sql
        int count = stmt.executeUpdate(sql);//受影响的行数

        //6.处理结果
//        System.out.println(count);
        if (count > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
        //7.释放资源
        stmt.close();
        conn.close();
    }
}