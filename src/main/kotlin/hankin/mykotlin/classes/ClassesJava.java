package hankin.mykotlin.classes;

import java.util.ArrayList;
import java.util.List;

public class ClassesJava {

    public static void main(String[] args){
        D d = D.INSTANCE;
        E e1 = E.Companion.ofE();
        E e2 = E.ofE();
//        E.Companion.getNum();
        int a = E.num;

        List<Integer> ls = new ArrayList<>();
        ls.add(1);
    }

}
