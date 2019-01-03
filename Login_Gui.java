package homework;

import javax.swing.*;
import java.awt.*;

/**
 * 建立一个登录的界面
 */

public class Login_Gui extends JFrame {
    private JLabel Name_Lable, Password_Lable;
    private JTextField Name_Text, Password_Text;
    private JButton Ok_Button, Cancel_Button;
    private Box box1, box2, box3, Base1, Base2;
    Login_GUI_Plugin lu;
    Main_GUI_Plugin mgp;

    void init(){
        Name_Lable = new JLabel("账号");
        Name_Lable.setForeground(Color.GREEN);
        Password_Lable = new JLabel("密码");
        Password_Lable.setForeground(Color.GREEN);
        Name_Text = new JTextField(10);
        Password_Text = new JPasswordField(10);
        Ok_Button = new JButton("确定");
        Ok_Button.setBounds(0, 0, 5, 6);
        Ok_Button.setForeground(Color.BLUE);
        Cancel_Button = new JButton("退出");
        Cancel_Button.setBounds(0, 0, 5, 6);
        Cancel_Button.setForeground(Color.BLUE);
        // 将登陆信息进行传递
        lu = new Login_GUI_Plugin();
        mgp = new Main_GUI_Plugin();


        // 构建布局
        box1 = Box.createHorizontalBox();
        box1.add(Box.createHorizontalStrut(30));
        box1.add(Name_Lable);
        box1.add(Box.createHorizontalStrut(0));
        box1.add(Name_Text);


        box2 = Box.createHorizontalBox();
        box2.add(Box.createHorizontalStrut(30));
        box2.add(Password_Lable);
        box2.add(Box.createHorizontalStrut(0));
        box2.add(Password_Text);


        box3 = Box.createHorizontalBox();
        box3.add(Box.createHorizontalStrut(30));
        box3.add(Ok_Button);
        // 两个按钮之间的距离
        box3.add(Box.createHorizontalStrut(30));
        box3.add(Cancel_Button);


        Base1 = Box.createVerticalBox();
        // 与顶层的距离
        Base1.add(Box.createVerticalStrut(40));
        Base1.add(box1);
        Base1.add(Box.createVerticalStrut(20));
        Base1.add(box2);
        Base1.add(Box.createVerticalStrut(20));
        Base1.add(box3);


//        Base2 = Box.createHorizontalBox();
//        Base2.add(Box.createVerticalStrut(20));
//        Base2.add(Base1);
//        Base2 = Box.createHorizontalBox();
//        Base2.add(Ok_Button);
//        Base2.add(Box.createHorizontalStrut(20));
//        Base2.add(Cancel_Button);
        // 建立监听设置
        Ok_Button.addActionListener(lu);
        Cancel_Button.addActionListener(lu);

        // 进行参数的传递
        lu.setName_Text(Name_Text);
        lu.setPassword_Text(Password_Text);
        lu.setOk_Button(Ok_Button);
        lu.setCancel_Button(Cancel_Button);
        lu.setjFrame(this);

//        Main_GUI_Plugin main_gui_plugin = new Main_GUI_Plugin();

        // 布局的设置
        add(Base1);
        ImageIcon image=new ImageIcon("E:\\Web-Download\\wer.gif");
        JLabel logo_label = new JLabel(image);

        logo_label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());

        // 设置底层把图片放在最下面的一层
        getLayeredPane().add(logo_label, Integer.valueOf(Integer.MIN_VALUE));

        //设置内容面板  getContentPane前面添加  这个JFrame的对象 由于这个图片是继承了JFrame  所以不需要对象 或者使用this
        JPanel jp = (JPanel) this.getContentPane();

        //设置内容面板未透明  true代表透明  透明之后的gui界面是看不到背景图像的
        jp.setOpaque(false);

        setLayout(new FlowLayout());

        setBounds(500, 200, 300, 270);
        // 不可修改界面
        setResizable(false);
        setVisible(true);
        setTitle("Login Interface");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    public static void main(String[] args){
            Login_Gui loginUserGui = new Login_Gui();
            loginUserGui.init();
    }

}
