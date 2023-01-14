package Data

import Service.Logger
import Service.Requests
import mjson.Json

/**
 * 存储课程信息
 */
object Course {
    var meta = ""
    var name:MutableList<String> = mutableListOf()
    var openId:MutableList<String> = mutableListOf()
    var openClassId:MutableList<String> = mutableListOf()
    var process:MutableList<String> = mutableListOf()
    var selectedOpenid = ""
    var selectedOpenClassId = ""
    fun init(){
        Requests.setUrl(Apis.learningCourseList)
        Requests.clearBody()
        this.meta = Requests.post()
        val data = Json.read(meta)
        val courseList = data.at("courseList").asJsonList()
        for (i in courseList){
            openId.add(i.at("courseOpenId").asString())
            name.add(i.at("courseName").asString())
            openClassId.add(i.at("openClassId").asString())
            process.add(i.at("process").asString())
        }
    }

    fun setId(index: Int){
        setOpenClassId(index)
        setOpenClassId(index)
    }
    fun setOpenId(index:Int){
        this.selectedOpenid = openId[index]
    }
    fun setOpenClassId(index: Int){
        this.selectedOpenClassId = openClassId[index]
    }

    /**
     * 判断是否已选择科目
     */
    fun isSelectedId():Boolean{
        return selectedOpenid != "" && selectedOpenClassId != ""
    }
    /**
     * 获取Module List
     * 第一个元素是moduleId 剩下的是module的源数据 (未进行解析的字符串)
     * 如果第一个元素是error说明出现问题
     */
    fun getProcessList(): MutableList<String> {
        val result = mutableListOf<String>()
        return if (isSelectedId()){
            Requests.setUrl(Apis.processList)
            Requests.clearBody()
            Requests.setBody("courseOpenId",this.selectedOpenid)
            Requests.setBody("openClassId",this.selectedOpenClassId,true)
            val meta =  Requests.post()
            val data = Json.read(meta).at("progress")
            val moduleId = data.at("moduleId").asString()
            val moduleList = data.asJsonList()
            result.add(moduleId)
            for (i in moduleList){
                result.add(i.asString())
            }
            result
        }else{
            result.add("error")
            result
        }
    }

    /**
     * 解析module信息
     * 返回一个Map 拥有 id name percent
     */
    fun parseModuleString(data:String):MutableMap<String,String>{
        val result = mutableMapOf<String,String>()
        val jsonData  = Json.read(data)
        result["id"] = jsonData.at("id").asString()
        result["name"] = jsonData.at("name").asString()
        result["percent"] = jsonData.at("percent").asString()
        return result
    }

    /**
     * 获取Topic List
     * 第一个元素为code 剩下的是topic的源数据(未进行Json解析的字符串)
     * 如果第一个元素是error说明出现问题
     */
    fun getTopicList(moduleId:String):MutableList<String>{
        val result = mutableListOf<String>()
        return if (isSelectedId()){
            Requests.setUrl(Apis.topicByModuleId)
            Requests.clearBody();
            Requests.setBody("courseOpenId",this.selectedOpenid)
            Requests.setBody("moduleId",moduleId,true)
            val data = Json.read(Requests.post())
            result.add(data.at("code").asString())
            for (i in data.at("topicList").asJsonList()){
                result.add(i.asString())
            }
            result
        }else{
            result.add("error")
            result
        }
    }
    /**
     * 解析Topic信息
     * 获取的ID仍可以是topic  考虑到子节点的存在
     * 返回一个Map 拥有id name
     */
    fun parseTopicString(data:String):MutableMap<String,String>{
        val result = mutableMapOf<String,String>()
        val jsonData = Json.read(data)
        result["id"] = jsonData.at("id").asString()
        result["name"] = jsonData.at("name").asString()
        return result
    }
}