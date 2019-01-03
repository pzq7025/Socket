package homework;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

public class Constitute_GUI extends JFrame {

    static JPanel File_Up_j, File_Down_j;
    static JPanel Create_User_j, Change_User_j, Show_User_j;
    static String Name_User, Password_User, Role_User;

    Constitu_GUI_Plug constitu_gui_plug;

    public void setName_User(String name_User) {
        Name_User = name_User;
    }

    public void setPassword_User(String password_User) {
        Password_User = password_User;
    }

    public void setRole_User(String role_User) {
        Role_User = role_User;
    }


    // 管理者新建用户的界面
    JTextField Manage_Creator_Name_Text, Manage_Creator_Oral_Secret_Text;
    JLabel Manage_Creator_Label, Manage_Creator_Oral_Secret_Label, Manage_Creator_Role_Label;
    JButton Manage_Cancel_Button1, Manage_Create_Button;
    Box Create_box1, Create_box2, Create_box3, Change_box4;
    Box Create_Base2;
    String role;
    public JPanel Manage_Creator() {
        Create_User_j = new JPanel();
        Manage_Creator_Name_Text = new JTextField();
        Manage_Creator_Name_Text.setForeground(Color.BLUE);
        Manage_Creator_Name_Text.setSize(2, 3);

        Manage_Creator_Oral_Secret_Text = new JPasswordField();
        Manage_Creator_Oral_Secret_Text.setForeground(Color.BLUE);
        Manage_Creator_Oral_Secret_Text.setSize(2, 3);

        Manage_Create_Button = new JButton("新建");
        Manage_Create_Button.setForeground(Color.BLUE);

        Manage_Cancel_Button1 = new JButton("取消");
        Manage_Cancel_Button1.setForeground(Color.BLUE);

        Manage_Creator_Label = new JLabel("用户名");
        Manage_Creator_Label.setForeground(Color.BLUE);
        Manage_Creator_Label.setSize(2, 3);

        Manage_Creator_Oral_Secret_Label = new JLabel("密   令");
        Manage_Creator_Oral_Secret_Label.setForeground(Color.BLUE);
        Manage_Creator_Oral_Secret_Label.setSize(2, 3);

        Manage_Creator_Role_Label = new JLabel("角   色");
        Manage_Creator_Role_Label.setForeground(Color.BLUE);
        Manage_Creator_Role_Label.setSize(2, 3);


        String []Change = {"Browser", "Administrator", "Operator"};
        JComboBox<String> jComboBox_Change = new JComboBox<String>(Change);
        Create_box1 = Box.createHorizontalBox();
        Create_box1.add(Box.createHorizontalStrut(100));
        Create_box1.add(Manage_Creator_Label);
        Create_box1.add(Box.createHorizontalStrut(20));
        Create_box1.add(Manage_Creator_Name_Text);
        Create_box1.add(Box.createHorizontalStrut(100));

        Create_box2 = Box.createHorizontalBox();
        Create_box2.add(Box.createHorizontalStrut(100));
        Create_box2.add(Manage_Creator_Oral_Secret_Label);
        Create_box2.add(Box.createHorizontalStrut(20));
        Create_box2.add(Manage_Creator_Oral_Secret_Text);
        Create_box2.add(Box.createHorizontalStrut(100));

        Create_box3 = Box.createHorizontalBox();
        Create_box3.add(Box.createHorizontalStrut(100));
        Create_box3.add(Manage_Creator_Role_Label);
        Create_box3.add(Box.createHorizontalStrut(20));
        Create_box3.add(jComboBox_Change);
        Create_box3.add(Box.createHorizontalStrut(100));

        Change_box4 = Box.createHorizontalBox();
        Change_box4.add(Box.createHorizontalStrut(100));
        Change_box4.add(Manage_Create_Button);
        Change_box4.add(Box.createHorizontalStrut(50));
        Change_box4.add(Manage_Cancel_Button1);
        Change_box4.add(Box.createHorizontalStrut(100));

        Create_Base2 = Box.createVerticalBox();
        Create_Base2.add(Box.createVerticalStrut(10));
        Create_Base2.add(Create_box1);
        Create_Base2.add(Box.createVerticalStrut(20));
        Create_Base2.add(Create_box2);
        Create_Base2.add(Box.createVerticalStrut(20));
        Create_Base2.add(Create_box3);
        Create_Base2.add(Box.createVerticalStrut(20));
        Create_Base2.add(Change_box4);

        Create_User_j.add(Create_Base2);

//        jComboBox_Change.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                String comboxValue = (String) ((SteppedComboBox) e.getSource())
//                        .getSelectedItem();
//            }
//        });
        constitu_gui_plug = new Constitu_GUI_Plug();

        Manage_Create_Button.addActionListener(constitu_gui_plug);
        Manage_Cancel_Button1.addActionListener(constitu_gui_plug);

        constitu_gui_plug.setCreator_User_Button(Manage_Create_Button);
        constitu_gui_plug.setCancel_User_Button(Manage_Cancel_Button1);
        constitu_gui_plug.setCreator_User_Text(Manage_Creator_Name_Text);
        constitu_gui_plug.setSecret_User_Text(Manage_Creator_Oral_Secret_Text);

//        role = Objects.requireNonNull(jComboBox_Change.getSelectedItem()).toString();


        jComboBox_Change.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int j = jComboBox_Change.getSelectedIndex();
                for (int i = 0; i < Change.length; i++) {
                    if (i == j){
                        role = Change[i];
                        constitu_gui_plug.setRole_User(role);
                    }
                }
            }
        });

        Manage_Create_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 建立数据行向量 和数据列向量
                Vector<Vector<String>> Data = new Vector<>();
                Vector<String> name = new Vector<>();
                Vector<String> V_Creator = new Vector<>();
                Vector<String> V_Oral_Secret = new Vector<>();
                Vector<String> V_Role = new Vector<>();
                V_Creator.add( Manage_Creator_Name_Text.getText());
                V_Oral_Secret.add( Manage_Creator_Oral_Secret_Text.getText());
                V_Role.add(role);
//                tableModel.addRow(V_Creator);
//                tableModel.addRow(V_Oral_Secret);
//                tableModel.addRow(V_Role);
                name.add(String.valueOf(V_Creator));
                name.add(String.valueOf(V_Oral_Secret));
                name.add(String.valueOf(V_Role));
//                User_table.updateUI();
//                tableModel.setDataVector(V_Creator, V_Oral_Secret, V_Role);
//                JTable jTable1 = new JTable();
//                User_table.setModel(tableModel);
                // 添加无法即使更新
                tableModel.setDataVector(Data, name);
//                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.setDataVector(Data,name);
                User_table.setModel(tableModel);
//                User_table.updateUI();
//                tableModel.fireTableDataChanged();
            }
        });

//        pack();
        // 窗口可见
//        setVisible(true);
        // 大小不可变
//        setResizable(false);
        // 设置大小及位置
//        setLocation(400, 220);
//        setSize(500, 300);
        // 关闭方式
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        Create_User_j.add(this);
        return Create_User_j;
    }


//     设置修改用户的GUI界面
    JTextField Manage_Change_Name_Text, Manage_Change_Oral_Secret_Text;
    JLabel Manage_Change_Label, Manage_Change_Oral_Secret_Label, Manage_Change_Role_Label;
    Box Change_box1, Change_box2, Change_box3, Change_box4_1;
    Box Change_Base3;
    JButton Manage_Change_Button, Manage_Cancel_Button3;
    String role_change;
    public JPanel Manage_Change(){
        Change_User_j = new JPanel();
        Manage_Change_Label = new JLabel("用户名");
        Manage_Change_Label.setForeground(Color.BLUE);
        Manage_Change_Label.setSize(2, 3);

        Manage_Change_Oral_Secret_Label = new JLabel("密   令");
        Manage_Change_Oral_Secret_Label.setForeground(Color.BLUE);
        Manage_Change_Oral_Secret_Label.setSize(2, 3);

        Manage_Change_Role_Label = new JLabel("角   色");
        Manage_Change_Role_Label.setForeground(Color.BLUE);
        Manage_Change_Role_Label.setSize(2, 3);

        Manage_Change_Name_Text = new JTextField();
        Manage_Change_Name_Text.setForeground(Color.BLUE);
        Manage_Change_Name_Text.setSize(2, 3);

        Manage_Change_Oral_Secret_Text = new JPasswordField();
        Manage_Change_Oral_Secret_Text.setForeground(Color.BLUE);
        Manage_Change_Oral_Secret_Text.setSize(2, 3);

        Manage_Change_Button = new JButton("修改");
        Manage_Change_Button.setForeground(Color.BLUE);

        Manage_Cancel_Button3 = new JButton("取消");
        Manage_Cancel_Button3.setForeground(Color.BLUE);


        String []Change = {"Browser", "Administrator", "Operator"};
        JComboBox<String> jComboBox_Change = new JComboBox<String>(Change);
        Change_box1 = Box.createHorizontalBox();
        Change_box1.add(Box.createHorizontalStrut(100));
        Change_box1.add(Manage_Change_Label);
        Change_box1.add(Box.createHorizontalStrut(20));
        Change_box1.add(Manage_Change_Name_Text);
        Change_box1.add(Box.createHorizontalStrut(100));

        Change_box2 = Box.createHorizontalBox();
        Change_box2.add(Box.createHorizontalStrut(100));
        Change_box2.add(Manage_Change_Oral_Secret_Label);
        Change_box2.add(Box.createHorizontalStrut(20));
        Change_box2.add(Manage_Change_Oral_Secret_Text);
        Change_box2.add(Box.createHorizontalStrut(100));

        Change_box3 = Box.createHorizontalBox();
        Change_box3.add(Box.createHorizontalStrut(100));
        Change_box3.add(Manage_Change_Role_Label);
        Change_box3.add(Box.createHorizontalStrut(20));
        Change_box3.add(jComboBox_Change);
        Change_box3.add(Box.createHorizontalStrut(100));

        Change_box4_1 = Box.createHorizontalBox();
        Change_box4_1.add(Box.createHorizontalStrut(100));
        Change_box4_1.add(Manage_Change_Button);
        Change_box4_1.add(Box.createHorizontalStrut(50));
        Change_box4_1.add(Manage_Cancel_Button3);
        Change_box4_1.add(Box.createHorizontalStrut(100));

        Change_Base3 = Box.createVerticalBox();
        Change_Base3.add(Box.createVerticalStrut(15));
        Change_Base3.add(Change_box1);
        Change_Base3.add(Box.createVerticalStrut(25));
        Change_Base3.add(Change_box2);
        Change_Base3.add(Box.createVerticalStrut(25));
        Change_Base3.add(Change_box3);
        Change_Base3.add(Box.createVerticalStrut(25));
        Change_Base3.add(Change_box4_1);

        Change_User_j.add(Change_Base3);

        Constitu_GUI_Plug constitu_gui_plug = new Constitu_GUI_Plug();

        Manage_Change_Button.addActionListener(constitu_gui_plug);
        Manage_Cancel_Button3.addActionListener(constitu_gui_plug);
//        jComboBox_Change.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                role = (String)jComboBox_Change.getSelectedItem();
//            }
//        });

        constitu_gui_plug.setChange_Name_Text(Manage_Change_Name_Text);
        constitu_gui_plug.setChange_secret_Text(Manage_Change_Oral_Secret_Text);
//        int r = jComboBox_Change.getSelectedIndex();
//        role = Objects.requireNonNull(jComboBox_Change.getSelectedItem()).toString();
        // 获取选择的角色名称
//        String role = Objects.requireNonNull(jComboBox_Change.getSelectedItem()).toString();
//        constitu_gui_plug.setRole_(role);
        constitu_gui_plug.setChange_User(Manage_Change_Button);
        constitu_gui_plug.setCancel_Button(Manage_Cancel_Button3);

        jComboBox_Change.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int j = jComboBox_Change.getSelectedIndex();
                for (int i = 0; i < Change.length; i++) {
                    if (i == j) {
                        role_change = Change[j];
                        constitu_gui_plug.setRole_(role_change);
                    }
                }
            }
        });

//        role = (String)jComboBox_Change.getSelectedItem();


//        setLayout(new FlowLayout());
        // 设置参数
//        pack();
        // 窗口可见
//        setVisible(true);
        // 大小不可变
//        setResizable(false);
        // 设置大小及位置
//        setLocation(400, 220);
//        setSize(400, 300);
        // 关闭方式
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        return Change_User_j;
    }



    //  显示用户列表以及删除用户信息
    // 将DefaultTable设置为全局变量  为了能够是Up_File文件使用
    static DefaultTableModel tableModel;
    static JTable User_table;
    Box List_Base1;
    JButton Manage_Cancel_Button2, Manage_Delete_Button;
    public JPanel Manage_Delete(){
        Show_User_j = new JPanel();
        Manage_Delete_Button = new JButton("删除");
        Manage_Delete_Button.setForeground(Color.BLUE);

        Manage_Cancel_Button2 = new JButton("取消");
        Manage_Cancel_Button2.setForeground(Color.BLUE);

        // 用户列表里面的信息 表头 信息内容等
        String [] column_Name = {"用户名", "口令", "角色"};
        // 20行3列
        String [][] content = new String[20][3];
        // 20行5列
        try {
            DataProcess.User_DB();
            Enumeration<User> e = DataProcess.getAllUser();
            User user;
            int row = 0;
            while (e.hasMoreElements()) {
                user = e.nextElement();
                content[row][0] = user.getName();
                content[row][1] = user.getPassword();
                content[row][2] = user.getRole();
                row++;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


        // 获取表的数据和表头名
        tableModel = new DefaultTableModel(content, column_Name);
        User_table = new JTable(tableModel);


        // 设置表信息
        User_table.setForeground(Color.BLACK);
        User_table.setFont(new Font(null, Font.PLAIN, 15));
        User_table.setSelectionBackground(Color.LIGHT_GRAY);
        User_table.setForeground(Color.BLACK);
        User_table.setGridColor(Color.GRAY);

        // 设置表头信息
        User_table.getTableHeader().setFont(new Font(null, Font.BOLD, 15));
        User_table.getTableHeader().setForeground(Color.RED);
        User_table.getTableHeader().setResizingAllowed(false);       // 不可改变
        User_table.getTableHeader().setReorderingAllowed(false);     //  不可移动


        // 设置行高
        User_table.setRowHeight(20);

        // 设置第一列宽度
        User_table.getColumnModel().getColumn(0).setPreferredWidth(60);

        // 将表格放到滚动面板中
        JScrollPane scrollPane = new JScrollPane(User_table);

        // 设置添加的JPanel组件 往窗口添加东西
        JPanel window = (JPanel) this.getContentPane();

        // 设置scrollPane的大小
        JPanel jP_Scorll = new JPanel();

        // 添加按钮的Jpanel
        JPanel jP_Add_Button = new JPanel();

        // 设置滚动面板视口大小
        scrollPane.setPreferredSize(new Dimension(485, 200));

        // 设置网格布局
        jP_Scorll.setLayout(new GridBagLayout());
        jP_Scorll.add(scrollPane);

        List_Base1 = Box.createHorizontalBox();
        List_Base1.add(Box.createHorizontalStrut(10));
        List_Base1.add(Manage_Delete_Button);
        List_Base1.add(Box.createHorizontalStrut(40));
        List_Base1.add(Manage_Cancel_Button2);

        jP_Add_Button.add(List_Base1);

        // 进行整个组件的组合
        window.setLayout(new BorderLayout());
        window.add(jP_Scorll, BorderLayout.CENTER);
        window.add(jP_Add_Button, BorderLayout.SOUTH);

        Show_User_j.add(window);
        // 将监听的响应以及按钮传给JFrame
        Constitu_GUI_Plug constitu_gui_plug = new Constitu_GUI_Plug();
        Manage_Cancel_Button2.addActionListener(constitu_gui_plug);
        constitu_gui_plug.setDelete_User_Button(Manage_Cancel_Button2);

        Manage_Delete_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = User_table.getSelectedRow();
                    // 读取第几行的信息  并获取它的字段内容
                    String Name = User_table.getValueAt(index, 0).toString();
                    boolean com = Show_Delete_Detail(Name);
                    if (com){
//                        JOptionPane.showMessageDialog(null, "删除成功", "Tip", JOptionPane.WARNING_MESSAGE);
                        // 删除选中的一行
                        // 对表进行刷新
                        tableModel.removeRow(index);
                        // 批量删除数据
//                    tableModel.fireTableRowsDeleted(index, index);
                        tableModel.fireTableDataChanged();
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败", "Tip", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "请选择删除对象", "Tip", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        // 将对象转化成一个JPanel的对象来实现
        return Show_User_j;
    }


    // 上传文件
    JTextArea UpFile_Descripe_Area;
    JLabel UpFile_Descripe_Label, UpFile_Id_Label, UpFile_Name_Label;
    JTextField UpFile_Id_Text, UpFile_Path_Text;
    JButton Open_Button, UpLoad_Button, Cancel_Button;
    Box File_box1, File_box2, File_box3, File_box4, File_Base;
    public JPanel Up_File(){
        File_Up_j = new JPanel();
        UpFile_Descripe_Area = new JTextArea(5, 6);
//        Descripe_Area.setBounds(0, 0, 2, 2);
        UpFile_Descripe_Area.setTabSize(3);

        UpFile_Id_Text = new JTextField();
//        Id_Text.setBounds(0, 0, 2, 2);


        UpFile_Path_Text = new JTextField();
//        upfile_Text.setBounds(0, 0, 5, 6);
        UpFile_Path_Text.setColumns(10);

        UpFile_Descripe_Label = new JLabel("文件描述");
        UpFile_Descripe_Label.setForeground(Color.BLUE);
//        Descripe_Label.setBounds(0, 0, 2, 2);

        UpFile_Id_Label = new JLabel("文件编号");
        UpFile_Id_Label.setForeground(Color.BLUE);
//        Id_Label.setBounds(0, 0, 2, 2);

        UpFile_Name_Label = new JLabel("文件路径");
        UpFile_Name_Label.setForeground(Color.BLUE);
//        File_Name_Label.setBounds(0, 0, 2, 2);

        Open_Button = new JButton("打开");
        Open_Button.setForeground(Color.BLUE);
//        Open_Button.setBounds(0, 0, 2, 2);

        UpLoad_Button = new JButton("上传");
        UpLoad_Button.setForeground(Color.BLUE);
//        UpLoad_Button.setBounds(0, 0, 2, 2);

        Cancel_Button = new JButton("取消");
        Cancel_Button.setForeground(Color.BLUE);
//        Cancel_Button.setBounds(0, 0, 2, 2);


        File_box1 = Box.createHorizontalBox();
        File_box1.add(Box.createHorizontalStrut(100));
        File_box1.add(UpFile_Id_Label);
        File_box1.add(Box.createHorizontalStrut(20));
        File_box1.add(UpFile_Id_Text);
        File_box1.add(Box.createHorizontalStrut(100));


        File_box2 = Box.createHorizontalBox();
        File_box2.add(Box.createHorizontalStrut(100));
        File_box2.add(UpFile_Descripe_Label);
        File_box2.add(Box.createHorizontalStrut(20));
        File_box2.add(UpFile_Descripe_Area);
        File_box2.add(Box.createHorizontalStrut(100));

        File_box3 = Box.createHorizontalBox();
        File_box3.add(Box.createHorizontalStrut(100));
        File_box3.add(UpFile_Name_Label);
        File_box3.add(Box.createHorizontalStrut(20));
        File_box3.add(UpFile_Path_Text);
        File_box3.add(Box.createHorizontalStrut(20));
        File_box3.add(Open_Button);
        File_box3.add(Box.createHorizontalStrut(100));

        File_box4 = Box.createHorizontalBox();
        File_box4.add(Box.createHorizontalStrut(100));
        File_box4.add(UpLoad_Button);
        File_box4.add(Box.createHorizontalStrut(30));
        File_box4.add(Cancel_Button);
        File_box4.add(Box.createHorizontalStrut(100));


        File_Base = Box.createVerticalBox();
        File_Base.add(Box.createVerticalStrut(20));
        File_Base.add(File_box1);
        File_Base.add(Box.createVerticalStrut(20));
        File_Base.add(File_box2);
        File_Base.add(Box.createVerticalStrut(20));
        File_Base.add(File_box3);
        File_Base.add(Box.createVerticalStrut(20));
        File_Base.add(File_box4);

        File_Up_j.add(File_Base);

        Main_GUI_Plugin main_gui_plugin = new Main_GUI_Plugin();

        // 建立监听响应
        Open_Button.addActionListener(main_gui_plugin);
        UpLoad_Button.addActionListener(main_gui_plugin);
        Cancel_Button.addActionListener(main_gui_plugin);

        main_gui_plugin.setOpen_Button(Open_Button);
        main_gui_plugin.setUpFile_Button(UpLoad_Button);
        main_gui_plugin.setCancel_Button_UP(Cancel_Button);
        main_gui_plugin.setFile_Path_Text(UpFile_Path_Text);
        main_gui_plugin.setId_Text(UpFile_Id_Text);
        main_gui_plugin.setDescripte_Text(UpFile_Descripe_Area);
//        main_gui_plugin.setjFrame(this);

        UpLoad_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里完成函数的响应  来实现函数里面的监听以及功能

            }
        });
        return File_Up_j;
    }

    // 下载文件
    JButton FileDown_Load, Back;
    Box box1;
    public JPanel DownLoad_File(){
            File_Down_j = new JPanel();
            FileDown_Load = new JButton("下载");
            FileDown_Load.setBounds(0,0,  5, 6);
            FileDown_Load.setForeground(Color.BLUE);

            Back = new JButton("返回");
            Back.setBounds(0, 0, 5, 6);
            Back.setForeground(Color.BLUE);
            // 表头  信息内容
            String [] column_Name = {"档案号", "上传者", "时间", "文件名", "描述"};
            // 20行5列
            String [][] content = new String[20][5];
            try {
                // 从数据库获取信息
                DataProcess.Doc_DB();
                // 枚举输入
                Enumeration<Doc> e = DataProcess.getAllDocs();
                Doc doc;
                int row = 0;
                while (e.hasMoreElements()){
                    doc = e.nextElement();
                    content[row][0] = doc.getID();
                    content[row][1] = doc.getCreator();
                    content[row][2] = doc.getTimestamp().toString();
                    content[row][3] = doc.getFilename();
                    content[row][4] = doc.getDescription();
                    row++;
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        // 获取表的数据和表头名
            DefaultTableModel model = new DefaultTableModel(content, column_Name);
            JTable table = new JTable(model);

            // 表示获取第几行
//            int index = table.getSelectedRow();
            // 读取第几行的信息  并获取它的字段内容
//            String id = table.getValueAt(index, 0).toString();


                // 设置表信息
            table.setForeground(Color.BLACK);                            // 字体颜色
            table.setFont(new Font(null, Font.PLAIN, 15));   // 字体样式
            table.setSelectionBackground(Color.LIGHT_GRAY);                    // 选中后字体颜色
            table.setForeground(Color.BLACK);                       // 选中后字体背景
            table.setGridColor(Color.GRAY);                              // 网格颜色

            // 设置表头信息
            table.getTableHeader().setFont(new Font(null, Font.BOLD, 15));      // 设置表头名称字样
            table.getTableHeader().setForeground(Color.RED);          // 设置表头名称颜色
            table.getTableHeader().setResizingAllowed(false);           // 不允许改变宽度
            table.getTableHeader().setReorderingAllowed(false);         // 不允许重新排列

            // 设置行高
            table.setRowHeight(20);

            // 第一列宽度
            table.getColumnModel().getColumn(0).setPreferredWidth(80);

            // 将表格放到滚动面板中
            JScrollPane scrollPane = new JScrollPane(table);

            // 设置添加的JPanel组件  可以往窗口中添加东西
            JPanel window = (JPanel) this.getContentPane();

            // 设置scrollPane的大小
            JPanel Jp_scorll = new JPanel();

            // 添加按钮的JPanel
            JPanel Jp_Add_Button = new JPanel();

            // 设置滚动面板视口大小
            scrollPane.setPreferredSize(new Dimension(485, 200));

            // 设置网格布局
            Jp_scorll.setLayout(new GridBagLayout());
            Jp_scorll.add(scrollPane);

            box1 = Box.createHorizontalBox();
            box1.add(Box.createHorizontalStrut(10));
            box1.add(FileDown_Load);
            box1.add(Box.createHorizontalStrut(40));
            box1.add(Back);


            Jp_Add_Button.add(box1);
        //    table.setAutoCreateColumnsFromModel(AUTO_RESIZE_ALL_COLUMNS);

            // 进行整个组件的组合
            window.setLayout(new BorderLayout());
            window.add(Jp_scorll, BorderLayout.CENTER);
            window.add(Jp_Add_Button, BorderLayout.SOUTH);


            Main_GUI_Plugin main_gui_plugin = new Main_GUI_Plugin();
            Back.addActionListener(main_gui_plugin);
            main_gui_plugin.setCancel_Button_Down(Back);

            File_Down_j.add(window);

            FileDown_Load.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        // 表示获取第几行
                        int index = table.getSelectedRow();
                        // 读取第几行的信息  并获取它的字段内容
                        String id = table.getValueAt(index, 0).toString();
                        Show_Down_Path(id);
                    } catch (Exception e1){
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "请选择下载对象", "Tip", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            // 设置参数
            // 关闭方式
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            return File_Down_j;
    }

    // 获取文件的下载
    Client_Up_File client_up_file = new Client_Up_File();
    void Show_Down_Path(String Id) throws Exception {
        // 选择下载的文件
        JFileChooser jFileChooser = new JFileChooser("E:\\exploitation\\codes");
        int value = jFileChooser.showSaveDialog(null);
        if (value == JFileChooser.APPROVE_OPTION){
            // 获取文件路径
            File get_path = jFileChooser.getSelectedFile();
            // 获取文件的绝对路径来完整的写入文件
            String file_path = get_path.getAbsolutePath();
//            boolean com = Down_File(Id, file_path);
//            client_thread.run();
//            client_thread.setFile_path(file_path);
            boolean com = client_up_file.Client_File_Down(file_path);
            if (com){
                JOptionPane.showMessageDialog(null, "下载成功");
            } else {
                JOptionPane.showMessageDialog(null, "下载失败", "Tip", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // 下载文件的函数
    // 文件ID:获取下载的文件
    // 文件名:需要下载的文件名
    // 文件路径:需要下载到那个地方的路径
    // 上传时的文件路径
    String UpLoadPath = "E:\\exploitation\\codes\\uploadfile\\";
    boolean Down_File(String Id, String file_path){
        try {
            Doc doc = DataProcess.search_Doc(Id);
            if (doc != null){
                try {
                    byte[] bytes= new byte[1024];
                    // 建立完整的文件对象
                    File Gain_File = new File(UpLoadPath + doc.getFilename());
                    // 写入流读取文件
                    BufferedInputStream infile = new BufferedInputStream(new FileInputStream(Gain_File));
                    // 输出流将文件写入的地方
                    BufferedOutputStream outfile = new BufferedOutputStream(new FileOutputStream(file_path));
                    // 开始写入
                    while(true){
                        // 先读后写
                        int byteRead = infile.read(bytes);
                        if (byteRead == -1)
                            break;
                        outfile.write(bytes, 0, byteRead);
                    }
                    // 关闭资源
                    infile.close();
                    outfile.close();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除用户
    boolean Show_Delete_Detail(String Name){
        try {
            Client_User_Delete client_user_delete = new Client_User_Delete();
            client_user_delete.set_Name(Name);
            boolean ju = client_user_delete.Client_Transmit();
            if (ju){
                return true;
            }
            return false;
//            DataProcess.delete(Name);
//            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 测试函数的类
     */
//    public static void main(String[] args){
//            Constitute_GUI constitute_gui = new Constitute_GUI();
//            constitute_gui.db();
//    }
}
