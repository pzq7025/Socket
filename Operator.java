package homework;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Operator extends User {
    // 构造函数对函数进行附初值
    Operator(String name, String password, String role){
        super(name, password, role);
    }
    private boolean statue = true;

    @Override
    public void showInfo() {
        while(statue) {
            System.out.println("***************Welcome Entry Operator Interface**************");
            System.out.println("\t1.上传文件\n\t2.下载文件\n\t3.文件列表\n\t4.修改密码\n\t5.退  出");
            System.out.println("**************************************************************");
            System.out.println("请输入选菜单项:");
            try {
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                switch (i) {
                    case 1:
                        System.out.println("请输入文件路径:");
                        Scanner scanner1 = new Scanner(System.in);
                        String file_path = scanner1.next();
                        System.out.println("请输入档案号:");
                        Scanner scanner2 = new Scanner(System.in);
                        String record_num = scanner2.next();
                        System.out.println("请输入档案描述:");
                        Scanner scanner3 = new Scanner(System.in);
                        String describe = scanner3.next();
                        try {
                            // 上传文件
                            boolean ju = UpFile(record_num, describe, file_path);
                            if (ju) {
                                System.out.println("Upload Successful!");
                            } else {
                                System.out.println("Upload fail!");
                            }
                        } catch (SQLException e) {
                                e.printStackTrace();
                                break;
//                            System.out.println(e);
                        }
//                        finally {
//                            statue = true;
//                        }
                        break;
                    case 2:
                        System.out.println("请输入档案号:");
                        Scanner scanner4 = new Scanner(System.in);
                        String recod_num1 = scanner4.next();
                        try {
                            // 下载文件
                            boolean ju = downloadFile(recod_num1);
                            if (ju) {
                                System.out.println("DownLoad  Successful!");
                            } else {
                                System.out.println("DownLoad fail!");
                            }
                        } catch (IOException e) {
//                        e.printStackTrace();
                            e.printStackTrace();
                            break;
                        }
//                        finally {
//                            statue = true;
//                        }
                        break;
                    case 3:
                        try {
                            // 文件列表
                            showFileList();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            break;
//                        e.printStackTrace();
                        }
//                        finally {
//                            statue = true;
//                        }
                        break;
                    case 4:
                        // 修改密码
                        System.out.println("修改密码:");
                        Scanner scanner5 = new Scanner(System.in);
                        String password = scanner5.next();
                        try {
                            boolean ju = changeSelfInfo(password);
                            if (ju){
                                System.out.println("修改成功!");
                            } else {
                                System.out.println("修改失败!");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            break;
//                            System.out.println(e);
                        }
//                        finally {
//                            statue = true;
//                        }
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
//                        statue = true;
                        System.out.println("Entry is invalid\nPlease again!");
                        break;
                }
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Enter is invalid\nPlease enter again!");
//                statue = true;
            }
            // 一直循环运行的状态
        }
        // 函数的括号
    }




    // 上传文件
    public boolean UpFile(String Id, String description, String file_path) throws IOException, SQLException{
//        double ranVal = Math.random();
//        if (ranVal > 0.5){
//            throw new IOException("Upload file is fail!");
//        }
//        System.out.println("上传成功!");
        try {
            byte[] buffer = new byte[1024];

            // 获取源文件
            File temp_file = new File(file_path);
            String file_name = temp_file.getName();

//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            DataProcess.insert_Doc(Id, getName(), timestamp, description, file_name);
//            try {
//
//                boolean ju =
//                if (ju) {
//                    return true;
//                } else {
//                    return false;
//                }
//            } catch (SQLException e){
//                e.printStackTrace();
//            }


            // 建立输入的文件流
            BufferedInputStream infile = new BufferedInputStream(new FileInputStream(temp_file));
            // 建立输出的文件流
            BufferedOutputStream outfile = new BufferedOutputStream(new FileOutputStream(UpLoadPath + file_name));

            while (true) {
                int byteRead = infile.read(buffer);
                if (byteRead == -1)
                    break;
                outfile.write(buffer, 0, byteRead);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DataProcess.insert_Doc(Id, getName(), timestamp, description, file_name);
            }
            infile.close();
            outfile.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



    // 下载文件  构建文件自己的异常处理机制
    public void Download(String filename) throws IOException{
//        double ranVal = Math.random();
//        if (ranVal > 0.5){
//            throw new IOException("Download file is fail!");
//        }
    }

    @Override
    public void GUI() {
        Operator_Gui operator_gui = new Operator_Gui();
        operator_gui.init();
    }
}
