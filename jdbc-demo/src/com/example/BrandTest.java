package com.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.pojo.Brand;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 品牌数据的测试方法
 *
 * @author lbh
 * @create 2022-05-05 22:45
 */
public class BrandTest {
    /*
    查询所有
    1.SQL：select *from tb_brand
    2.参数不需要
    3.结果：List<Brand>
     */
    @Test
    public void testSelectAll() throws Exception {
//        System.out.println(System.getProperty("user.dir"));
        //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/com/druid.properties"));
        //4.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5.获取数据库连接Connection
        Connection conn = dataSource.getConnection();

        //2.定义SQL语句
        String sql = "select *from tb_brand";

        //3.获取pstm对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数

        //5.执行SQL
        ResultSet rs = pstmt.executeQuery();

        //6.处理结果List<Brand> 封装到Brand对象，装载到list集合
        Brand brand = null;
        List<Brand> brands = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String brand_name = rs.getString("brand_name");
            String company_name = rs.getString("company_name");
            int ordered = rs.getInt("ordered");
            String description = rs.getString("description");
            int status = rs.getInt("status");
            brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brand_name);
            brand.setCompanyName(company_name);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);

            brands.add(brand);
        }

        System.out.println(brands);

        //7.释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }

    /*
   查询所有
   1.SQL：insert into tb_brand(brand_name,company_name,ordered,description,status)
values(.....)
   2.参数:除id之外的所有信息
   3.结果：booleam
    */
    @Test
    public void testAdd() throws Exception {
        //接收页面提交的信息
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1;
        String description = "绕地球一圈";
        int status = 1;
//        System.out.println(System.getProperty("user.dir"));
        //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/com/druid.properties"));
        //4.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5.获取数据库连接Connection
        Connection conn = dataSource.getConnection();

        //2.定义SQL语句
        String sql = "insert into tb_brand(brand_name,company_name,ordered,description,status)\n" +
                "values(?,?,?,?,?)";

        //3.获取pstm对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数
        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);

        //5.执行SQL
        int count = pstmt.executeUpdate();

        //6.处理结果
        System.out.println(count);

        //7.释放资源

        pstmt.close();
        conn.close();
    }

    /*
修改信息
1.SQL：update
values(.....)
2.参数:所有信息
3.结果：booleam
 */
    @Test
    public void testUpdate() throws Exception {
        //接收页面提交的信息
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1;
        String description = "老逼登";
        int status = 1;
        int id = 4;
//        System.out.println(System.getProperty("user.dir"));
        //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/com/druid.properties"));
        //4.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5.获取数据库连接Connection
        Connection conn = dataSource.getConnection();

        //2.定义SQL语句
        String sql = "update tb_brand set brand_name=?,company_name=?,ordered=?,description=?" +
                ",status=? where id=?";

        //3.获取pstm对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数
        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);
        pstmt.setInt(6, id);

        //5.执行SQL
        int count = pstmt.executeUpdate();

        //6.处理结果
        System.out.println(count);

        //7.释放资源

        pstmt.close();
        conn.close();
    }

    /*
    修改信息
1.SQL：delete
    values(.....)
2.参数:所有信息
3.结果：booleam
 */
    @Test
    public void testDelete() throws Exception {
        //接收页面提交的信息
        int id = 4;
//        System.out.println(System.getProperty("user.dir"));
        //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/com/druid.properties"));
        //4.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5.获取数据库连接Connection
        Connection conn = dataSource.getConnection();

        //2.定义SQL语句
        String sql = "delete from tb_brand where id=?";

        //3.获取pstm对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数
        pstmt.setInt(1, id);

        //5.执行SQL
        int count = pstmt.executeUpdate();

        //6.处理结果
        System.out.println(count);

        //7.释放资源

        pstmt.close();
        conn.close();
    }
}
