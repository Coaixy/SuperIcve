package Service

object Logger {
    fun info(message:String){
        println("【信息】$message")
    }
    fun error(message:String){
        println("【错误】$message")
    }
    fun warn(message: String){
        println("【警告】$message")
    }
}