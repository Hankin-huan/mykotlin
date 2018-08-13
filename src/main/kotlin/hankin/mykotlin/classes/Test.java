package hankin.mykotlin.classes;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        // opencv 所有lib库
//        File file = new File("E:\\opencv\\project\\workspace_vs\\OpenCV342Sources_contrib\\install\\x64\\vc14\\lib");
//        String[] names = file.list();
//        for (int i = 0; i < names.length; i++) {
//            System.out.println(names[i]);
//        }

        // orl faces database 所有人脸数据图像
//        String orl = "E:\\opencv\\images\\orl_faces";
//        File file = new File(orl);
//        String[] names = file.list();
//        for (int i = 0; i < names.length; i++) {
//            String dir = names[i];
//            int num = Integer.valueOf(dir.substring(1));
//            String child = orl+"\\"+dir;
//            File f = new File(child);
//            String imgs[] = f.list();
//            for (int j = 0; j < imgs.length; j++) {
//                System.out.println(child+"\\"+imgs[j]+";"+num);
//            }
//        }

        // myfaces 所有人脸数据图像
        String myfaces = "E:\\opencv\\images\\myfaces";
        File file = new File(myfaces);
        String[] names = file.list();
        for (int i = 0; i < names.length; i++) {
            System.out.println(myfaces+"\\"+names[i]+";"+2);
        }
    }

}
