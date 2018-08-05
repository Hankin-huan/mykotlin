//package hankin.mykotlin.classes;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ClassesJava extends Y { // Y 是数据类
//
//    public ClassesJava(int id, @NotNull String name) {
//        super(id, name);
//    }
//
//    public static void main(String[] args){
//        D dd = D.INSTANCE;
//        E e1 = E.Companion.ofE();
//        E e2 = E.ofE();
////        E.Companion.getNum();
//        int aa = E.num;
//
//        List<Integer> ls = new ArrayList<>();
//        ls.add(1);
//
//        int d = 10;
//        int e = 21>>1;
//        System.out.println(d);
//        System.out.println(e);
//
//        int a = 110>>68;
//        int b = 110>>4;
//        int f = 110>>(64*100);
//        int g = 110>>32;
//
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(f);
//        System.out.println(g);
//
//        System.out.println("---------------------------------------------------------------------------------------");
//        try {
//            String arr[] = {"cmd.exe", "/c", "curl http://www.baidu.com"};//如果要https需要配置cacert.pem CA证书
//            Runtime runtime = Runtime.getRuntime();
//            Process process = runtime.exec(arr);//在cmd运行命令
//
//            InputStream inputStream = process.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"gb2312"));
//            String line = null;
//            while((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (Exception eee) {
//            eee.printStackTrace();
//        }
//        System.out.println("---------------------------------------------------------------------------------------");
//    }
//
//}
