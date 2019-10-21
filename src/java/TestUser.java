import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.sql.*;

/**
 * @author: Administrator
 * @Date: 2019/10/16 14:04
 */
public class TestUser {

    public Connection getcon(){
        //1、加载数据库驱动
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2、连接数据库（需要用户名和密码以及数据库服务器连接地址）
            String url = "jdbc:mysql:///usersdb";
            String userName = "root";
            String password = "123";
            //通过连接数据库产生连接数据库的连接对象
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  conn;
    }

    /**
     * 查询数据
     * @throws Exception
     */
    @Test
    public void getUser(){
        Connection conn = null;
        //3、创建执行sql语句的对象
        Statement state = null;
        ResultSet rs = null;
        try {
            conn = getcon();
            state = conn.createStatement();
            //4、执行sql语句，返回结果集
            String sql = "select * from userdb";
            rs = state.executeQuery(sql);
            //5、处理结果集
            while(rs.next()){
                //表中字段的索引去获取数据
                String s1 = rs.getString(1);
                String s2 = rs.getString(2);
                //通过表中的字段名获取数据
                String s3 = rs.getString("pwd");
                String s4 = rs.getString("sex");
                System.out.println(s1+"\t"+s2+"\t"+s3+"\t"+s4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6、释放资源
            release(conn,state,rs);
        }

    }

    public void release( Connection conn,Statement state,ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(state != null){
                state.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改数据
     * @throws Exception
     */
    @Test
    public void updateUser() throws Exception{
        Connection conn = getcon();
        //3、创建执行sql语句的对象
        Statement state = conn.createStatement();
        //4、执行sql语句
        String sql = "update userdb set pwd='abc' where uname='淼哥'";
        int i = state.executeUpdate(sql);
        System.out.println("i="+i);
        //释放资源
        state.close();
        conn.close();
    }
}
