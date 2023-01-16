import Data.Course
import Data.UserInfo
import Service.Logger
import Service.Requests
import Service.WebSocket
import kotlin.system.exitProcess

fun main() {
    if (!Requests.init() || Requests.cookie == ""){
        Logger.error("没有获取到Cookie")
        exitProcess(0)
    }
    UserInfo.init() //初始化用户信息
    Course.init() //初始化课程信息

//    val webSocketServer = WebSocket(1109)
//    webSocketServer.run()

}