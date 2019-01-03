package homework;

/**
 * 所有需要的GUI全在这里
 */

import javax.swing.*;
import java.awt.*;



public class Main_GUI extends JFrame {
    // 外部类访问时 设置函数的接口来实现接口
    private JMenuBar Total;
    private JMenu User_Manage, Record_Manage, Personal_Manage;
    private JMenuItem Change_User, Delete_User, Add_User, Record_Download, Change_Information, Record_Upload;
    // 将对象传递给对应的配置文件
    Main_GUI_Plugin mop;
    Constitu_GUI_Plug constitu_gui_plug;

    static String Name, Password, Role;

    // 接收操作者的信息
    public String set_Name(String name){
        Name = name;
        return Name;
    }

    public String set_Pass(String pass){
        Password = pass;
        return Password;
    }

    public String set_Role(String role){
        Role = role;
        return Role;
    }



    // 主界面的显示
    void init(){
        // 建立JMenuBar的按钮 在Swing中有预留 用setJMenuBar使其预留出空间
        Total = new JMenuBar();

        // 实例化Menu菜单选项
        User_Manage = new JMenu("用户管理");
        Record_Manage = new JMenu("档案管理");
        Personal_Manage = new JMenu("个人信息管理");

        // 实例化MenuItem的选项
        Change_User = new JMenuItem("修改用户");
        Delete_User = new JMenuItem("删除用户");
        Add_User = new JMenuItem("新增用户");
        Record_Upload = new JMenuItem("档案上传");
        Record_Download = new JMenuItem("档案下载");
        Change_Information = new JMenuItem("个人信息修改");

        // 使其可视化
        setJMenuBar(Total);

        // 添加到Total中
        Total.add(User_Manage);
        Total.add(Record_Manage);
        Total.add(Personal_Manage);

        // 添加到User_Manage中
        User_Manage.add(Change_User);
        User_Manage.add(Delete_User);
        User_Manage.add(Add_User);

        // 添加到Record中
        Record_Manage.add(Record_Upload);
        Record_Manage.add(Record_Download);

        // 添加到Personal_Manage中
        Personal_Manage.add(Change_Information);


        // 建立监听传递监听
        mop = new Main_GUI_Plugin();


        // 建立对应的监听对象
        Change_User.addActionListener(mop);
        Delete_User.addActionListener(mop);
        Add_User.addActionListener(mop);
        Record_Upload.addActionListener(mop);
        Record_Download.addActionListener(mop);
        Change_Information.addActionListener(mop);


        // 将监听对象进行传递
        mop.setChange_User(Change_User);
        mop.setDelete_User(Delete_User);
        mop.setAdd_User(Add_User);
        mop.setRecord_Download(Record_Download);
        mop.setRecord_Upload(Record_Upload);
        mop.setChange_Information(Change_Information);



        ImageIcon image=new ImageIcon("F:\\exploitation\\codes\\java_codes_project\\new_project\\src\\signer\\time5.jpg");
        JLabel logo_label = new JLabel(image);

        logo_label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());

        // 设置底层把图片放在最下面的一层
        getLayeredPane().add(logo_label, Integer.valueOf(Integer.MIN_VALUE));

        //设置内容面板  getContentPane前面添加  这个JFrame的对象 由于这个图片是继承了JFrame  所以不需要对象 或者使用this
        JPanel jp = (JPanel) this.getContentPane();

        //设置内容面板未透明  true代表透明  透明之后的gui界面是看不到背景图像的
        jp.setOpaque(false);


        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBounds(250, 200, 500, 350);
    }


    private static JPanel Main_contentPane;
    private static JPanel Add_panel;
    //  点击文件管理菜单弹出的GUI界面
    private JMenuBar File_GUI;
    private JMenu File_Up, File_Down;
    private JPanel File_Down_Panel, File_Up_panel;
    private JTabbedPane File_TabbedPane;
//    JTabbedPane tb1;
    JButton Down_Load, Back;
    Box box1;
    // 一般用户
    void File_GUI(){
        // 文件下载界面
        File_GUI = new JMenuBar();

        File_Up = new JMenu("上传文件");
        File_Down = new JMenu("文件下载");
        File_Up_panel = new JPanel();
        File_Down_Panel = new JPanel();
        Add_panel = new JPanel();
        Main_contentPane = new JPanel();
        File_TabbedPane = new JTabbedPane();

        // 在JMenuBar中添加JMenu 按照添加顺序进行添加
        File_GUI.add(File_Down);
        File_GUI.add(File_Up);

        Main_GUI_Plugin main_gui_plugin = new Main_GUI_Plugin();
        main_gui_plugin.setjFrame(this);

        constitu_gui_plug = new Constitu_GUI_Plug();

        Constitute_GUI constitute_gui = new Constitute_GUI();
        File_Up.getAction();
        File_TabbedPane.addTab("下载文件", null, constitute_gui.DownLoad_File(), null);
        File_TabbedPane.addTab("上传文件", null, constitute_gui.Up_File(), null);
        File_TabbedPane.setEnabledAt(1, false);

        add(File_TabbedPane);

        // 这两个没用到 无意义  后面删除 要采取JTabbedPane的方式
        constitu_gui_plug.setFile_Up(File_Up);
        constitu_gui_plug.setFile_Down(File_Down);
        setTitle("文件管理");
        // 窗口可见
        setVisible(true);
        // 大小不可变
        setResizable(false);
        // 设置大小及位置
        setLocation(400, 220);
        setSize(500, 300);
        // 关闭方式
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    // 通过用户的不同来调用不一样文件GUI界面
    // 文件管理者界面
    private JTabbedPane File_TabbedPane_Operator;
    void File_GUI_Operator(){
        File_TabbedPane_Operator = new JTabbedPane();
        Constitute_GUI constitute_gui = new Constitute_GUI();
        File_TabbedPane_Operator.addTab("下载文件", null, constitute_gui.DownLoad_File(), null);
        File_TabbedPane_Operator.addTab("上传文件", null, constitute_gui.Up_File(), null);

        add(File_TabbedPane_Operator);
        Main_GUI_Plugin main_gui_plugin = new Main_GUI_Plugin();
        main_gui_plugin.setjFrame(this);

        setTitle("文件管理");
        // 窗口可见
        setVisible(true);
        // 大小不可变
        setResizable(false);
        // 设置大小及位置
        setLocation(400, 220);
        setSize(500, 300);
        // 关闭方式
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    // 点击修改用户菜单弹出的窗口  个人信息修改界面
    // 用户信息修改界面  用户界面就一个  就放在这里
    JLabel Person_Name_Label, Person_PassWord_Label, Person_Identity_Label;
    JTextField Person_Name_Text, Persona_Password_Text, Person_Identity_Text;
    JButton Ok_Person_Button, Cancel_Person_Button;
    Box P_box1, P_box2, P_box3, P_box4, p_Base;
    Main_GUI_Plugin main_gui_plugin;
    void Change_Person_Information(){
        Person_Name_Label = new JLabel("用户的姓名");
        Person_Name_Label.setForeground(Color.BLUE);

        Person_PassWord_Label = new JLabel("输入新密码");
        Person_PassWord_Label.setForeground(Color.BLUE);

        Person_Identity_Label = new JLabel("操作者类型");
        Person_Identity_Label.setForeground(Color.BLUE);

        Person_Name_Text = new JTextField(Name);
        Person_Name_Text.setEnabled(false);
        Person_Name_Text.setSize(2,3);

        Persona_Password_Text = new JPasswordField();
        Persona_Password_Text.setSize(2, 3);


        Person_Identity_Text = new JTextField(Role);
        Person_Identity_Text.setEnabled(false);
        Person_Identity_Text.setSize(2, 3);

        Ok_Person_Button = new JButton("确定");
        Ok_Person_Button.setForeground(Color.BLUE);
        Ok_Person_Button.setSize(2, 3);

        Cancel_Person_Button = new JButton("取消");
        Cancel_Person_Button.setForeground(Color.BLUE);
//        Cancel_Button.setSize(2, 3);

        P_box1 = Box.createHorizontalBox();
        P_box1.add(Box.createHorizontalStrut(100));
        P_box1.add(Person_Name_Label);
        P_box1.add(Box.createHorizontalStrut(20));
        P_box1.add(Person_Name_Text);
        P_box1.add(Box.createHorizontalStrut(100));

        P_box2 = Box.createHorizontalBox();
        P_box2.add(Box.createHorizontalStrut(100));
        P_box2.add(Person_PassWord_Label);
        P_box2.add(Box.createHorizontalStrut(20));
        P_box2.add(Persona_Password_Text);
        P_box2.add(Box.createHorizontalStrut(100));

        P_box4 = Box.createHorizontalBox();
        P_box4.add(Box.createHorizontalStrut(100));
        P_box4.add(Person_Identity_Label);
        P_box4.add(Box.createHorizontalStrut(20));
        P_box4.add(Person_Identity_Text);
        P_box4.add(Box.createHorizontalStrut(100));

        P_box3 = Box.createHorizontalBox();
        P_box3.add(Box.createHorizontalStrut(100));
        P_box3.add(Ok_Person_Button);
        P_box3.add(Box.createHorizontalStrut(50));
        P_box3.add(Cancel_Person_Button);
        P_box3.add(Box.createHorizontalStrut(100));

        p_Base = Box.createVerticalBox();
        p_Base.add(Box.createVerticalStrut(20));
        p_Base.add(P_box1);
        p_Base.add(Box.createVerticalStrut(30));
        p_Base.add(P_box2);
        p_Base.add(Box.createVerticalStrut(30));
        p_Base.add(P_box4);
        p_Base.add(Box.createVerticalStrut(30));
        p_Base.add(P_box3);


        add(p_Base);

        main_gui_plugin = new Main_GUI_Plugin();


        Ok_Person_Button.addActionListener(main_gui_plugin);
        Cancel_Person_Button.addActionListener(main_gui_plugin);


        main_gui_plugin.setPerson_Password_Text(Persona_Password_Text);
        main_gui_plugin.setPerson_Ok(Ok_Person_Button);
        main_gui_plugin.setPerson_Cancel(Cancel_Person_Button);
        main_gui_plugin.setPerson_Jframe(this);


        setLayout(new FlowLayout());
        setTitle("用户信息修改");
        // 窗口可见
        setVisible(true);
        // 大小不可变
        setResizable(false);
        // 设置大小及位置
        setLocation(400, 220);
        setSize(300, 300);
//        setBounds(400, 220, 300, 300);
//        setLocation(400, 220);
        // 关闭方式
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }



    // 管理者界面
    private JMenuBar Manage_GUI;
    private JMenu Manage_User_List_JMenu, Manage_Create_User_JMenu, Manage_Change_infos_JMenu;
    private JTabbedPane Manage_TabbedPane;
    void Manage_GUI(){
        // 显示用户界面以及删除用户界面
        Manage_GUI = new JMenuBar();
        Manage_TabbedPane = new JTabbedPane();

        Manage_Create_User_JMenu = new JMenu("新增用户");
        Manage_Change_infos_JMenu = new JMenu("修改用户");
        Manage_User_List_JMenu = new JMenu("用户列表");

        Constitute_GUI constitute_gui = new Constitute_GUI();

        Manage_TabbedPane.addTab("新建用户", null, constitute_gui.Manage_Creator(), null);
        Manage_TabbedPane.addTab("修改用户", null, constitute_gui.Manage_Change(), null);
        Manage_TabbedPane.addTab("删除用户", null, constitute_gui.Manage_Delete(), null);

        add(Manage_TabbedPane);

        constitu_gui_plug = new Constitu_GUI_Plug();

        Manage_Create_User_JMenu.addActionListener(constitu_gui_plug);

        constitu_gui_plug.setMange_Create_User(Manage_Create_User_JMenu);
        // 将需要关闭的窗口传给新建用户的接收器
        // 新建用户的JFrame接收器
        constitu_gui_plug.setUser_Creator_Jframe(this);
        // 删除用户的JFrame接收器
        constitu_gui_plug.setDelete_Cancel_JFrame(this);
        // 修改用户的JFrame接收器
        constitu_gui_plug.setjFrame(this);

        pack();
        // 窗口可见
        setVisible(true);
        // 大小不可变
        setResizable(false);
        // 设置大小及位置
        setLocation(400, 220);
        setSize(500, 300);
        // 关闭方式
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args){
            Main_GUI main_gui = new Main_GUI();
            main_gui.init();
    }


    // 设置接口函数
    public JMenuBar getTotal() {
        return Total;
    }

    public JMenu getUser_Manage() {
        return User_Manage;
    }

    public JMenu getRecord_Manage() {
        return Record_Manage;
    }

    public JMenu getPersonal_Manage() {
        return Personal_Manage;
    }

    public JMenuItem getRecord_Upload() {
        return Record_Upload;
    }

    public JMenu getFile_Up() {
        return File_Up;
    }

}
