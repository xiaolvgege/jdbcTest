package com.jdbc;

import org.junit.Test;

import java.sql.*;

/**用户登录
 *
 * @author lbh
 * @create 2022-05-04 21:48
 */
public class JDBCDemo7_PreparedStatement {
    @Test
    public void testDML() throws  Exception{
        //1.注册驱动
        String driver="com.mysql.cj.jdbc.Driver";

        Class.forName(driver);

        //2.驱动连接
        String url="jdbc:mysql://127.0.0.1:3306/db2?useServerPrepStmts=true";
        String username="root";
        String password="lvbinghao";
        Connection conn = DriverManager.getConnection(url, username, password);

        //接收用户名密码
        String name="zhangsan";
        String pwd="123";

        //定义sql
        String sql="select * from tb_user where username=? and password=?";

        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置？的值
        pstmt.setString(1,name);
        pstmt.setString(2,pwd);

        //执行sql
        ResultSet rs = pstmt.executeQuery();

        //判断登录是否成功
        if (rs.next()){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
        //7.释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }
}
