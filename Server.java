package homework;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

import static java.lang.System.out;
/**
 * 9097  文件下载端口号
 * 7821  登录用户端口号
 * 9527  添加用户端口号
 * 9806  删除用户端口号
 * 6174  更新用户端口号
 * 7909  文件上传端口号
 */

/**
 * 建立服务器通过服务器  接收客户端传过来的消息
 */
public class Server {
    public static void main(String[] args) throws Exception {
        // 建立对象

//        server_configure_file.run();
//        Thread t1 = new Thread(server_configure);
//        Thread t2 = new Thread(server_configure);
////        while(true) {
//            t1.start();
//            t2.start();
//        }
        // 建立对象
        Server_Configure_File server_configure_file = new Server_Configure_File();
        Server_Configure_Login server_configure_login = new Server_Configure_Login();
        Server_Configure_Add server_configure_add = new Server_Configure_Add();
        Server_Configure_Delete server_configure_delete = new Server_Configure_Delete();
        Server_Configure_Update server_configure_update = new Server_Configure_Update();
        Server_Configure_Down_File server_configure_down_file = new Server_Configure_Down_File();
        // 建立对应的线程对象
        Thread thread_file = new Thread(server_configure_file);
        Thread thread_Login = new Thread(server_configure_login);
        Thread thread_add = new Thread(server_configure_add);
        Thread thread_delete = new Thread(server_configure_delete);
        Thread thread_update = new Thread(server_configure_update);
        Thread thread_down_file = new Thread(server_configure_down_file);
        // 开启线程对象
        thread_file.start();
        thread_Login.start();
        thread_add.start();
        thread_delete.start();
        thread_update.start();
        thread_down_file.start();
        System.out.println("服务器已开启");
        System.out.println("-------------***********--------------");
//        Thread thread = new Thread(server_configure_manage);
//        Thread thread1 = new Thread(server_configure_manage);
//        thread.start();
//        thread1.start();
//        server_configure_manage.run();
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.execute(server_configure_manage);
//        executorService.execute(server_configure_manage);
    }
}

// 下载文件的配置
class Server_Configure_File extends Thread {
    public static final String fileDir = "E:\\exploitation\\codes\\downloadfile\\";

    // 通过关键字synchronize关键字实现synchronize
    @Override
    public synchronized void run() {
        try {
            while (true) {
                notify();
                Start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     void Start() throws Exception {
        ServerSocket as = new ServerSocket(9097);        // 创建服务端的连接
//        System.out.println("服务器已开启，等待接收文件...");
        Socket s = as.accept();                               // 客户端连接服务器端
        System.out.println("正在接受来自" + s.getInetAddress().getHostAddress() + "的文件....");
        System.out.println(currentThread().getName());
        receiveFile(s);                                       // 连接成功后，开始传输文件
        System.out.println("***************************************************");
        as.close();                                           // 关闭资源
    }

    private void receiveFile(Socket socket) throws IOException {
        // buffer起缓冲作用 一次读取或写入多个字节的数据
        byte[] buffer = new byte[1024];
        // 创建DataInputStream对象 可以调用它的readUTF方法来读取要传输的文件名
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        // 首先读取文件名
        String oldFileName = dis.readUTF();
        // 文件名路径采用与客户端相同的路径 文件名重新命名
        String filePath = fileDir + genereate_FileName(oldFileName);
        System.out.println("接收文件成功，另存为：" + filePath);
        // 利用FileOutputStream来操作文件输出流
        FileOutputStream fos = new FileOutputStream(new File(filePath));
        int length = 0;
        while ((length = dis.read(buffer, 0, buffer.length)) > 0) {
            fos.write(buffer, 0, length);
            fos.flush();
        }
        dis.close();                                   //释放资源
        fos.close();
        socket.close();
    }

    private Random a = new Random();

    private String genereate_FileName(String old_Name) {
        int b = a.nextInt(10000000);
        String new_Name = null;
        new_Name = old_Name.substring(0, old_Name.lastIndexOf(".")) + b + old_Name.substring(old_Name.lastIndexOf("."));
        return new_Name;
    }

}

// 用户登录的服务
class Server_Configure_Login extends Thread{
    @Override
    public synchronized void run() {
        try {
            while (true) {
                ServerSocket serverSocket_Manage = new ServerSocket(7821);
//                Thread thread = new Thread();
//                Thread thread1 = new Thread();
//                thread.start();
//                thread1.start();
//                yield();
                Start(serverSocket_Manage);
//                System.out.println(currentThread().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void Start(ServerSocket serverSocket_Manage) throws Exception {
//        ServerSocket serverSocket_Manage = new ServerSocket(7821);
//        System.out.println("服务器已开启，等待接收信息...");
        Socket s = serverSocket_Manage.accept();                               // 客户端连接服务器端
        System.out.println("正在接受来自" + s.getInetAddress().getHostAddress() + "的信息....");
        System.out.println(currentThread().getName());
        Manage_User(s);                                       // 连接成功后，开始传输信息
        out.println("*****************************************");
        serverSocket_Manage.close();                                           // 关闭资源
    }

    void Manage_User(Socket socket) throws Exception {
        // 建立信息传输的管道
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataInputStream ids = new DataInputStream(socket.getInputStream());
        // 读取传输过来的信息
        String Login = dis.readUTF();
        String Secret = ids.readUTF();
        User user = DataProcess.search(Login, Secret);
        if (user != null) {
            DataOutputStream dos_ju =  new DataOutputStream(socket.getOutputStream());
            dos_ju.writeUTF("true");
            user.GUI();
            out.println(Login);
            out.println(Secret);
            System.out.println("登录成功!");
            // 服务器获取的信息传给个人用户界面修改内容
            Main_GUI main_gui = new Main_GUI();
            main_gui.set_Name(Login);
            main_gui.set_Pass(Secret);
            main_gui.set_Role(user.getRole());
            Main_GUI_Plugin main_gui_plugin = new Main_GUI_Plugin();
            main_gui_plugin.setLogin_Name_Text(Login);
            main_gui_plugin.setLogin_Password_Text(Secret);
            main_gui_plugin.getRole(user.getRole());
        } else {
            DataOutputStream dos_ju =  new DataOutputStream(socket.getOutputStream());
            System.out.println("登录失败");
            dos_ju.writeUTF("false");
        }
    }

}

// 添加用户的配置
class Server_Configure_Add extends Thread{
    @Override
    public synchronized void run() {
        try {
            while (true){
//                ServerSocket server_add = new ServerSocket(9527);
                Transport();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    void Transport() throws Exception {
        ServerSocket server_add = new ServerSocket(9527);
        // 一直获取监听直到信息传入
        Socket s = server_add.accept();
        out.println("正在接受" + s.getInetAddress().getHostAddress() + "的信息.....");
        out.println(currentThread().getName());
        // 将接收的socket对象传给Add_User建立连接
        Add_User(s);
        out.println("***************************************************");
        // 关闭serverSocket_T的服务
        server_add.close();

    }

    void Add_User(Socket socket) throws Exception {
        // 建立信息传输的管道
        DataInputStream User_Data = new DataInputStream(socket.getInputStream());
        DataInputStream PassW_Data = new DataInputStream(socket.getInputStream());
        DataInputStream Role_Data = new DataInputStream(socket.getInputStream());
        // 获取接收到的信息
        String User = User_Data.readUTF();
        String PW = PassW_Data.readUTF();
        String Role = Role_Data.readUTF();
        boolean com = DataProcess.insert_User(User, PW, Role);
        if (!com){
            JOptionPane.showMessageDialog(null, "添加失败");
//            JOptionPane.showMessageDialog(null, "添加成功");
        }
    // 函数Add_User的括号
    }
}

// 删除用户的配置
class Server_Configure_Delete extends Thread{
    @Override
    public synchronized void run() {
        try{
            while (true){
                Transport();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void Transport() throws IOException, SQLException {
        ServerSocket server_Delete = new ServerSocket(9806);
        // 监听获取Client传来的信息
        Socket s = server_Delete.accept();
        out.println("正在接受" + s.getInetAddress().getHostAddress() + "传来的信息...");
        // 获取当前的线程名
        out.println(currentThread().getName());
        // 将接受的socket对象传给Delete_User  建立删除功能连接
        Delete_User(s);
        out.println("************************************************");
        // 关闭资源
        server_Delete.close();
    }

    void Delete_User(Socket socket) throws IOException, SQLException {
        // 建立信息传输的管道
        DataInputStream User_Name = new DataInputStream(socket.getInputStream());
        // 接收获取到的信息
        String user_name = User_Name.readUTF();
        boolean com = DataProcess.delete(user_name);
        if (!com){
            JOptionPane.showMessageDialog(null, "删除失败");
        }
    }
}

// 更新用户信息的配置
class Server_Configure_Update extends Thread{
    @Override
    public void run() {
        try {
            while (true){
                Transport();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void Transport() throws IOException, SQLException {
        ServerSocket server_Update = new ServerSocket(6174);
        // 监听来自Client的信息
        Socket s = server_Update.accept();
        out.println("正在接受" + s.getInetAddress().getHostAddress() + "传来的信息...");
        // 获取当前的线程名称 并显示出来
        out.println(currentThread().getName());
        // 将接受的socket对象传给Delete_User 建议更新功能的连接
        User_Update(s);
        out.println("**************************************************");
        // 关闭连接的资源
        server_Update.close();
    }

    void User_Update(Socket socket) throws IOException, SQLException {
        // 建立信息接受的管道
        DataInputStream User_Name = new DataInputStream(socket.getInputStream());
        DataInputStream User_Pw = new DataInputStream(socket.getInputStream());
        DataInputStream User_Role = new DataInputStream(socket.getInputStream());
        // 接受获取到的信息
        String user_name = User_Name.readUTF();
        String user_pw = User_Pw.readUTF();
        String user_role = User_Role.readUTF();
        boolean com = DataProcess.update(user_name, user_pw, user_role);
        if (!com){
            JOptionPane.showMessageDialog(null, "更新失败");
        }
    }
}

// 上传文件的配置
class Server_Configure_Down_File extends Thread{
    // 上传文件的位置
    public static final String fileDir = "E:\\exploitation\\codes\\uploadfile\\";
    // 通过关键字synchronize

    @Override
    public synchronized void run() {
        try {
            while (true){
                notify();
                Start();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void Start() throws Exception {
        ServerSocket server_file = new ServerSocket(7909);
        Socket s = server_file.accept();
        System.out.println("正在接受来自" + s.getInetAddress().getHostAddress() + "的文件...");
        System.out.println(currentThread().getName());
        downfile(s);
        System.out.println("*********************************************************");
        server_file.close();
    }

    void downfile(Socket socket) throws IOException, SQLException {
        byte[] buffer = new byte[1024];
        // 创建DataInputStream对象  获取客户端传来的消息
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataInputStream dis_num = new DataInputStream(socket.getInputStream());
        DataInputStream dis_creator = new DataInputStream(socket.getInputStream());
        DataInputStream dis_Discip = new DataInputStream(socket.getInputStream());
        // 首先读取文件名
        String num = dis.readUTF();
        String creator = dis_num.readUTF();
        String dis_dis = dis_creator.readUTF();
        String fileName = dis_Discip.readUTF();
        String filePath = fileDir + fileName;
        System.out.println("接受文件:" + fileName + "\n上传至:" + fileDir);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DataProcess.insert_Doc(num, creator, timestamp, fileName, dis_dis);
        FileOutputStream fos = new FileOutputStream(new File(filePath));
        int length = 0;
        while((length = dis.read(buffer, 0, buffer.length)) > 0){
            fos.write(buffer, 0, length);
            fos.flush();
        }
        dis.close();
        fos.close();
        socket.close();
    }
}
