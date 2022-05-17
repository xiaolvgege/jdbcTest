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

/**
 * @author lbh
 * @create 2022-05-04 21:48
 */
public class JDBCDemo5_ResultSet {
    @Test
    public void testDML() throws  Exception{
        //1.注册驱动
        String driver="com.mysql.cj.jdbc.Driver";

        Class.forName(driver);

        //2.驱动连接
        String url="jdbc:mysql://192.168.28.203/db2";
        String username="root";
        String password="lvbinghao";
        Connection conn = DriverManager.getConnection(url, username, password);
        //定义sql
        String sql="select * from account";
        //4.获取statement对象
        Statement stmt = conn.createStatement();
        //5.执行sql
        ResultSet rs = stmt.executeQuery(sql);
        //6.处理结果，遍历rs中的所有数据
        while (rs.next()){
            int id=rs.getInt(1);
            String name=rs.getString(2);
            double money=rs.getDouble(3);

            System.out.print(id);
            System.out.print(name);
            System.out.print(money);
            System.out.println();
        }

        //7.释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
    //查询account表内的信息，封装为Account对象，并存储到Arraylist集合中
    @Test
    public void testDML2() throws  Exception{
        //1.注册驱动
        String driver="com.mysql.cj.jdbc.Driver";

        Class.forName(driver);

        //2.驱动连接
        String url="jdbc:mysql://192.168.28.203/db2";
        String username="root";
        String password="lvbinghao";
        Connection conn = DriverManager.getConnection(url, username, password);
        //定义sql
        String sql="select * from account";
        //4.获取statement对象
        Statement stmt = conn.createStatement();
        //5.执行sql
        ResultSet rs = stmt.executeQuery(sql);
        //创建集合
        List<Account> list=new ArrayList<>();

        //6.处理结果，遍历rs中的所有数据
        while (rs.next()){
            Account account=new Account();
            int id=rs.getInt(1);
            String name=rs.getString(2);
            double money=rs.getDouble(3);

            account.setId(id);
            account.setName(name);
            account.setMoney(money);
            //存入集合
            list.add(account);
        }
//        System.out.println(list);
        Iterator<Account> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //7.释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}
