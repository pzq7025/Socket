package homework;


import javax.swing.*;
import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class DataProcess extends conn_db{
    // 初始状态为true
    private static boolean connectDB = true;
    // 获取user的名字 账号  进行哈希匹配
    static Hashtable<String, User> users;
    // 获取docs的名字  账号  进行哈希匹配
    static Hashtable<String, Doc> docs;

    static Connection connection = null;
    static ResultSet resultSet = null;
    static Statement statement = null;
    // 用来控制更新数据的方式  因为数据更新的方式为整型
    static int rw;

    static {
        // 构建哈希表的静态成员变量
        users = new Hashtable<String, User>(500);
//        users.put("Jack", new Browser("Jack", "123", "Browser"));
//        users.put("Rose", new Administrator("Rose", "123", "Administrator"));
//        users.put("Petry", new Operator("Petry", "123", "Operator"));


        // 定义文件集的哈希表   以及获取时间戳
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        docs = new Hashtable<String, Doc>(500);
//        docs.put("0001", new Doc("0001", "jack", timestamp, "Doc Source Java", "Doc.txt"));


    }

    // 调控connecttion的连接
    public static void Init() throws SQLException {
        // 连接数据库  对数据进行操作
        // 直接掉
        conn_db cd = new conn_db();
        try {
            cd.connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection connection = cd.con;
        statement = connection.createStatement();

    }


    // dataProcess的哈希匹配方法  进行对象调用  就可以完成对应的哈希匹配
    // 正确返回对应的对象信息 错误返回失败null
    public static User search(String name, String password) throws SQLException {
        // 添加对数据库的判断
        // 如果不能连接数据库
//        if (!connectDB){
//            throw new SQLException("Not Connection is Error!");
//        }
//
//        double ranVal = Math.random();
//        if (ranVal > 1000){
//            throw new SQLException("Error in excecuting Query!");
//        }
        // 连接数据库对数据库进行操作
        User_DB();
        if (users.containsKey(name)) {
            User temp = users.get(name);
            if ((temp.getPassword()).equals(password))
                return temp;
        }
        return null;
    }



    // 显示用户信息  与GetAll配对使用
    public static void showInfo() throws SQLException {
        // Show all balances in hash table.
        Enumeration<User> e = DataProcess.getAllUser();
        User user;
        try {
            System.out.println("Information:");
            // 通过获取的getAll获取到用户的信息
            while (e.hasMoreElements()) {
                user = e.nextElement();
                System.out.println("Name:" + user.getName() + "\tPassword:" + user.getPassword() + "\tRole:" + user.getRole());
            }
        } catch (Exception e1){
            e1.printStackTrace();
        }

    }


    // 获取枚举类
    // 获取用户的全部信息
    public static Enumeration<User> getAllUser() throws SQLException{
        // 对数据库的连接做判断
//        if (!connectDB){
//            throw new SQLException("Not Connection DataBase!");
//        }
//        double ranVal = Math.random();
//        if (ranVal > 1000){
//            throw new SQLException("Error in excecuting Query");
//        }
        // 将信息返还给调用的函数
        Enumeration<User> e  = users.elements();
        return e;
    }


    // 更新用户的数据信息
    public static boolean update(String name, String password, String role) throws SQLException{
        // 添加一个对数据库的判断
        User user;
//        if (!connectDB){
//            throw new SQLException("Not Connection DataBase!");
//        }
//
//        double ranval = Math.random();
//        if (ranval > 1000){
//            throw new SQLException("Error in excecuting Update");
//        }
        // 可以用抽象类建立一个子类的对象  比如用user对象建立一个administrator的对象
        if (role.equalsIgnoreCase("Administrator"))
            user = new Administrator(name,password, role);
        else if (role.equalsIgnoreCase("Operator"))
            user = new Operator(name,password, role);
        else
            user = new Browser(name,password, role);
        users.put(name, user);
        // 将数据在数据库中更新
        boolean com = Update_DB(name, password, role);
        if (com) {
            return true;
        }else {
            return false;
        }
    }


    // 插入用户数据信息
    public static boolean insert_User(String name, String password, String role) throws SQLException{
        // 添加一个对数据库的判断
        User user;
//        if (!connectDB){
//            throw new SQLException("Not Connected to Database");
//        }
//        double ranValue = Math.random();
//        if (ranValue > 1000){
//            throw new SQLException("Error in excecuting Insert");
//        }
        if (users.containsKey(name))
            return false;
        else{
            if (role.equalsIgnoreCase("administrator"))
                user = new Administrator(name,password, role);
            else if (role.equalsIgnoreCase("operator"))
                user = new Operator(name,password, role);
            else
                user = new Browser(name,password, role);
            users.put(name, user);
            boolean com = Insert_DB(name, password, role);
            if (com){
                return true;
            } else {
                return false;
            }
        }
    }


    // 删除用户数据信息
    public static boolean delete(String name) throws SQLException{
        // 添加一个对数据库的操作
//        if (!connectDB){
//            throw new SQLException("Not Connected to DataBase");
//        }
//
//        double ranValue = Math.random();
//        if (ranValue > 1000){
//            throw new SQLException("Error in excecuting Delete");
//        }
        if (users.containsKey(name)){
            users.remove(name);
            boolean com = Delete_DB(name);
            if (com) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void disconnectFromDB(){
        if (connectDB){
            // close Statement and Connection
        }
        try {
            if (Math.random() > 1000){
                throw new SQLException("Error in disconnecting DB!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connectDB = false;
        }
    }


    // 对数据库进行一个内容的查找
    // 获取的关键是获取ID信息
    // 查找文本信息
    public static Doc search_Doc(String ID) throws SQLException{
        // 获取的关键就是ID
        Doc_DB();
        if (docs.containsKey(ID)){
            Doc temp = docs.get(ID);
            if (temp.getID().equals(ID)) {
                return temp;
            }
        }
        return null;
    }


    // 对Doc进行哈希查找  哈希表的枚举
    public static Enumeration<Doc> getAllDocs() throws SQLException {
        Enumeration<Doc> e = docs.elements();
        return e;
    }

    // 对Doc进行添加
    public static synchronized boolean insert_Doc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
        Doc doc;
        // 如果存在就报错
        if (docs.containsKey(ID)) {
            return false;
            // 不存在 建立新的对象 以及对象内容放入Hash Table
        } else {
            doc = new Doc(ID, creator, timestamp, description, filename);
            docs.put(ID, doc);
            boolean com = Insert_Doc(ID, creator, timestamp, description, filename);
            if (com){
                return true;
            } else {
                return false;
            }
        }
    }

    // 从数据库中获取用户的信息
    public static void User_DB(){
        User user;
        String sql;
//        ResultSet resultSet;
        conn_db cd = new conn_db();
        try {
            cd.connection();
            Connection connection = cd.con;
            try {
                // 建立对应的游标
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                sql = "SELECT * FROM User";
                resultSet = statement.executeQuery(sql);
//                ResultSetMetaData data = resultSet.getMetaData();
                // 读取内容
                while (resultSet.next()){
//                    for (int i = 0; i < data.getColumnCount(); i++) {
                    String Name = resultSet.getString(1);
                    String Num = resultSet.getString(2);
                    String Author = resultSet.getString(3);
                    // 根据不同的用户生成不同的对象
                    switch (Author) {
                        case "Administrator":
                            user = new Administrator(Name, Num, Author);
                            users.put(Name, user);
                            break;
                        case "Operator":
                            user = new Operator(Name, Num, Author);
                            users.put(Name, user);
                            break;
                        case "Browser":
                            user = new Browser(Name, Num, Author);
                            users.put(Name, user);
                            break;
                        default:
                            break;
                    }
                // while的括号
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 从数据库中获取 文件的信息
    public static void Doc_DB(){
        Doc doc;
        String sql;
        conn_db cd = new conn_db();
        try {
            cd.connection();
            Connection connection = cd.con;
            try {
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                sql = "SELECT * FROM document";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    String file_num = resultSet.getString(1);
                    String upload = resultSet.getString(2);
                    Timestamp timestamp = Timestamp.valueOf(resultSet.getString(3));
                    String file_name = resultSet.getString(4);
                    String description = resultSet.getString(5);
                    doc = new Doc(file_num, upload, timestamp, description, file_name);
//                    Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
//                    doc1 = new Doc("111111", "jack", timestamp1, "dx.txt", "dc.txt");
                    docs.put(file_num, doc);
                }
            } catch (Exception e1){
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 更新数据库中用户的密码
    public static boolean Update_DB(String name, String password, String role) throws SQLException {
        // 更新语句
        String sql;
        Init();
        sql = "update user set secret= '" + password + "' where name='" + name + "' AND role='" + role + "'";
        try{
            rw = statement.executeUpdate(sql);
//            JOptionPane.showMessageDialog(null, "更新成功!");
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return false;
    }

    // 插入用户数据到数据库中
    static boolean Insert_DB(String name_in, String pass_in, String role_in) throws SQLException {
        String sql;
        Init();
        sql = "INSERT INTO user(name,secret,role) VALUE('"+ name_in + "'," + "'" + pass_in + "'," + "'" + role_in + "')";
        try{
            rw = statement.executeUpdate(sql);
//            JOptionPane.showMessageDialog(null, "添加成功!");
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return false;
    }

    // 从数据库中删除信息
    static boolean Delete_DB(String name) throws SQLException {
        String sql;
        Init();
        sql = "DELETE FROM user WHERE name = '" + name +"'";
        try{
            rw = statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "删除成功!");
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return false;
    }

    // 向数据库中插入文件信息
    static boolean Insert_Doc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException {
        String sql;
        Init();
        sql = "INSERT INTO document(num,author,time,file_name, description) VALUE ('"+ID+"','"+creator+"',NOW(),'"+description+"','"+filename+"');";
        try {
            rw = statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "上传成功!");
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return false;
    }

    // 修改用户自己的信息的数据
//    public static boolean Up_UserDelf(String name, String password, String role) throws SQLException {
//        String  sql;
//        Init();
//        sql = sql = "update user set secret= '" + password + "' where name='" + name + "' AND role='" + role + "'";
//        try{
//            rw
//        }
//    }

    /**
     * 测试函数类
     */
//    public static void main(String[] args) throws SQLException {
////        Doc_DB();
////        Enumeration<Doc> e = getAllDocs();
////        Doc doc = null;
////        while (e.hasMoreElements()){
////            doc = e.nextElement();
////        }
//        search_Doc("12");
//        System.out.println(search_Doc("12"));
//    }
}
