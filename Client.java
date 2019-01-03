package homework;

/**
 * 建立用户端的Client
 * 通过Client访问服务器进行操作
 */

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 9097  文件下载端口号
 * 7821  登录用户端口号
 * 9527  添加用户端口号
 * 9806  删除用户端口号
 * 6175  更新用户端口号
 * 7909  文件上传端口号
 */

public class Client {
    public static void main(String[] args){
//        Client_Thread client_thread1 = new Client_Thread();      // 创建client_thread的实例
        Client_User_Login client_user_login = new Client_User_Login();
        Client_Up_File client_thread = new Client_Up_File();
        Thread thread = new Thread(client_thread);
        Thread thread1 = new Thread(client_user_login);
        thread.start();
        thread1.start();
//        ExecutorService service = Executors.newFixedThreadPool(2);
//        service.execute(client_user_manage);
//        service.execute(client_user_manage);
//        client_user_manage.run();
//        client_thread.run();
    }

}

// 文件下载客户端
class Client_Up_File extends Thread{
    private static String file_path;

    // 接受GUI的信息
    public void setFile_path(String file_path1) {
        file_path = file_path1;
    }

    @Override
    public synchronized void run() {
        try {
            Thread client_thread = new Thread();
            client_thread.start();
            Client_File_Down(file_path);
            System.out.println(currentThread().getName());
//            System.out.println(currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 文件下载的客户端
    boolean Client_File_Down(String file_Path) throws Exception {
        System.out.println("正在发送文件:" + file_Path);
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9097);
        if (socket != null){
            System.out.println("发送成功");
            sendEmail(socket, file_Path);
            return true;
        }
        return false;
    }

    // 开始发送文件
    private void sendEmail(Socket socket, String File_Path) throws Exception {
        byte[] bytes = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(File_Path)));
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        // 首先发送文件名  客户端发送使用writeUTF方法  服务器端应该使用readUTF方法
        dos.writeUTF(getFileName(File_Path));
        int length = 0;
        while ((length = bis.read(bytes, 0 , bytes.length)) > 0){
            dos.write(bytes, 0, length);
            dos.flush();
        }
        bis.close();                            // 释放资源
        dos.close();
        socket.close();
    }

    // 得到文件名
    private String getFileName(String file_path){
        File file = new File(file_path);
        return file.getName();
    }

}

// 用户登录客户端
class Client_User_Login extends Thread{
    private static String Login_name;
    private static String Secret;

    // 接受GUI的信息
    public void setLogin_name(String login_name) {
        Login_name = login_name;
    }

    public void setSecret(String secret) {
        Secret = secret;
    }

    @Override
    public synchronized void run() {
        try {
//            while (true) {
//                Socket socket_Manage = new Socket(InetAddress.getByName("127.0.0.1"), 7821);
//                Thread client_user_login = new Thread();
//                Thread thread1 = new Thread();
//                client_user_login.start();
//                thread1.start();
//                yield();
                Client_Transmit();
                System.out.println(currentThread().getName());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 用户传输的客户端
    boolean Client_Transmit() throws Exception {
        System.out.println("正在发送信息");
        Socket socket_Manage = new Socket(InetAddress.getByName("127.0.0.1"), 7821);
        if (socket_Manage != null){
            sendUser(socket_Manage);
            System.out.println("发送成功!");
            System.out.println("*********************************");
            DataInputStream dos_ju = new DataInputStream(socket_Manage.getInputStream());
            String ju = dos_ju.readUTF();
            if (ju.equals("true")){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    void sendUser(Socket socket) throws IOException {
        // 建立传输信息的数据流
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataOutputStream dos1 = new DataOutputStream(socket.getOutputStream());
        // 输出信息
        dos.writeUTF(Login_name);
        dos1.writeUTF(Secret);
    }
}

// 添加用户的客户端
class Client_User_Add extends Thread{
    private static String User_name;
    private static String User_PW;
    private static String User_Role;

    // 接受GUI的信息
    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public void setUser_PW(String user_PW) {
        User_PW = user_PW;
    }

    public void setUser_Role(String user_Role) {
        User_Role = user_Role;
    }

    @Override
    public synchronized void run() {
        try {
//            super.run();
//            Thread thread_Add = new Thread();
//            thread_Add.start();
            Client_Transmit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    boolean Client_Transmit() throws IOException {
        Socket socket_Add = new Socket(InetAddress.getByName("127.0.0.1"),9527);
        System.out.println("正在发送信息");
        if (socket_Add != null){
            Send_Information(socket_Add);
            System.out.println("发送成功!");
            System.out.println("*********************************");
            return true;
        }
        return false;
    }

    void Send_Information(Socket socket) throws IOException {
        // 建立输送的信息流
        DataOutputStream User_name_Data = new DataOutputStream(socket.getOutputStream());
        DataOutputStream User_Pw_Data = new DataOutputStream(socket.getOutputStream());
        DataOutputStream User_Role_Data = new DataOutputStream(socket.getOutputStream());
        // 输出信息
        User_name_Data.writeUTF(User_name);
        User_Pw_Data.writeUTF(User_PW);
        User_Role_Data.writeUTF(User_Role);
    }
}

// 删除用户的客户端
class Client_User_Delete extends Thread{
    private static String Name;

    // 接受GUI的信息
    public void set_Name(String name) {
        Name = name;
    }

    @Override
    public synchronized void run() {
        try {

//            Thread thread_delete = new Thread();
//            thread_delete.start();
            Client_Transmit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    boolean Client_Transmit() throws IOException {
        Socket socket_delete = new Socket(InetAddress.getByName("127.0.0.1"), 9806);
        System.out.println("正在发送信息");
        if (socket_delete != null){
            Send_Delete_Information(socket_delete);
            System.out.println("发送成功!");
            System.out.println("***************************************************");
            return true;
        }
        return false;
    }

    void Send_Delete_Information(Socket socket) throws IOException {
        // 建立输送的信息流
        DataOutputStream User_name = new DataOutputStream(socket.getOutputStream());
        // 输出信息
        User_name.writeUTF(Name);
    }

}

// 用户更新的客户端
class Client_User_Update extends Thread{
    private static String User_Name;
    private static String User_Pw;
    private static String User_Role;

    // 接受GUI的信息
    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public void setUser_Pw(String user_Pw) {
        User_Pw = user_Pw;
    }

    public void setUser_Role(String user_Role) {
        User_Role = user_Role;
    }

    @Override
    public synchronized void run() {
        try {
//            Thread thread_update = new Thread();
//            thread_update.start();
            Client_Transmit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    boolean Client_Transmit() throws IOException {
        Socket socket_update = new Socket(InetAddress.getByName("127.0.0.1"), 6174);
        System.out.println("正在发送信息");
        if (socket_update != null){
            Send_Update_Information(socket_update);
            System.out.println("发送成功!");
            System.out.println("***********************************************");
            return true;
        }
        return false;
    }

    void Send_Update_Information(Socket socket) throws IOException {
        // 建立输送的信息流
        DataOutputStream user_name = new DataOutputStream(socket.getOutputStream());
        DataOutputStream user_pw = new DataOutputStream(socket.getOutputStream());
        DataOutputStream user_role = new DataOutputStream(socket.getOutputStream());
        // 输出信息
        user_name.writeUTF(User_Name);
        user_pw.writeUTF(User_Pw);
        user_role.writeUTF(User_Role);
    }

}

// 文件上传客户端
class Client_File_UP extends Thread{
   static String file_path;
   static String Dos_num;
   static String Dos_Create;
   static String Dos_Discrip;
    @Override
    public synchronized void run() {
        try {
            Client_File_Up(file_path, Dos_num, Dos_Create, Dos_Discrip);
            System.out.println(currentThread().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean Client_File_Up(String file_path, String num, String create, String Descripe) throws IOException {
        System.out.println("正在发送文件:" + file_path);
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 7909);
        if (socket != null){
            send_file(socket, file_path, num, create, Descripe);
            System.out.println("发送成功");
            return true;
        }
        return false;
    }

    void send_file(Socket socket, String file_path, String num, String create, String Descripe) throws IOException {
        byte[] bytes = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file_path));
        // 发送文件信息
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        DataOutputStream dos_num = new DataOutputStream(socket.getOutputStream());
        DataOutputStream dos_creator = new DataOutputStream(socket.getOutputStream());
        DataOutputStream dos_Discrip = new DataOutputStream(socket.getOutputStream());
        // 发送文件名
        dos.writeUTF(getName(file_path));
        dos_num.writeUTF(num);
        dos_creator.writeUTF(create);
        dos_Discrip.writeUTF(Descripe);
//        System.out.println(file_path + " " + num + " " + create + " " + Descripe + " ");
        int length = 0;
        while ((length = bis.read(bytes, 0, bytes.length)) > 0){
            dos.write(bytes, 0 , length);
            dos.flush();
        }
    }
    String getName(String file_path){
        File file = new File(file_path);
        return file.getName();
    }

}