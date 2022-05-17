package com.jdbc;

import com.pojo.Account;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**用户登录
 *
 * @author lbh
 * @create 2022-05-04 21:48
 */
public class JDBCDemo6_UserLogin {
    @Test
    public void testDML() throws  Exception{
        //1.注册驱动
        String driver="com.mysql.cj.jdbc.Driver";

        Class.forName(driver);

        //2.驱动连接
        String url="jdbc:mysql://127.0.0.1:3306/db2";
        String username="root";
        String password="lvbinghao";
        Connection conn = DriverManager.getConnection(url, username, password);

        //接收用户名密码
        String name="zhangsan";
        String pwd="123";

        String sql="select * from tb_user where username='"+name+"'and password='"+pwd+"'";

        //获取stmt对象
        Statement stmt = conn.createStatement();

        //执行sql
        ResultSet rs = stmt.executeQuery(sql);

        //判断登录是否成功
        if (rs.next()){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
        //7.释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}
