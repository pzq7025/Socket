package homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conn_db {


/**
 * 实现了数据库的封装以及对外操作
 * 操作数据库可以通过这个类来调用
 */
    /**
     * 通过建立连接的对象首先创造出mysql的连接属性
     * 所以定义了一个conn_db的一个类
     * 建立对mysql数据库的操作可以实现这种功能
     * 建立对外的接口
     * 是所有数据库操作的主类
     * 3306后面接的是一个数据库名
     */
    Connection con;
    String url = null;
//    String url1 = "jdbc:mysql://localhost:3306/my?useUnicode=true&characterEncoding=utf8";

// statement stmt;


    public void connection() throws ClassNotFoundException{
        // 3306/+【数据库名称】
        url = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String pwd = "123456";
        String driver="com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
//            System.out.println("connection is sucessful!");
            // stmt = con.createStatement();
//            con.close();
        }catch (SQLException e){
            System.out.println("link is fail!");
        }
    }

//    public static void main(String[] args) throws ClassNotFoundException{
//        conn_db conn_db1 = new conn_db();
//        conn_db1.connection();
//    }
}



