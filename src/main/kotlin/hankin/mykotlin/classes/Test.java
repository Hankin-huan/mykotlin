package hankin.mykotlin.classes;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        File file = new File("E:\\opencv\\project\\workspace_vs\\OpenCV342Sources_contrib\\install\\x64\\vc14\\lib");
        String[] names = file.list();
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }

}
