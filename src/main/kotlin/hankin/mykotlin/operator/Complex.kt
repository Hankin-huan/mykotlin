package hankin.mykotlin.operator

import java.io.BufferedReader
import java.io.FileReader

class Complex(var d: Double){
    operator fun plus(a: Int): Complex{ // + 运算符重载，参数只能是一个
        d += a
        return this
    }
    //infix中缀表达式，当函数只有一个参数时，就可用它修饰，调用时可去掉.和()调用
    infix fun on(any: Any): Boolean{ // 定义一个 on 运算符，可以是任意字符串
        return false
    }
    override fun toString(): String {
        return "Complex(d=$d)"
    }
}

fun doWhen(num: Any): Any{
    var u: Any = when(num){ // when相当于java中的switch case，且when分支也有返回值，返回值为每个分支最后一句代码
        0, 1 -> println("01") // 相当于case 0: 不加break; case 1： ，所以num为0/1时println("01")都会调用
        is Int -> { // 如果when()中传入的是Int类型，那么case分支只能判断 is Int ，或者Int的子类。如果上面的分支执行了，下面的分支就算true也不会执行
            println("is Int")
        }
        !in arrayOf("ad", "cd") -> println("!in arrayOf(\"ab\", \"cd\")")
        else -> { // else 相当于java中case的default语句，当只有一句代码时 {} 可以不写，上面的分支一样
            println("default")
        }
    }
    return u
}

fun main(args: Array<String>) {
    var complex = Complex(1.0)
    println("${complex+1}, ${complex on Unit}, ${complex on Complex(2.0)}") // complex on Unit 这种就叫中缀表达式

    var a: Int = 3
    val b = if(a==0){ // if 表达式，有返回值的，返回值为代码块中的最后一句代码
        0
    } else if(a==1){
        1.0f
    } else {
        "abc" // 什么都不写的时候，b的打印值为 kotlin.Unit
    }
    println(b)

    doWhen("ad") // default

    var arr = arrayOf(22, 33)
    for ((index, value) in arr.withIndex()){
        print("$index, $value;")
    }
    for (ret in arr.withIndex()){ // 等同于上面的for循环
        print("${ret.index}, ${ret.value};")
    }
    println()
    class MyIterator(val iterator: Iterator<Int>){
        operator fun next(): Int{
            return iterator.next()
        }
        operator  fun hasNext(): Boolean{
            return iterator.hasNext()
        }
    }
    class MyList{
        private val list = ArrayList<Int>()
        fun add(int: Int){
            list.add(int)
        }
        operator  fun iterator(): MyIterator{
            return MyIterator(list.iterator())
        }
    }
    var ls = MyList()
    ls.add(55)
    ls.add(66)
    for (i in ls){
        print("$i, ")
    }
    println()

    Outter@for (i in arr){
        var j = 1
        Inner@while (j>=0){
            print("${arr[j]}, ")
            j--
            if (j==0) break@Inner // break@Inner 退出内层循环             33, 33,
//            if (j==0) break@Outter // break@Outter 退出内层与外层循环     33,
        }
    }
    println()

    var num = try {
        1 / 0
    } catch (e: Exception){
        1
    } finally { // finally 不会成为 try catch 表达式的返回值
        2
    }
    println(num)

    BufferedReader(FileReader("hello.txt")).use { // use 内部 做了流的关闭操作
        var line:String?
        while (true){
            line = it.readLine()?:break
            println(line)
        }
    }
}
