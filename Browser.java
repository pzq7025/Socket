package homework;

import Demo.Browser_Gui;

import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Browser extends User {
    // 构造函数 附初值
    Browser(String name, String password, String role) {
        super(name, password, role);
    }
    private boolean statue = true;

    // 覆盖showInfo函数  输出对应的函数内容
    @Override
    public void showInfo(){
        while (statue){
            System.out.println("*********Welcome Browser************");
            System.out.println("\t1.修改用户\n\t2.删除用户\n\t3.新增用户\n\t4.列出用户\n\t5.下载文件\n\t6.文件列表\n\t7.修改密码\n\t8.退  出");
            System.out.println("************************************");
            System.out.println("请输入菜单选项:");
            try {
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                switch (i) {
                    // 修改用户
                    case 1:
                        System.out.println("修改的账号:");
                        Scanner scanner3 = new Scanner(System.in);
                        String id = scanner3.next();
                        System.out.println("输入修改的密码:");
                        Scanner scanner2 = new Scanner(System.in);
                        String password = scanner2.next();
                        System.out.println("修改的角色名:");
                        Scanner scanner4 = new Scanner(System.in);
                        String rol = scanner4.next();
                        try {
                            boolean ju = DataProcess.update(id, password, rol);
                            if (ju){
                                System.out.println("修改成功!");
                            } else {
                                System.out.println("修改失败!");
                            }
                        } catch (SQLException e) {
//                            System.out.println(e);
                        e.printStackTrace();
                        break;
                        }
//                        finally {
//                            statue = true;
//                        }
                        break;
                    // 删除用户
                    case 2:
                        System.out.println("删除的Id:");
                        Scanner scanner1 = new Scanner(System.in);
                        String name = scanner1.next();
//                    System.out.println("删除的密令:");
//                    Scanner scanner5 = new Scanner(System.in);
//                    String password1 = scanner5.next();
//                    System.out.println("删除的角色:");
//                    Scanner scanner6 = new Scanner(System.in);
//                    String rol1 = scanner6.next();
                        try {
                            boolean ju = DataProcess.delete(name);
                            if (ju){
                                System.out.println("删除成功!");
                            } else {
                                System.out.println("删除失败!");
                            }
                        } catch (SQLException e) {
//                            System.out.println(e);
                        e.printStackTrace();
                        break;
                        }
//                        finally {
//                            statue = true;
//                        }
                        break;
                    // create user
                    case 3:
                        System.out.println("新增的用户名:");
                        Scanner scanner7 = new Scanner(System.in);
                        String name1 = scanner7.next();
                        System.out.println("用户的密码:");
                        Scanner scanner8 = new Scanner(System.in);
                        String password2 = scanner8.next();
                        System.out.println("新增用户的角色:");
                        Scanner scanner9 = new Scanner(System.in);
                        String role2 = scanner9.next();
                        try {
                            boolean ju = DataProcess.insert_User(name1, password2, role2);
                            if (ju){
                                System.out.println("添加成功!");
                            } else {
                                System.out.println("添加失败!");
                            }
                        } catch (SQLException e) {
//                            System.out.println(e);
                        e.printStackTrace();
                        break;
                        }
//                        finally {
//                            statue = true;
//                        }
                        break;
                    // query User Information
                    case 4:
//                        DataProcess.getAllDocs();
//                        DataProcess.getAllUser();
                        try {
                            DataProcess.showInfo();
                        } catch (Exception e){
                            e.printStackTrace();
                            break;
                        }
                        break;
                    // download file
                    case 5:
                        System.out.println("输入文件ID:");
                        Scanner scanner5 = new Scanner(System.in);
                        String fileId = scanner5.next();
                        try {
                            boolean ju = downloadFile(fileId);
                            if (ju) {
                                System.out.println("Download Successful!");
                            } else {
                                System.out.println("DownLoad Fail!");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
//                        e.printStackTrace();
                        }
//                        finally {
//                            statue = true;
//                        }
                        break;
                    // show file_list Information
                    case 6:
                        try {
                            showFileList();
                        } catch (SQLException e) {
//                            System.out.println(e);
                        e.printStackTrace();
                        break;
                        }
//                        finally {
//                            statue = true;
//                        }
                        break;
                    // change Password
                    case 7:
                        System.out.println("请输入密码:");
                        Scanner scanner6 = new Scanner(System.in);
                        String ps2 = scanner6.next();
                        try {
                            boolean ju = changeSelfInfo(ps2);
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
                    case 8:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please again serial number!");
//                        statue = true;
                        break;
                }
            } catch (Exception e){
                System.out.println("Entry is fail!\nPlease again!");
//                statue = true;
            }
        // 状态的运行判断函数
        }
    // 主函数的括号
    }


    @Override
    public void GUI(){
        Browser_GUI browser_gui = new Browser_GUI();
        browser_gui.init();
    }


}
