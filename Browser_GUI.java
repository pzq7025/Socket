package homework;

/**
 * 浏览者界面  继承于Main_Gui
 */

public class Browser_GUI extends Main_GUI {


    @Override
    void init() {
        // 先继承后改造
        super.init();
        super.setTitle("浏览者界面");
        //浏览者界面只可以下载文件  以及修改个人信息
        super.getUser_Manage().setEnabled(false);
        super.getRecord_Upload().setEnabled(false);
    }



//    @Override
//    void File_GUI() {
//        // 先继承后改造
//        super.File_GUI();
//        super.getFile_Up().setEnabled(false);
//        Constitute_GUI constitute_gui = new Constitute_GUI();
//        constitute_gui.getFile_Up_j().setEnabled(false);
//    }
}
