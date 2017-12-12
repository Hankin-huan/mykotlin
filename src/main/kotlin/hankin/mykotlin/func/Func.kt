package hankin.mykotlin.func

import java.io.BufferedReader
import java.io.FileReader
import java.io.OutputStream

fun aaa(arg: Any){
    println(arg)
}

open class A{
    fun println(arg: Any){
        kotlin.io.println(arg)
    }
}

data class ListNode(var value: Int, var next: ListNode? = null)

tailrec fun getNode(value: Int, node: ListNode?): ListNode?{
    node ?: return null
    if (value == node.value) return node
    //如果return后的表达式仅仅是递归调用函数本身，那么就是尾递归，尾递归加上tailrec关键字修饰，编译器会优化为迭代
    return getNode(value, node.next)
}

fun makeFun(): ()->Unit{
    var num = 0//num像是static的，不会随着makeFun函数的运行完而内存回收
    return fun(){
        println(++num)
    }
}

// m(x) = f(g(x))
val add5 = {i: Int -> i+5}//g(x)
val multi = {i: Int -> i*2}//f(x)
//函数复合，PP1、PP2、RR 名字可以随便取，Function1表示只有一个参数的函数，总共有Function0到Function22
//<PP1, PP2, RR>表示此函数有这么些泛型，而且泛型的顺序没有关系，随便排
//Function1<PP1, PP2>.addThen 表示给只有一个参数的函数扩展一个函数，传参为PP1，返回值为PP2
//function: Function1<PP2, RR> 表示传入的参数为一个传参为PP2返回值为RR的一个函数
//Function1<PP1, RR> 表示 Function1<PP1, PP2>.addThen 的返回值为一个传参为PP1返回值为RR的一个函数
infix fun <PP1, PP2, RR> Function1<PP1, PP2>.addThen(function: Function1<PP2, RR>): Function1<PP1, RR>{
    return fun(pp1: PP1): RR{
        //this表示 fun(pp1: PP1): RR 函数本身，我擦，kotlin中this不仅可以表示类本身，还可以表示函数本身，那属性本身？
        return function.invoke(this.invoke(pp1))//multi(add5(8))
    }
}
infix fun <PP1, PP2, RR> Function1<PP2, RR>.addGo(function: Function1<PP1, PP2>): Function1<PP1, RR>{
    return fun(pp1: PP1): RR{
        return this.invoke(function.invoke(pp1))//add5(multi(8))
    }
}

fun mylog(tag: String, target: OutputStream, msg: Any?){
    target.write("$tag, $msg\n".toByteArray())
}
//柯里化
fun curryingLog(tag: String)
        = fun(target: OutputStream)
        = fun(msg: Any?)
                = target.write("$tag, $msg\n".toByteArray())
//将Function3的函数柯里化，fun顺序可以随便的，只需要在传实参时对应顺序就行
fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.curried()
        = fun(p2: P2)
        = fun(p1: P1)
                = fun(p3: P3)
                        = this(p1, p2, p3)
//偏函数，绑定函数某些参数
fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.partial(p2: P2)
        = fun(p1: P1)
        = fun(p3: P3)
                = this(p1, p2, p3)

fun main(args: Array<String>) {
    val arr = arrayOf("", "tang")
    val pa = A::println
    println("-----------------------------------------------------------------------------------------------")
    //函数引用的三种方式：包级引用、类引用、实例引用
    arr.forEach(::aaa)
    println(arr.filter(String::isNotEmpty)) // filter 的集合不能有 null 即String？ 类型
    val a = A()
    val pp = a::println
    arr.forEach(a::println) // 不能 A::println 传入，需要以实例的方式
    println("-----------------------------------------------------------------------------------------------")
    val list = listOf(1..3, 1..2)
    val mapList = list.flatMap {
        it.map {
            it
        }
    }
    mapList.forEach(::println)
    
    println(mapList.reduce { acc, i -> acc+i })
//    arr.fold()

    println("-----------------------------------------------------------------------------------------------")
    BufferedReader(FileReader("hello.txt")).use { // use 已经实现了流的关闭
        var line: String?
        while (true){
            line = it.readLine() ?: break
            println(line)
        }
    }

    println("-----------------------------------------------------------------------------------------------")
    val max = 100000
    val head = ListNode(0)
    var p = head
    for (i in 1..max){
        p.next = ListNode(i)
        p = p.next!!
    }
    println(getNode(max-3, head)?.value)//当max很大时，如果尾递归不加tailrec让编译器优化，会报栈溢出错误

    val fun1 = makeFun()
    fun1()//1
    fun1()//2
    fun1()//3

    println(multi(add5(8)))// (8+5)*2
    //Function1<PP1, PP2>.addThen 等同于上面的表达式
    println((add5 addThen  multi)(8))// (8+5)*2  m(x) = f(g(x))
    println((add5 addGo  multi)(8))// 8*2+5      m(x) = g(f(x))

    mylog("mydebug---", System.out, "printLog")
    curryingLog("mydebug---")(System.out)("printLog again")
    //不是 infix 的函数扩展，需要 :: 加上函数引用？
    ::mylog.curried()(System.out)("mydebug---")("printLog Twice")

    val partial = ::mylog.partial(System.out)
    partial("mydebug---")("printLog Third")
}