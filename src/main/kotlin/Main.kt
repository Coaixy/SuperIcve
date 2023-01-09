import Data.Course
import Data.UserInfo
import Service.Requests

fun main(args: Array<String>) {
    Requests.init() //初始化请求
    UserInfo.init() //初始化用户信息
    Course.init() //初始化课程信息
}