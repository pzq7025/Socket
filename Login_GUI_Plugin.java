package homework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 建立的登录界面的响应事件函数
 */

public class Login_GUI_Plugin extends conn_db implements ActionListener {
    private static JTextField Name_Text;
    private static JTextField Password_Text;
    private JButton Ok_Button, Cancel_Button;
    private JFrame jFrame;

    Main_GUI_Plugin main_gui_plugin;


    // 接收响应的参数
    void setName_Text(JTextField name_Text) {
        Name_Text = name_Text;
    }

    void setPassword_Text(JTextField password_Text) {
        Password_Text = password_Text;
    }

    void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    void setOk_Button(JButton ok_Button) {
        Ok_Button = ok_Button;
    }

    void setCancel_Button(JButton cancel_Button) {
        Cancel_Button = cancel_Button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Ok_Button){
            if (Password_Text.getText().equals("")){
                JOptionPane.showMessageDialog(null, "\t姓名不能为空", "Tip", JOptionPane.WARNING_MESSAGE);
            } else if (Name_Text.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "\t密码不能为空", "Tip", JOptionPane.WARNING_MESSAGE);
            } else{
                try {
//                int ju = JOptionPane.showConfirmDialog(null, "\t\t确定登录", "Tip", JOptionPane.OK_CANCEL_OPTION);
//                if (ju == 0) {
                    convey();
//                    User user = DataProcess.search(Name_Text.getText(), Password_Text.getText());
                    // 对信息进行检索  不为空继续 为空报错
//                    if (user != null) {
                        Client_User_Login client_user_login = new Client_User_Login();
//                        client_user_login.run();
                        boolean ju = client_user_login.Client_Transmit();
                        if (ju) {
                            client_user_login.interrupt();
                            System.out.println(ju);
                            Name_Text.setText("");
                            Password_Text.setText("");
//                        client_user_manage.setLogin_name(Name_Text.getText());
//                        client_user_manage.setSecret(Password_Text.getText());
//                        user.GUI();
                            jFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null,"\t\t登录信息错误\n\t\t请重试" );
                            Name_Text.setText("");
                            Password_Text.setText("");
                        }
//                    }
//                    } else {
//                        JOptionPane.showMessageDialog(null, , "WARNINGING", JOptionPane.WARNING_MESSAGE);
                        // 重置输入密码
//
//                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "\t\t信息错误\n\t\t请重试", "WARNING", JOptionPane.WARNING_MESSAGE);
                    Name_Text.setText("");
                    Password_Text.setText("");
                    e1.printStackTrace();
                }
            }
        } else if (e.getSource() == Cancel_Button) {
            int ju = JOptionPane.showConfirmDialog(null, "\t\t是否退出", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0){
                System.exit(0);
            }
        }
    // 监听对象的括号
    }

    // 将获取的用户信息传出去
    static String role;
    void convey() {
        main_gui_plugin = new Main_GUI_Plugin();
        Main_GUI main_gui = new Main_GUI();
        Constitute_GUI constitute_gui = new Constitute_GUI();
        Client_User_Login client_user_login = new Client_User_Login();
        String namelgp = Name_Text.getText();
        String passwordlgp = Password_Text.getText();
        try {
            User user = DataProcess.search(namelgp, passwordlgp);
            if (user != null) {
                role = user.getRole();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        main_gui_plugin.setLogin_Name_Text(namelgp);
        main_gui_plugin.setLogin_Password_Text(passwordlgp);
        main_gui_plugin.getRole(role);
        main_gui.set_Name(namelgp);
        main_gui.set_Pass(passwordlgp);
        main_gui.set_Role(role);
        constitute_gui.setName_User(namelgp);
        constitute_gui.setPassword_User(passwordlgp);
        constitute_gui.setRole_User(role);
        client_user_login.setLogin_name(namelgp);
        client_user_login.setSecret(passwordlgp);
    }

//    public static String getName_Text() {
//        return Name_Text.getText();
//    }
//
//    public static String getRole() {
//        return role;
//    }
//
//    public static String getPassword_Text() {
//        return Password_Text.getText();
//    }
}
