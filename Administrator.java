package homework;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Administrator extends User {
    // 构造函数  对变量附初值
    Administrator(String name, String password, String role){
        super(name,password,role);
    }

    private boolean statue = true;

    // 覆盖函数进行新的内容输出
    @Override
    public void showInfo() {
        while (statue){
            System.out.println("***********进入档案浏览器菜单***********");
            System.out.println("\t1.下载文件\n\t2.文件列表\n\t3.修改密码\n\t4.退  出");
            System.out.println("**************************************");
            System.out.println("请输入菜单选项:");
            try {
                Scanner scanner = new Scanner(System.in);
                int i;
                i = scanner.nextInt();
                switch (i) {
                    case 1:
                        System.out.println("下载文件\n请输入文件号:");
                        Scanner scanner1 = new Scanner(System.in);
                        String file_num = scanner1.next();
                        try {
                            boolean ju = downloadFile(file_num);
                            if (ju){
                                System.out.println("DownLoad successful!");
                            } else {
                                System.out.println("Download is fail!");
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
                    case 2:
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
                    case 3:
//                    System.out.println("修改的账号:");
//                    Scanner scanner3 = new Scanner(System.in);
//                    String id = scanner3.next();
                        System.out.println("输入修改的密码:");
                        Scanner scanner2 = new Scanner(System.in);
                        String password = scanner2.next();
//                    System.out.println("修改的角色名:");
//                    Scanner scanner4 = new Scanner(System.in);
//                    String rol = scanner4.next();
//                    DataProcess.update(id, password, rol);
                        try {
                            boolean ju = changeSelfInfo(password);
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
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please again!");
//                        statue = true;
                        break;
                }
            } catch (Exception e){
                System.out.println("Enter is wrong\nPlease again!");
//                statue = true;
            }
            // 一直可以循环的状态判断
        }
       // 大函数的括号
    }


    @Override
    public void GUI() {
        Administrator_GUI administrator_gui = new Administrator_GUI();
        administrator_gui.init();
    }
}
