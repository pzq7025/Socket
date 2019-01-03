package homework;
/**
 * 注意调用界面的范围  由于是组件界面因此在使用的时候  要关闭父系窗口
 * 也就是能够添加组件窗口的JFrame
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public  class Constitu_GUI_Plug implements ActionListener {
    JMenu Mange_Create_User;
    JMenu File_Up, File_Down;

    // 主界面的GUI设计
    public void setMange_Create_User(JMenu mange_Create_User) {
        Mange_Create_User = mange_Create_User;
    }

    // 文件Menu的设计 这个未用到  后面再删除掉
    public void setFile_Up(JMenu file_Up) {
        File_Up = file_Up;
    }

    public void setFile_Down(JMenu file_Down) {
        File_Down = file_Down;
    }

    // 新建用户的接收器
    JButton Creator_User_Button, Cancel_Creator_User_Button;
    JTextField Creator_User_Text, Secret_User_Text;
    static String role_User;
    static JFrame User_Creator_Jframe;
    public void setCreator_User_Button(JButton creator_User_Button) {
        Creator_User_Button = creator_User_Button;
    }

    public void setCancel_User_Button(JButton cancel_User_Button) {
        Cancel_Creator_User_Button = cancel_User_Button;
    }

    public void setCreator_User_Text(JTextField creator_User_Text) {
        Creator_User_Text = creator_User_Text;
    }

    public void setSecret_User_Text(JTextField secret_User_Text) {
        Secret_User_Text = secret_User_Text;
    }

    public void setRole_User(String role_User1) {
        role_User = role_User1;
    }

    public void setUser_Creator_Jframe(JFrame user_Creator_Jframe) {
        User_Creator_Jframe = user_Creator_Jframe;
    }

    // 修改用户的密令的GUI界面
    // 修改用户的GUI的按钮接收器
    JTextField Change_Name_Text, Change_secret_Text;
    JButton Change_User_Button, Cancel_Change_User_Button;
    static String role_;
    static JFrame Change_User_jFrame;

    public void setChange_Name_Text(JTextField change_Name_Text) {
        Change_Name_Text = change_Name_Text;
    }

    public void setChange_secret_Text(JTextField change_secret_Text) {
        Change_secret_Text = change_secret_Text;
    }

    public void setChange_User(JButton change_User) {
        Change_User_Button = change_User;
    }

    public void setCancel_Button(JButton cancel_Button) {
        Cancel_Change_User_Button = cancel_Button;
    }

    public void setjFrame(JFrame jFrame) {
        Change_User_jFrame = jFrame;
    }

    public void setRole_(String role_1) {
        role_ = role_1;
    }


    // 删除用户密令的GUI
    // 删除用户的接收窗口
    static JFrame Delete_Cancel_JFrame;
    JButton Delete_User_Button;
    // 获取删除界面的GUI窗口以及删除按钮
    public void setDelete_Cancel_JFrame(JFrame delete_Cancel_JFrame) {
        Delete_Cancel_JFrame = delete_Cancel_JFrame;
    }
    public void setDelete_User_Button(JButton delete_User_Button) {
        Delete_User_Button = delete_User_Button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // 文件下载
        if (e.getSource() == File_Down){
//        } else if (e.getSource() == Cancel_Button1){
//            int ju = JOptionPane.showConfirmDialog(null, "\t\t\t确定退出", "Tip", JOptionPane.OK_CANCEL_OPTION);
//            if (ju == 0) {
////                Change_jFrame.dispose();
//            }


            // 创建用户
        } else if (e.getSource() == Creator_User_Button){
            Creator_User();
//        } else if (e.getSource() == Cancel_User_Button){
//            int ju = JOptionPane.showConfirmDialog(null, "\t\t\t确定退出", "Tip", JOptionPane.OK_CANCEL_OPTION);
//            if (ju == 0) {
//                User_Creator_Jframe.dispose();
//            }
        } else if (e.getSource() == Cancel_Creator_User_Button) {
            int ju = JOptionPane.showConfirmDialog(null, "\t\t\t确定退出", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                User_Creator_Jframe.dispose();
            }


            // 修改用户
        } else if (e.getSource() == Change_User_Button) {
            Change_Information();
        } else if (e.getSource() == Cancel_Change_User_Button) {
            int ju = JOptionPane.showConfirmDialog(null, "\t\t\t确定退出", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                Change_User_jFrame.dispose();
            }


            // 删除用户界面
        } else if (e.getSource() == Delete_User_Button){
            int ju = JOptionPane.showConfirmDialog(null, "\t\t\t确定退出", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                Delete_Cancel_JFrame.dispose();
            }
        }




    }


    // 新建用户界面的函数调用
    void Creator_User(){
        if (Creator_User_Text.getText().equals("")){
            JOptionPane.showMessageDialog(null, "\t\t请输入账号", "WARNING", JOptionPane.WARNING_MESSAGE);
        } else if (Secret_User_Text.getText().equals("")){
            JOptionPane.showMessageDialog(null, "\t\t请输入密令", "WARNING", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                convey();
                Client_User_Add client_user_add = new Client_User_Add();
                boolean ju = client_user_add.Client_Transmit();
                if (ju) {
                    JOptionPane.showMessageDialog(null, "\t\t创建成功");
                    Creator_User_Text.setText("");
                    Secret_User_Text.setText("");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


    // 修改用户的函数调用
    void Change_Information(){
        if (Change_Name_Text.getText().equals("")){
            JOptionPane.showMessageDialog(null, "\t\t请输入账号", "WARNING", JOptionPane.WARNING_MESSAGE);
        } else if (Change_secret_Text.getText().equals("")){
            JOptionPane.showMessageDialog(null, "\t\t请输入密令", "WARNING", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

//                System.out.println(Change_Name_Text.getText());
//                System.out.println(Change_secret_Text.getText());
//                System.out.println(role_);
                Client_User_Update client_user_update = new Client_User_Update();
                client_user_update.setUser_Name(Change_Name_Text.getText());
                client_user_update.setUser_Pw(Change_secret_Text.getText());
                client_user_update.setUser_Role(role_);
//                client_user_update.run();
                boolean ju = client_user_update.Client_Transmit();
                if (ju) {
                    JOptionPane.showMessageDialog(null, "\t\t修改成功");
                    Change_Name_Text.setText("");
                    Change_secret_Text.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "\t\t修改失败");
                    Change_Name_Text.setText("");
                    Change_secret_Text.setText("");
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "\t\t修改失败\n\t\t信息有问题", "WARNING", JOptionPane.WARNING_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    // 向服务端传值
    void convey(){
        // 向添加用户的Client端传值
        Client_User_Add client_user_add = new Client_User_Add();
        client_user_add.setUser_name(Creator_User_Text.getText());
        client_user_add.setUser_PW(Secret_User_Text.getText());
        client_user_add.setUser_Role(role_User);
    }


}
