package homework;

/**
 * 文件操作者界面  继承于Main_GUI
 */

public class Operator_Gui extends Main_GUI {

    @Override
    void init() {
        // 先继承后改造
        super.init();
        super.setTitle("文件管理者界面");
        // 文件操作者只能管理文件 以及个人信息
        super.getUser_Manage().setEnabled(false);
    }

//    @Override
//    void File_GUI() {
//        // 先继承后改造
//        super.File_GUI();
//    }
}
