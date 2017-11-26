package hankin.mykotlin.grammar

// val 修饰的表示常量，分为编译时常量(const修饰)与运行时常量
// var 修饰表示变量，不可用const修饰
const val HELLO = "hello" // 不加 const 的常量可以通过反射修改其值，const修饰是编译期常量，反射不了
var hi = HELLO // 类型推导，不用 : String 加上数据类型

fun main(args: Array<String>): Unit { // 函数没有返回值的时候为 Unit，此时返回值类型可以省略不写
    println("hello ${sum(1, 2)}") // 匿名函数
    println("${l(1)}, ${ll(2)}")

    println("${lambda1()}, ${lambda2(4, 4)}") // 5, 8   lambda表达式 其实就是匿名函数
    lambda2(7, 7) // 这里的 () 就相当于下面的 .invoke 函数    ()运算符重载？
    lambda2.invoke(6, 6)

    println("-----------------------------------------------------------------")
    var arr = charArrayOf('a', 'b')
    arr.forEach { print(it) } // forEach 遍历  it ?
    arr.forEach { it -> print(it) }
    arr.forEach() { it -> print(it) }//如果函数的最后一个参数是一个lambda表达式，那么可以这样写，()中传其他参
    arr.forEach() { print(it) }
    arr.forEach(::print) // 终极写法 。。
    println()

    arr.forEach ForEach@{ // ForEach@ 与 return@ForEach 终止lambda表达式，如果是直接return，那么main函数就return了
        if (it == 'b') return@ForEach
        println(it)
    }

    println("-----------------------------------------------------------------")
    println(lambda4({ ch: Char -> abc(ch, 2) }))
    println(lambda4.invoke() {  abc(it, 2) }) // 等同于 ch -> abc(ch,2) 或 ch: Char -> abc(ch, 2)
    println("-----------------------------------------------------------------")
    println(::sum is (Int, Int)-> Int) // ::sum 表示sum函数的引用？
    println(lambda4)
    println(::lambda4) // lambda表达式没有引用？
}

fun abc(ch: Char,a: Int): Int{
    println("abc : $ch, ${a+a}")
    return a+a
}

fun sum(a: Int, b: Int): Int{ // 此函数可以写成 fun sum2(a: Int, b: Int) = a+b
    return a+b
}
fun sum2(a: Int, b: Int) = a+b //可以理解为 fun sum2 的返回值为 a+b 的返回值

var l = fun(x: Int): Long { // 匿名函数必须赋值给一个变量，否则匿名函数报错
    return x.toLong()
}
var ll = fun(x: Int) = x.toLong() // 卧槽，还可以这样写，与c里面的函数指针有点类似

var lambda1 = {  // kotlin中lanbda表达式需要用 {} 括起来？
    println()
    5
} // 类型 ()->Int         Function0
var lambda2 = {
    // 注意看 () 的放的位置
    a: (Int), b: Int -> // a、b表示传入参数，没有参数的话 ->可以不写， ->后多行可以不用{}括起来
    println("$a + $b = ${a+b}")
    a+b  // 最后一行为此函数的返回值
} // 类型 (Int. Int)->Int     Function2
var lambda4 = {
    ac: (Char) -> Int -> // 注意断句。ac是变量名，(Char) -> Int是一个整体，表示lambda数据类型
    println(ac)          // 所以lambda4就是一个传参为一个 (Char) -> Int 类型lambda表达式的匿名函数
    ac('a')          // 这里调用的是实参的lambda表达式的函数体代码， 'a'对应实参函数体中的it  it只适用于只有一个形参时
    7                    // lambda4的返回值为 7 ，也可以以上一句代码作为返回值
} // 类型 ((Char) -> Int) -> Int     Function1   Function参数最多只有22个，超过会报错
