package Data

import Service.Requests
import mjson.Json

/**
 * 存储用户信息
 */
object UserInfo {

    var name = ""
    var number = ""
    fun init(){
        Requests.setUrl(Apis.index)
        Requests.clearBody()
        val data = Json.read(Requests.post())
        this.name = data.at("disPlayName").asString()
        this.number = data.at("stuNo").asString()
    }
}