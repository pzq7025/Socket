package homework;

/**
 * 管理者界面  继承于Main_GUI
 */

public class Administrator_GUI extends Main_GUI {

    @Override
    void init() {
        // 先继承后改造
        // 管理者不能上传文件
        super.setTitle("管理员界面");
        super.init();
        super.getRecord_Upload().setEnabled(false);
    }

//    @Override
//    void File_GUI(){
//        super.File_GUI();
//        super.getFile_Up().setEnabled(false);
//    }

}
