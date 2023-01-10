package Data

import Service.Requests
import mjson.Json

/**
 * 存储用户信息
 */
object UserInfo {
    var meta = ""
    var name = ""
    var number = ""
    fun init(){
        Requests.setUrl(Apis.index)
        Requests.clearBody()
        this.meta = Requests.post()
        val data = Json.read(meta)
        this.name = data.at("disPlayName").asString()
        this.number = data.at("stuNo").asString()
    }
}