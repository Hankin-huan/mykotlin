package hankin.mykotlin.classes

class People(var xg: String, var zx: String){
    init {

    }
    fun sing(str: String){

    }
}

class A{
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
