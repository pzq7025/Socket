package homework;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static boolean statue = true;
    public static void main(String[] args) throws SQLException {
        while (statue) {
            // 登录用户的界面
            int i;
            System.out.println("************Welcome manage system************");
            System.out.println("\t1.Login\n\t2.Exit");
            System.out.println("************Please menu****************");
            System.out.println("请输入选项:");
            try {
                Scanner scanner0 = new Scanner(System.in);
                i = scanner0.nextInt();
                switch (i) {
                    case 1:
                        // 定义name  password  的变量
                        String name, password;
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Please Enter name:");
                        name = scanner.next();
                        System.out.println("Please Enter password:");
                        password = scanner.next();
                        // 对user中的哈希表进行匹配
                        // 成功返回内容 失败返回错误信息
                        try {
                            User user = DataProcess.search(name, password);
                            if (user == null) {
                                System.out.println("Information is Wrong!");
                            } else {
                                user.showInfo();
                            }
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    case 2:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please again!");
                        break;
                }
            } catch (Exception e){
                System.out.println("Entry is wrong!\nPlease again");
            }
        // 一直循环的括号标记
        }
        // 函数的括号
    }
}
