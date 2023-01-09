package Service

import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

/**
 * Request请求
 */
object Requests {
    var cookie:String = ""
    private const val userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.76"
    private var body = ""
    private var url = ""
    private fun getCookieFilePath():String{
        return File("").absolutePath+"\\cookie.txt"
    }
    fun init(cookie:String){
        this.cookie = File(getCookieFilePath()).readText()
    }
    fun setBody(key:String,value:String,last:Boolean=false){
        body = if (last){
            "$body$key=$value"
        }else{
            "$body$key=$value&"
        }
    }
    fun clearBody(){
        body = ""
    }
    fun setUrl(url:String){
        this.url = url
    }
    fun post():String{
        return if (this.cookie!="" && this.url!=""){
            val client = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .setHeader("User-Agent",this.userAgent)
                .setHeader("cookie",this.cookie)
                .POST(HttpRequest.BodyPublishers.ofString(this.body))
                .build()
            val response = client.send(request,HttpResponse.BodyHandlers.ofString())
            response.body().toString()
        }else{
            ""
        }
    }
}