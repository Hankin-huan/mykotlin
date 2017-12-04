package hankin.mykotlin.classes

import hankin.mykotlin.annotations.PoKo
import kotlin.reflect.KProperty

class People(var xg: String, var zx: String){
    lateinit var ss: String
    init {
        ss = xg
    }
    fun sing(str: String){

    }
}

open class A{
    var a = 0 // 缺省的访问权限是public
    get() {
        println("get a")
        return field // field 关键字只能在 get set 里使用
    }
    set(value) { // 成员a 对应java里的get set函数 。 必须写在成员后面。 val 常量没有set函数，只有get
        println("set a")
        field = value
    }
//    protected set // 如果只是想设置set函数访问权限，可以简写成这样

    lateinit var b: String // lateinit 告诉编译器延时初始化(注意是使用的var，不能是val)，在初始化前使用此变量的话，会报错
    val c: People by lazy { // val延时初始化的方式，只有在c被使用前，才会执行lazy中的lambda表达式
        println("init People")
        People("", "")
    } // var的lateinit 或val的延时初始化的方式 都不支持 get set
}

interface IFace{   // 接口不能有状态，接口中的成员属性必须初始化
    var num: Int   // 不能 var num: Int = 31 这样初始化，java可以。kotlin中需要用get初始化
        get() = 31 // num的默认实现，不能用 {} 的方式做默认实现
        set(value) = TODO() // 因为没有back field，所以不能 field = value ，编译报错。set没有默认实现，如果子类不override num而直接赋值会运行抛宕
    fun abc(){
        println(num) // 接口的默认实现，所以实现此接口时可以不实现此函数
    }
    fun aabb(): Int = 11 // 返回值不是Unit的接口函数，默认实现不能用 {} ，只能用这种。。。。
    fun bcd(some: Int): String = "IFace"
}
interface ILoop{
    fun bcd(some: Int): String = "ILoop"
    fun ddd()
}
abstract class P{ // abstract就自带open属性了
    internal var shu = 3 // internal表示当前module内可见，module外为private
    abstract fun def()
    open fun bcd(some: Int): String = "P"
}
// kotlin也是单继承，多实现                                                      //这里的使用场景是by的接口代理，by还有一种是属性代理
class MyClass(override var num: Int, val bl: ILoop) : P(), IFace, ILoop by bl { //ILoop by bl表示使用成员bl的ILoop的接口实现
//    override var num: Int = 1 // 除了构造函数形参方式重写，IFace的num重写的另一种方式
    override fun def() {}
//    override fun aabb(): Int {  // 因为IFace中的aabb有默认实现，所以子类里可以不重写
//        TODO("not implemented") // 这个会运行抛宕
//    }
    override fun bcd(some: Int): String {
        if (some>10) return super<IFace>.bcd(some) // 如果父类们有相同的成员，使用super<>区分调用那个父类的成员
            else if (some>5) return super<ILoop>.bcd(some)
            else return super<P>.bcd(some)
    }
}
class C: ILoop{
    constructor(){ // 将构造函数写在类内部的时候，就不能在开头添加构造函数了。class C(): ILoop 会报错
        println("constructor no args")
    }
    constructor(som: Int){
        println("constructor one arg")
    }
    init {
        println("init") // init { } 代码块会优先于构造函数执行
    }
    override fun ddd() {
        println("c loop")
    }
}
object D{ // 定义单例类，类的成员都是public static的
    var num = 0
    fun eee() = 3
}
class E private constructor(){ // 将构造函数私有化
    companion object { // companion object 伴生对象，伴生对象中的成员类似java中的静态成员
        @JvmStatic  // @JvmStatic 让在java代码里调用时，与java语法一样  E e2 = E.ofE(); 与上式效果一样，可以反编译成java代码看看
        fun ofE(): E{ // 静态成员
            return E()
        }
        @JvmField // 在java中可以 E.num 这样调用num
        var num: Int = 10 // 静态成员。不加 @JvmField 在java中 E.Companion.getNum() 这样调用num
    }
}
class F{
    fun a() = 0
    fun a(num: Int = 0) = 1 // 默认参数可以重载的原因是因为存在具名参数
    @JvmOverloads // 让此类在java中可以 f.b() f.b(1) 两种函数调用，不加 @JvmOverloads 只会有一个带参的函数
    fun b(som: Int = 1)  = 2
}
operator fun String.times(int: Int): String{ // String. 表示扩展String的函数，times为 *乘号的运算符
    var sb = StringBuilder()
    for (i in 0 until int){
        sb.append(this) // this 表示调用者， 这里亮了 =_=
    }
    return sb.toString()
}
var String.num: Int // 扩展String的成员属性
    set(value) { // 因为没有back field 所以不能 field = value

    }
    get() = 7

class X(){
    private var str: String? = null // 这里的String 可以为任意数据类型
//    public inline operator fun <T> Lazy<T>.getValue(thisRef: Any?, property: KProperty<*>): T = value // Lazy源码
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String{ // 这里的String 可以为任意数据类型
        println("getValue : $thisRef --- ${property.name}") // thisRef为Delegates类，property.name为Delegates类中被代理的成员属性名字
        return str?: "str is null"
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String){ // 这里的String 可以为任意数据类型
        println("setValue : $thisRef --- ${property.name} = $value")//thisRef为Delegates类，property.name为Delegates类中被代理的成员属性名字，value为设置的值
        this.str = value
    }
}
class Delegates{
    val hi1 by lazy{
        "Delegates hi1"
    }
    val hi2 by X() // val的属性代理只需要在 X 中重写操作符 getValue
    var hi3 by X() // var的属性代理还需要在 X 中重写操作符 setValue
}

@PoKo // 让数据类不是final，且带无参构造函数
data class Y(var id: Int, var name: String) // data定义数据类(带copy函数)，默认是final的，且只带一个带两个参数的构造函数
open class Z{
    operator fun component1(): String = "this component1" // 非 data 类可以重写componentN 操作符
}

interface Listener{
    fun on()
}
class V{
    var lis: Listener? = null
}
class Out{
    val a = 0
    inner class Inn{ // kotlin内部类默认的是静态的，使用inner关键字定义为非静态的内部类
        val a = 1
        fun abc(){
            this@Inn.a // Inn类的a
            this@Out.a // Out类的a
            var v = V()
            v.lis = object : Z(), Listener{ // 匿名内部类写法，kotlin的匿名内部类还可以继承其他类和实现其他接口的
                override fun on() {
                }
            }
        }
    }
}

//枚举，性质就是一个类
enum class MYTYPE constructor(val id: Int){
    SHUAI(7), HENSHUAI(8); // 如果枚举值后面还要跟函数的话，需要用 ; 号隔开
    fun getArgs(): Int{ // 自定义函数，函数名不能是getId，报错。。。
        return id
    }
    override fun toString(): String {
        return "$id, $name, $ordinal" // name对应 SHUAI ，ordinal对应SHUAI在第几个位置
    }
}

//密封类，此类只能在同一个文件中的类可以继承
sealed class Seal{
    class S1: Seal()
    object S2: Seal() // 匿名内部类
}

fun main(args: Array<String>) {
    var clz: IFace = MyClass(21, C())
    println(clz.num) // 21
    (clz as MyClass).ddd() // c loop
    println("${clz.aabb()}, ${clz.bcd(20)}, ${clz.bcd(8)}, ${clz.bcd(1)}") // 11, IFace, ILoop, P

    println("${D.num}, ${D.eee()}") // 0, 3
    println("${E.num}") // 10

    println("${F().a()}, ${F().a(num = 3)}") // 0, 1

    println("ab"*3) // ababab

    var str = ""
    str.num = 3
    println(str.num) // 7

    println("---------------------------------------------------------------------")
    var delegate = Delegates()
    println(delegate.hi1)
    println(delegate.hi2)
    delegate.hi3 = "hulala"
    println(delegate.hi3)

    println("---------------------------------------------------------------------")
    var y = Y(0, "hankin")
    println("${y.component1()}, ${y.component2()}") // 数据类重写了componentN操作符   0, hankin
    val(a1, a2) = y // 将y中的两个属性赋值给a1 a2 ， for((index, value) in array.withIndex()) 也是这种做法
    println("$a1, $a2") // 0, hankin
    var z = Z()
    var(a3) = z
    println("${z.component1()}: $a3") // this component1: this component1

    var inn = Out().Inn() // 非静态的内部类是持有外部类的状态的，创建实例时需要用外部类的对象.出来，静态类才能直接Inn()。java中也是这个性质

    println("---------------------------------------------------------------------")
    MYTYPE.values().map(::println)
    println("${MYTYPE.SHUAI}, ${MYTYPE.valueOf("SHUAI")}, ${MYTYPE.SHUAI.getArgs()}")
}
