package homework;

import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * 内部成员  用接口访问
 * 进行类的封装
 */
public abstract class User {
    private String name, password, role;

    User(String name, String password, String role){
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // DownLoad file dir
    String DownLoadPath = "E:\\exploitation\\codes\\downloadfile\\";
    // Upload file dir
    String UpLoadPath = "E:\\exploitation\\codes\\uploadfile\\";




    // 修改密码
    public boolean changeSelfInfo(String password) throws SQLException {
        // 更新信息
        if (DataProcess.update(name, password, role)){
            this.password=password;
            return true;
        }else
            return false;
    }


//    // 上传文件
//    public void UpFile(String filename) throws SQLException{
//        double ranVal = Math.random();
//        if (ranVal > 0.5){
//            throw new SQLException("Upload file is fail!");
//        }
//        System.out.println("上传成功!");
//    }


//    // 下载文件
//    public void DownLoad(String filename) throws IOException {
//        double ranVal = Math.random();
//        if (ranVal > 0.5){
//            throw new IOException("Download files is wrong!");
//        }
//        System.out.println("下载成功!");
//    }

    // 下载文件列表
    public boolean downloadFile(String ID) throws IOException, SQLException {
//        double ranVal = Math.random();
//        if (ranVal > 0.5){
//            throw new IOException("Download files is wrong!");
//        }
        try {
            byte[] buffer = new byte[1024];

            Doc doc = DataProcess.search_Doc(ID);

            if (doc == null)
                return false;

            // Gain orgin file
            File tempFile = new File(UpLoadPath + doc.getFilename());
//        System.out.println(tempFile);
            // 将文件名获取出来 添加给下载的路径
            String filename = tempFile.getName();


            // Gain File_Stream  and Read File1as
            BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile));
            // Save file to download Path
            BufferedOutputStream target_file = new BufferedOutputStream(new FileOutputStream(DownLoadPath + filename));

            while (true) {
                // from file read character to Array
                int byteRead = infile.read(buffer);
                if (byteRead == -1)
                    break;
//            target_file.flush();
                target_file.write(buffer, 0, byteRead);
            }
            infile.close();
            target_file.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 显示Document的列表
    public void showFileList() throws SQLException{
//        double ranVal = Math.random();
//        if (ranVal > 0.5){
//            throw new SQLException("Show files is wrong!");
//        }
//        String list[][] = new String[1][5];
//        String list = "";
        Enumeration<Doc> e = DataProcess.getAllDocs();
        Doc doc;
        try {
            while (e.hasMoreElements()) {
                doc = e.nextElement();
                System.out.println("ID:" + doc.getID() + "\t\tCreator:" + doc.getCreator() + "\t\tTimestamp:" +
                        doc.getTimestamp() + "\tDescription:" + doc.getDescription() +
                        "\t\nFilename:" + doc.getFilename());
//                list = {{doc.getID()};
//                list = doc.getID()+" "+doc.getCreator()+" "+doc.getTimestamp()+" "+doc.getDescription()+" "+doc.getFilename();
            }
//            return list;
        } catch (Exception e1){
            e1.printStackTrace();
        }
//        return "error";
        // Show all balances in hash table.
    }

    // 定义输出的函数
    public abstract void showInfo();


    // 定义调用的GUI函数
    public abstract void GUI();

//
//    // 定义时候需要调用上传菜单界面
//    public abstract void File_Up();



    // 后面是获取的接口函数
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


}
