package homework;

/**
 * 所有的GUI的实现都在这里
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;


public class Main_GUI_Plugin extends conn_db implements ActionListener{
    // JMenu界面
    private JMenuItem Change_User, Delete_User, Add_User, Record_Upload, Record_Download, Change_Information;
    // FileUP界面
    private JButton Open_Button, UpFile_Button, Cancel_Button_UP, Cancel_Button_Down;
    private JTextField Id_Text;
    private JTextArea Descripte_Text;
    private JTextField File_Path_Text;
    static private JFrame jFrame;
    // mian_gui主菜单  change_gui 动态修改菜单
    private JPanel Main_GUI, Change_GUI;
    // 获取登录的信息
    private static String name, password, Role;
//    private JFrame j1;
    // 修改personal信息
    JButton Person_Ok, Person_Cancel;
    JTextField Person_Password_Text;
    JFrame Person_Jframe;


    // JMenu组件
    void setAdd_User(JMenuItem add_User) {
        Add_User = add_User;
    }

    void setChange_Information(JMenuItem change_Information) {
        Change_Information = change_Information;
    }

    void setChange_User(JMenuItem change_User) {
        Change_User = change_User;
    }

    void setDelete_User(JMenuItem delete_User) {
        Delete_User = delete_User;
    }

    void setRecord_Download(JMenuItem record_Download) {
        Record_Download = record_Download;
    }

    void setRecord_Upload(JMenuItem record_Upload) {
        Record_Upload = record_Upload;
    }

    // FileUp
    public void setUpFile_Button(JButton upFile_Button) {
        UpFile_Button = upFile_Button;
    }

    public void setOpen_Button(JButton open_Button) {
        Open_Button = open_Button;
    }

    // 一个就够了  后期再做优化
    public void setCancel_Button_UP(JButton cancel_Button) {
        Cancel_Button_UP = cancel_Button;
    }

    public void setCancel_Button_Down(JButton cancel_Button_Down) {
        Cancel_Button_Down = cancel_Button_Down;
    }

    public void setDescripte_Text(JTextArea descripte_Text) {
        Descripte_Text = descripte_Text;
    }

    public void setFile_Path_Text(JTextField file_Path_Text) {
        File_Path_Text = file_Path_Text;
    }

    public void setId_Text(JTextField id_Text) {
        Id_Text = id_Text;
    }

    public void setjFrame(JFrame j1) {
        jFrame = j1;
    }

    public void setChange_gui(JPanel change_gui) {
        Change_GUI = change_gui;
    }

    public void setMain_gui1(JPanel main_gui) {
        Main_GUI = main_gui;
    }

    // 登录信息的获取
    // 获取操作的属性和信息传给Main_GUI函数让它显示出来
    public String setLogin_Name_Text(String login_Name_Text) {
        name = login_Name_Text;
        return name;
    }

    public String setLogin_Password_Text(String login_Password_Text) {
        password = login_Password_Text;
        return password;
    }

    public String getRole(String role) {
        Role = role;
        return Role;
    }

    //        public void setJ1(JFrame j12) {
//        j1 = j12;
//    }


    // 用户个人信息
    public void setPerson_Cancel(JButton person_Cancel) {
        Person_Cancel = person_Cancel;
    }

    public void setPerson_Ok(JButton person_Ok) {
        Person_Ok = person_Ok;
    }

    public void setPerson_Password_Text(JTextField person_Password_Text) {
        Person_Password_Text = person_Password_Text;
    }

    public void setPerson_Jframe(JFrame person_Jframe) {
        Person_Jframe = person_Jframe;
    }

    @Override
    // 点击菜单按钮弹出的界面窗口信息
    public void actionPerformed(ActionEvent e) {
        // 建立Main_GUI的对象  直接调用Main_GUI里面的界面函数对象
        Main_GUI main_gui = new Main_GUI();
        Constitute_GUI constitute_gui = new Constitute_GUI();
        if (e.getSource() == Change_User){
//            constitute_gui.Manage_Change();
            main_gui.Manage_GUI();
        } else if (e.getSource() == Delete_User){
//            constitute_gui.Manage_Delete();
            main_gui.Manage_GUI();
        } else if (e.getSource() == Add_User){
//            constitute_gui.Manage_Creator();
            main_gui.Manage_GUI();
        } else if (e.getSource() == Record_Upload){
            // 现在存在的jPanel
            if (Role.equals("Operator")) {
//                constitute_gui.Up_File();
                main_gui.File_GUI_Operator();
            } else {
                Browser_GUI browser_gui = new Browser_GUI();
//                constitute_gui.Up_File();
                browser_gui.File_GUI();
            }
        } else if (e.getSource() == Record_Download){
            if (Role.equals("Operator")) {
//                constitute_gui.DownLoad_File();
                main_gui.File_GUI_Operator();
            } else {
                Browser_GUI browser_gui = new Browser_GUI();
//                constitute_gui.DownLoad_File();
                browser_gui.File_GUI();
            }
        } else if (e.getSource() == Change_Information){
            main_gui.Change_Person_Information();
        } else if (e.getSource() == Open_Button){
            windows();
            // 文件删除取消按钮
        } else if (e.getSource() == Cancel_Button_UP){
            int ju = JOptionPane.showConfirmDialog(null, "\t\t\t确定退出", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                jFrame.dispose();
            }
            // 文件下载取消按钮
        } else if (e.getSource() == Cancel_Button_Down){
            int ju = JOptionPane.showConfirmDialog(null, "\t\t\t确定退出", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                jFrame.dispose();
            }
            // 上传文件按钮
        } else if (e.getSource() == UpFile_Button){
            show_UpFile();
        // else if括号
            // 个人信息界面确定按钮
        } else if (e.getSource() == Person_Ok){
            int ju = JOptionPane.showConfirmDialog(null, "\t\t\t确定修改", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                change_user_password();
            }
            // 个人信息界面取消按钮
        } else if (e.getSource() == Person_Cancel){
            int ju = JOptionPane.showConfirmDialog(null, "\t\t\t确定退出", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                Person_Jframe.dispose();
            }
        }
    // 监听函数的括号
    }


    // 弹出window的界面窗口  将文件信息进行显示
    void windows(){
        JFileChooser jf=new JFileChooser("F:/");
        int value=jf.showSaveDialog(null);
        if(value == JFileChooser.APPROVE_OPTION){ //判断窗口是否点的是打开或保存
            File getPath = jf.getSelectedFile(); //取得路径
            String file_path = getPath.getPath();
            // 窗口界面接收JFileChooser的内容对象
            File_Path_Text.setText(file_path);
//            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream())
        }else{
    // 没有选择，即点了窗口的取消
    //点了取消后有要做些什么
            File_Path_Text.setText("");
        }
    }


    // 上传文件中的show函数
    void show_UpFile(){
        if (Id_Text.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "\t\t\t请输入文件号", "Tip", JOptionPane.WARNING_MESSAGE);
        } else if (Descripte_Text.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "\t\t\t请输入描述", "Tip", JOptionPane.WARNING_MESSAGE);
        } else if (File_Path_Text.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "\t\t\t请输入文件路径", "Tip", JOptionPane.WARNING_MESSAGE);
        } else {
            // 进行判断
            int ju = JOptionPane.showConfirmDialog(null, "\t\t\t确定上传", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                // 进行查实以免发生错误
                try {
//                    Client_File_UP client_file_up = new Client_File_UP();
//                    client_file_up.Client_File_Up(File_Path_Text.getText(), Id_Text.getText(), );
                    boolean com = Up_file_GUI();
                    if (com) {
                        Id_Text.setText("");
                        Descripte_Text.setText("");
                        File_Path_Text.setText("");
//                        JOptionPane.showMessageDialog(null, "文件上传成功", "Tip", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "文件上传失败", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "文件上传失败", "WARNING", JOptionPane.WARNING_MESSAGE);
                    Id_Text.setText("");
                    Descripte_Text.setText("");
                    File_Path_Text.setText("");
                    e1.printStackTrace();
                }
                // if的括号
            }
        }
    }

    // 上传文件函数
    boolean Up_file_GUI(){
        // 获取登录的信息
        // 将获取的信息进行一个匹配
        try {
            User user = DataProcess.search(name, password);
            // 这个if的问题
            if (user != null){
                String role = user.getRole();

//                Operator operator = new Operator(name, password, role);
                try {
                    // 调取函数进行上传
                    Client_File_UP client_file_up = new Client_File_UP();
                    boolean m = client_file_up.Client_File_Up(File_Path_Text.getText(), Id_Text.getText(), role, Descripte_Text.getText());
//                    Client_File_UP client_file_up = new Client_File_UP();
//                    client_file_up.Client_File_Up()
//                    operator.UpFile(Id_Text.getText(), Descripte_Text.getText(), File_Path_Text.getText());
                    if (m) {
                        return true;
                    }
                    return false;
                } catch (Exception e1) {
                    e1.printStackTrace();
                    return false;
                }
            }
            return false;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
    // Up_file函数的括号
    }


    // 修改用户的函数
    void change_user_password(){
        try {
//            User user = DataProcess.search(name, password);
//            if (user != null) {
//                DataProcess.update(user.getName(), Person_Password_Text.getText(), user.getRole());
//                user.changeSelfInfo(Person_Password_Text.getText());

                Client_User_Update client_user_update = new Client_User_Update();
                client_user_update.setUser_Name(name);
                client_user_update.setUser_Pw(Person_Password_Text.getText());
                client_user_update.setUser_Role(Role);
                boolean ju = client_user_update.Client_Transmit();
                if (ju){
                Person_Password_Text.setText("");
                JOptionPane.showMessageDialog(null, "\t\t修改成功");
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "\t\t修改失败");
            Person_Password_Text.setText("");
            e1.printStackTrace();
        }
    }

}
