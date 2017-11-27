package hankin.mykotlin.types

import hankin.mykotlin.types.pack.Demo // kotlin中的包就是命名空间

/**
 *  kotlin 数据类型
 * */

val b:Boolean = true

val num:Int = 8
val num2:Int = 0xff
val num3:Int = 0b00000011
val num4:Int = Int.MAX_VALUE
val num5:Int = Int.MIN_VALUE

val a:Long = 123
val aa:Byte = 1
val aaa:Short = 1

val c:Float = 2.0f
val d:Float = 3.0F
val e:Float = 1E3f//1乘以10的三次方... 同样适用于Android Studio Java
val f:Float = 2E3f//2乘以10的三次方...
val g:Float = Float.MIN_VALUE//是个正数，要表示最小值时用 -Float.MAX_VALUE  Double一样
val h:Float = Float.NaN//不是数的数 0.0f/0.0f

val i:Char = '啊'//使用的unicode编码，一个字符占两个字节
val j:Char = '\u000f'// \u表示的unicode编码字符

val k: Int = 5
//val l: Long = k  // kotlin不支持隐式转换
val l: Long = k.toLong()

val m: String = "hello"
val n: String = String(charArrayOf('h', 'e', 'l', 'l', 'o'))

fun main(args:Array<String>){
    run {
        println("-----------------------------------------------------------------------------------")
        println(num4)
        println(e)
        println(f)

        println(m == n)//kotlin中 == 与 equal等价， 结果为true
        println(m === n)//kotlin中比较两个对象的引用是否相同用 ===
    }

    run {
        println("-----------------------------------------------------------------------------------")
        val a1: Int = 1
        val a2: Int = 2
        println("$a1 + $a2 = ${a1 + a2}, ${a1}") // 字符串模板。在 "" 中，$表示取变量值，${} 表示运算

        val str: String = """\n$a1""" // """ """ 包起来的字符串，支持字符串模板，但是不支持转义字符 打印为 \n1
        println(str)
    }

    run {
        println("-----------------------------------------------------------------------------------")
        var meizi: Meizi = Meizi("善良", "小巧")
        println(meizi is Any)  // is 类似于 java中 instanceof
    }

    run {
        println("-----------------------------------------------------------------------------------")
//        var s: String = getStr() ?: return // ? 判空操作符，如果?前面表达式为null则执行:后面表达式(后面表达式似乎只能是 return )
        println(getStr()?.length)//如果getStr返回不为空输出长度，否则输出 null

        val ss: String? = "hello" // String? 定义可以为null 的变量，String定义的变量不能为null
        if (ss is String){ // String? 与 String 可以理解为两种数据类型
            println(ss.length) //相比于下面语法
        }
//        println(ss!!.length) // !! 表示强制执行操作符 否则String?类型的变量 ss.length 编译器会标红

        val parent: Parent = Parent() // 可以创建java类的对象
        val child: Child? = parent as? Child // as(智能类型转换) 对应Child，as? 对应Child? 判断parent对象是否是Child类型，是的话就强转，不是的话为null，这样安全
        println(child?.getName())

        val d: Demo
        println(Demo::class.java.simpleName) // 类名
        println(Demo::class.java.name) // 类的全名，含包名
    }

    run{
        println("-----------------------------------------------------------------------------------")
        val range: IntRange = 0..1024 // 闭区间 [0, 1024]
        val unt: IntRange = 0 until 1024 // 半开区间 [0, 1024)
        println(range.isEmpty())
        println(""+unt.contains(50)+","+(100 in unt)+","+(100 !in 0 until 1024)) // in 也是运算符，ctrl + 鼠标左键 看源码有 operator 运算符重载
        for (i in range){ // in 用于遍历
            print("$i, ")
        }
        println()
    }

    run {
        println("-----------------------------------------------------------------------------------")
        val nums: IntArray = intArrayOf(0, 1, 2) // 数组
        val chars: CharArray = charArrayOf('a', 'b')
        val strs: Array<String> = arrayOf("c", "d")
        println(""+nums.size+","+strs[0]+","+chars.joinToString()) // joinToString函数形参都有默认值
        println(""+nums.slice(1..2)+"-"+nums.slice(1 until 2)) // 以区间的方式获取数组相应的内容

        val str: String = "hello"
        println(str.slice(1..3)) // 打印字符串下标从1到3
    }
}

// 只写class 默认是final的
class Meizi constructor(val xg: String, var zx: String){ //只要一个构造函数时 constructor 可以省略。var(变量?)与val(常量value?) 如果class没有内容{}也可以省略
    init{ // 每次构造时调用
        println("new $xg") // 构造函数中的形参，在类内部也会定义这样的成员属性，在init或其他成员函数里可以使用
    }
    fun abc(){
        xg
    }
}

//open相对于缺省的 final
open class animal(var type: String)

class fish() : animal("fish") // : 继承

fun getStr(): String?{ // String 表示return的不能为null(编辑器报错)， String? 表示return的可以为null，同样适用于定义变量
    return null
}
