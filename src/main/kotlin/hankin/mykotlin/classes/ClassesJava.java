package hankin.mykotlin.classes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClassesJava extends Y { // Y 是数据类

    public ClassesJava(int id, @NotNull String name) {
        super(id, name);
    }

    public static void main(String[] args){
        D dd = D.INSTANCE;
        E e1 = E.Companion.ofE();
        E e2 = E.ofE();
//        E.Companion.getNum();
        int aa = E.num;

        List<Integer> ls = new ArrayList<>();
        ls.add(1);

        int d = 10;
        int e = 21>>1;
        System.out.println(d);
        System.out.println(e);

        int a = 110>>68;
        int b = 110>>4;
        int f = 110>>(64*100);
        int g = 110>>32;

        System.out.println(a);
        System.out.println(b);
        System.out.println(f);
        System.out.println(g);
    }

}
