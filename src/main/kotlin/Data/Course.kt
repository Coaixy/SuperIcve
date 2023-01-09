package Data

import Service.Requests
import mjson.Json

/**
 * 存储课程信息
 */
object Course {
    var name:MutableList<String> = mutableListOf()
    var openId:MutableList<String> = mutableListOf()
    var openClassId:MutableList<String> = mutableListOf()
    var process:MutableList<String> = mutableListOf()

    fun init(){
        Requests.setUrl(Apis.learningCourseList)
        Requests.clearBody()
        val data = Json.read(Requests.post())
        val courseList = data.at("courseList").asJsonList()
        for (i in courseList){
            openId.add(i.at("courseOpenId").asString())
            name.add(i.at("courseName").asString())
            openClassId.add(i.at("openClassId").asString())
            process.add(i.at("process").asString())
        }
    }
}